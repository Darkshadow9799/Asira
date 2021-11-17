import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IOrg } from 'app/shared/model/org.model';
import { getEntities as getOrgs } from 'app/entities/org/org.reducer';
import { ISpe } from 'app/shared/model/spe.model';
import { getEntities as getSpes } from 'app/entities/spe/spe.reducer';
import { getEntity, updateEntity, createEntity, reset } from './project.reducer';
import { IProject } from 'app/shared/model/project.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProjectUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const orgs = useAppSelector(state => state.org.entities);
  const spes = useAppSelector(state => state.spe.entities);
  const projectEntity = useAppSelector(state => state.project.entity);
  const loading = useAppSelector(state => state.project.loading);
  const updating = useAppSelector(state => state.project.updating);
  const updateSuccess = useAppSelector(state => state.project.updateSuccess);

  const handleClose = () => {
    props.history.push('/project' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getOrgs({}));
    dispatch(getSpes({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.projCreatedDate = convertDateTimeToServer(values.projCreatedDate);
    values.projModifiedDate = convertDateTimeToServer(values.projModifiedDate);

    const entity = {
      ...projectEntity,
      ...values,
      org: orgs.find(it => it.id.toString() === values.orgId.toString()),
      spe: spes.find(it => it.id.toString() === values.speId.toString()),
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
          projCreatedDate: displayDefaultDateTime(),
          projModifiedDate: displayDefaultDateTime(),
        }
      : {
          ...projectEntity,
          projCreatedDate: convertDateTimeFromServer(projectEntity.projCreatedDate),
          projModifiedDate: convertDateTimeFromServer(projectEntity.projModifiedDate),
          orgId: projectEntity?.org?.id,
          speId: projectEntity?.spe?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="asiraApp.project.home.createOrEditLabel" data-cy="ProjectCreateUpdateHeading">
            <Translate contentKey="asiraApp.project.home.createOrEditLabel">Create or edit a Project</Translate>
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
                  id="project-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('asiraApp.project.projName')}
                id="project-projName"
                name="projName"
                data-cy="projName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('asiraApp.project.projCreatedDate')}
                id="project-projCreatedDate"
                name="projCreatedDate"
                data-cy="projCreatedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('asiraApp.project.projModifiedDate')}
                id="project-projModifiedDate"
                name="projModifiedDate"
                data-cy="projModifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('asiraApp.project.projMemberNumber')}
                id="project-projMemberNumber"
                name="projMemberNumber"
                data-cy="projMemberNumber"
                type="text"
              />
              <ValidatedField id="project-org" name="orgId" data-cy="org" label={translate('asiraApp.project.org')} type="select">
                <option value="" key="0" />
                {orgs
                  ? orgs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="project-spe" name="speId" data-cy="spe" label={translate('asiraApp.project.spe')} type="select">
                <option value="" key="0" />
                {spes
                  ? spes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/project" replace color="info">
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

export default ProjectUpdate;
