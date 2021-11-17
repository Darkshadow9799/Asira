import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ITag } from 'app/shared/model/tag.model';
import { getEntities as getTags } from 'app/entities/tag/tag.reducer';
import { ISpe } from 'app/shared/model/spe.model';
import { getEntities as getSpes } from 'app/entities/spe/spe.reducer';
import { IModules } from 'app/shared/model/modules.model';
import { getEntities as getModules } from 'app/entities/modules/modules.reducer';
import { getEntity, updateEntity, createEntity, reset } from './sub-modules.reducer';
import { ISubModules } from 'app/shared/model/sub-modules.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SubModulesUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const tags = useAppSelector(state => state.tag.entities);
  const spes = useAppSelector(state => state.spe.entities);
  const modules = useAppSelector(state => state.modules.entities);
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

    dispatch(getTags({}));
    dispatch(getSpes({}));
    dispatch(getModules({}));
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
      tag: tags.find(it => it.id.toString() === values.tagId.toString()),
      spe: spes.find(it => it.id.toString() === values.speId.toString()),
      module: modules.find(it => it.id.toString() === values.moduleId.toString()),
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
          tagId: subModulesEntity?.tag?.id,
          speId: subModulesEntity?.spe?.id,
          moduleId: subModulesEntity?.module?.id,
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
                label={translate('asiraApp.subModules.smCreatedDate')}
                id="sub-modules-smCreatedDate"
                name="smCreatedDate"
                data-cy="smCreatedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smModifiedDate')}
                id="sub-modules-smModifiedDate"
                name="smModifiedDate"
                data-cy="smModifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
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
                label={translate('asiraApp.subModules.speNameSmTagId')}
                id="sub-modules-speNameSmTagId"
                name="speNameSmTagId"
                data-cy="speNameSmTagId"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.subModules.smLoggedTime')}
                id="sub-modules-smLoggedTime"
                name="smLoggedTime"
                data-cy="smLoggedTime"
                type="text"
              />
              <ValidatedField id="sub-modules-tag" name="tagId" data-cy="tag" label={translate('asiraApp.subModules.tag')} type="select">
                <option value="" key="0" />
                {tags
                  ? tags.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="sub-modules-spe" name="speId" data-cy="spe" label={translate('asiraApp.subModules.spe')} type="select">
                <option value="" key="0" />
                {spes
                  ? spes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="sub-modules-module"
                name="moduleId"
                data-cy="module"
                label={translate('asiraApp.subModules.module')}
                type="select"
              >
                <option value="" key="0" />
                {modules
                  ? modules.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
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
