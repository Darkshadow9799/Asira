import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './spe.reducer';
import { ISpe } from 'app/shared/model/spe.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SpeUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const speEntity = useAppSelector(state => state.spe.entity);
  const loading = useAppSelector(state => state.spe.loading);
  const updating = useAppSelector(state => state.spe.updating);
  const updateSuccess = useAppSelector(state => state.spe.updateSuccess);

  const handleClose = () => {
    props.history.push('/spe');
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
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    const entity = {
      ...speEntity,
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
          createdDate: displayDefaultDateTime(),
          modifiedDate: displayDefaultDateTime(),
        }
      : {
          ...speEntity,
          createdDate: convertDateTimeFromServer(speEntity.createdDate),
          modifiedDate: convertDateTimeFromServer(speEntity.modifiedDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="asiraApp.spe.home.createOrEditLabel" data-cy="SpeCreateUpdateHeading">
            <Translate contentKey="asiraApp.spe.home.createOrEditLabel">Create or edit a Spe</Translate>
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
                  id="spe-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('asiraApp.spe.speFirstName')}
                id="spe-speFirstName"
                name="speFirstName"
                data-cy="speFirstName"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.spe.speLastName')}
                id="spe-speLastName"
                name="speLastName"
                data-cy="speLastName"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.spe.speEmailId')}
                id="spe-speEmailId"
                name="speEmailId"
                data-cy="speEmailId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('asiraApp.spe.spePhoneNumber')}
                id="spe-spePhoneNumber"
                name="spePhoneNumber"
                data-cy="spePhoneNumber"
                type="text"
              />
              <ValidatedField label={translate('asiraApp.spe.speOrgId')} id="spe-speOrgId" name="speOrgId" data-cy="speOrgId" type="text" />
              <ValidatedField
                label={translate('asiraApp.spe.speOrgVerified')}
                id="spe-speOrgVerified"
                name="speOrgVerified"
                data-cy="speOrgVerified"
                check
                type="checkbox"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/spe" replace color="info">
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

export default SpeUpdate;
