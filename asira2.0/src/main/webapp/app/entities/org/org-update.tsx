import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './org.reducer';
import { IOrg } from 'app/shared/model/org.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const OrgUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const orgEntity = useAppSelector(state => state.org.entity);
  const loading = useAppSelector(state => state.org.loading);
  const updating = useAppSelector(state => state.org.updating);
  const updateSuccess = useAppSelector(state => state.org.updateSuccess);

  const handleClose = () => {
    props.history.push('/org');
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
      ...orgEntity,
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
          ...orgEntity,
          createdDate: convertDateTimeFromServer(orgEntity.createdDate),
          modifiedDate: convertDateTimeFromServer(orgEntity.modifiedDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="asiraApp.org.home.createOrEditLabel" data-cy="OrgCreateUpdateHeading">
            <Translate contentKey="asiraApp.org.home.createOrEditLabel">Create or edit a Org</Translate>
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
                  id="org-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('asiraApp.org.orgName')}
                id="org-orgName"
                name="orgName"
                data-cy="orgName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('asiraApp.org.orgEmailId')}
                id="org-orgEmailId"
                name="orgEmailId"
                data-cy="orgEmailId"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.org.orgPhoneNum')}
                id="org-orgPhoneNum"
                name="orgPhoneNum"
                data-cy="orgPhoneNum"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.org.orgProjNum')}
                id="org-orgProjNum"
                name="orgProjNum"
                data-cy="orgProjNum"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.org.orgMembersNum')}
                id="org-orgMembersNum"
                name="orgMembersNum"
                data-cy="orgMembersNum"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/org" replace color="info">
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

export default OrgUpdate;
