import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ISubModules } from 'app/shared/model/sub-modules.model';
import { getEntities as getSubModules } from 'app/entities/sub-modules/sub-modules.reducer';
import { getEntity, updateEntity, createEntity, reset } from './com.reducer';
import { ICom } from 'app/shared/model/com.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ComUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const subModules = useAppSelector(state => state.subModules.entities);
  const comEntity = useAppSelector(state => state.com.entity);
  const loading = useAppSelector(state => state.com.loading);
  const updating = useAppSelector(state => state.com.updating);
  const updateSuccess = useAppSelector(state => state.com.updateSuccess);

  const handleClose = () => {
    props.history.push('/com' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getSubModules({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.cCreatedDate = convertDateTimeToServer(values.cCreatedDate);
    values.cModifiedDate = convertDateTimeToServer(values.cModifiedDate);

    const entity = {
      ...comEntity,
      ...values,
      subModules: subModules.find(it => it.id.toString() === values.subModulesId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          cCreatedDate: displayDefaultDateTime(),
          cModifiedDate: displayDefaultDateTime(),
        }
      : {
          ...comEntity,
          cCreatedDate: convertDateTimeFromServer(comEntity.cCreatedDate),
          cModifiedDate: convertDateTimeFromServer(comEntity.cModifiedDate),
          subModulesId: comEntity?.subModules?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="asiraApp.com.home.createOrEditLabel" data-cy="ComCreateUpdateHeading">
            <Translate contentKey="asiraApp.com.home.createOrEditLabel">Create or edit a Com</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="com-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('asiraApp.com.cCreatedById')}
                id="com-cCreatedById"
                name="cCreatedById"
                data-cy="cCreatedById"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('asiraApp.com.cCreatedByName')}
                id="com-cCreatedByName"
                name="cCreatedByName"
                data-cy="cCreatedByName"
                type="text"
              />
              <ValidatedField label={translate('asiraApp.com.cDesc')} id="com-cDesc" name="cDesc" data-cy="cDesc" type="text" />
              <ValidatedField
                id="com-subModules"
                name="subModulesId"
                data-cy="subModules"
                label={translate('asiraApp.com.subModules')}
                type="select"
              >
                <option value="" key="0" />
                {subModules
                  ? subModules.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.smName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/com" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ComUpdate;
