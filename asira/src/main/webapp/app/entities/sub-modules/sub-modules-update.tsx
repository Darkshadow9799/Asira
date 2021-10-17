import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './sub-modules.reducer';
import { ISubModules } from 'app/shared/model/sub-modules.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SubModulesUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const subModulesEntity = useAppSelector(state => state.subModules.entity);
  const loading = useAppSelector(state => state.subModules.loading);
  const updating = useAppSelector(state => state.subModules.updating);
  const updateSuccess = useAppSelector(state => state.subModules.updateSuccess);

  const handleClose = () => {
    props.history.push('/sub-modules' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.smCreatedDate = convertDateTimeToServer(values.smCreatedDate);
    values.smModifiedDate = convertDateTimeToServer(values.smModifiedDate);
    values.smDueDate = convertDateTimeToServer(values.smDueDate);
    values.smFinishedDate = convertDateTimeToServer(values.smFinishedDate);

    const entity = {
      ...subModulesEntity,
      ...values,
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
          smCreatedDate: displayDefaultDateTime(),
          smModifiedDate: displayDefaultDateTime(),
          smDueDate: displayDefaultDateTime(),
          smFinishedDate: displayDefaultDateTime(),
        }
      : {
          ...subModulesEntity,
          smCreatedDate: convertDateTimeFromServer(subModulesEntity.smCreatedDate),
          smModifiedDate: convertDateTimeFromServer(subModulesEntity.smModifiedDate),
          smDueDate: convertDateTimeFromServer(subModulesEntity.smDueDate),
          smFinishedDate: convertDateTimeFromServer(subModulesEntity.smFinishedDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="asiraApp.subModules.home.createOrEditLabel" data-cy="SubModulesCreateUpdateHeading">
            <Translate contentKey="asiraApp.subModules.home.createOrEditLabel">Create or edit a SubModules</Translate>
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
                  id="sub-modules-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('asiraApp.subModules.smName')}
                id="sub-modules-smName"
                name="smName"
                data-cy="smName"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smDesc')}
                id="sub-modules-smDesc"
                name="smDesc"
                data-cy="smDesc"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smMId')}
                id="sub-modules-smMId"
                name="smMId"
                data-cy="smMId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smSpeId')}
                id="sub-modules-smSpeId"
                name="smSpeId"
                data-cy="smSpeId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smDueDate')}
                id="sub-modules-smDueDate"
                name="smDueDate"
                data-cy="smDueDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smFinishedDate')}
                id="sub-modules-smFinishedDate"
                name="smFinishedDate"
                data-cy="smFinishedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smCompleted')}
                id="sub-modules-smCompleted"
                name="smCompleted"
                data-cy="smCompleted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smTagID')}
                id="sub-modules-smTagID"
                name="smTagID"
                data-cy="smTagID"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.speIdSmTagId')}
                id="sub-modules-speIdSmTagId"
                name="speIdSmTagId"
                data-cy="speIdSmTagId"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smLoggedTime')}
                id="sub-modules-smLoggedTime"
                name="smLoggedTime"
                data-cy="smLoggedTime"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/sub-modules" replace color="info">
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

export default SubModulesUpdate;
