import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './modules.reducer';
import { IModules } from 'app/shared/model/modules.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ModulesUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const modulesEntity = useAppSelector(state => state.modules.entity);
  const loading = useAppSelector(state => state.modules.loading);
  const updating = useAppSelector(state => state.modules.updating);
  const updateSuccess = useAppSelector(state => state.modules.updateSuccess);

  const handleClose = () => {
    props.history.push('/modules' + props.location.search);
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
    values.mCreatedDate = convertDateTimeToServer(values.mCreatedDate);
    values.mModifiedDate = convertDateTimeToServer(values.mModifiedDate);
    values.mDueDate = convertDateTimeToServer(values.mDueDate);
    values.mFinishedDate = convertDateTimeToServer(values.mFinishedDate);

    const entity = {
      ...modulesEntity,
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
          mCreatedDate: displayDefaultDateTime(),
          mModifiedDate: displayDefaultDateTime(),
          mDueDate: displayDefaultDateTime(),
          mFinishedDate: displayDefaultDateTime(),
        }
      : {
          ...modulesEntity,
          mCreatedDate: convertDateTimeFromServer(modulesEntity.mCreatedDate),
          mModifiedDate: convertDateTimeFromServer(modulesEntity.mModifiedDate),
          mDueDate: convertDateTimeFromServer(modulesEntity.mDueDate),
          mFinishedDate: convertDateTimeFromServer(modulesEntity.mFinishedDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="asiraApp.modules.home.createOrEditLabel" data-cy="ModulesCreateUpdateHeading">
            <Translate contentKey="asiraApp.modules.home.createOrEditLabel">Create or edit a Modules</Translate>
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
                  id="modules-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('asiraApp.modules.mName')} id="modules-mName" name="mName" data-cy="mName" type="text" />
              <ValidatedField label={translate('asiraApp.modules.mDesc')} id="modules-mDesc" name="mDesc" data-cy="mDesc" type="text" />
              <ValidatedField
                label={translate('asiraApp.modules.mSpeId')}
                id="modules-mSpeId"
                name="mSpeId"
                data-cy="mSpeId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('asiraApp.modules.mProjId')}
                id="modules-mProjId"
                name="mProjId"
                data-cy="mProjId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField label={translate('asiraApp.modules.mSmNum')} id="modules-mSmNum" name="mSmNum" data-cy="mSmNum" type="text" />
              <ValidatedField
                label={translate('asiraApp.modules.mDueDate')}
                id="modules-mDueDate"
                name="mDueDate"
                data-cy="mDueDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('asiraApp.modules.mFinishedDate')}
                id="modules-mFinishedDate"
                name="mFinishedDate"
                data-cy="mFinishedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('asiraApp.modules.mCompleted')}
                id="modules-mCompleted"
                name="mCompleted"
                data-cy="mCompleted"
                check
                type="checkbox"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/modules" replace color="info">
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

export default ModulesUpdate;
