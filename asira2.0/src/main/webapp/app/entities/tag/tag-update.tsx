import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tag.reducer';
import { ITag } from 'app/shared/model/tag.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TagUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const projects = useAppSelector(state => state.project.entities);
  const tagEntity = useAppSelector(state => state.tag.entity);
  const loading = useAppSelector(state => state.tag.loading);
  const updating = useAppSelector(state => state.tag.updating);
  const updateSuccess = useAppSelector(state => state.tag.updateSuccess);

  const handleClose = () => {
    props.history.push('/tag');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getProjects({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.tagCreatedDate = convertDateTimeToServer(values.tagCreatedDate);
    values.tagModifiedDate = convertDateTimeToServer(values.tagModifiedDate);

    const entity = {
      ...tagEntity,
      ...values,
      project: projects.find(it => it.id.toString() === values.projectId.toString()),
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
          tagCreatedDate: displayDefaultDateTime(),
          tagModifiedDate: displayDefaultDateTime(),
        }
      : {
          ...tagEntity,
          tagCreatedDate: convertDateTimeFromServer(tagEntity.tagCreatedDate),
          tagModifiedDate: convertDateTimeFromServer(tagEntity.tagModifiedDate),
          projectId: tagEntity?.project?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="asiraApp.tag.home.createOrEditLabel" data-cy="TagCreateUpdateHeading">
            <Translate contentKey="asiraApp.tag.home.createOrEditLabel">Create or edit a Tag</Translate>
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
                  id="tag-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('asiraApp.tag.tagTitle')} id="tag-tagTitle" name="tagTitle" data-cy="tagTitle" type="text" />
              <ValidatedField label={translate('asiraApp.tag.tagDesc')} id="tag-tagDesc" name="tagDesc" data-cy="tagDesc" type="text" />
              <ValidatedField
                label={translate('asiraApp.tag.tagCreatedById')}
                id="tag-tagCreatedById"
                name="tagCreatedById"
                data-cy="tagCreatedById"
                type="text"
              />
              <ValidatedField
                label={translate('asiraApp.tag.tagCreatedByName')}
                id="tag-tagCreatedByName"
                name="tagCreatedByName"
                data-cy="tagCreatedByName"
                type="text"
              />
              <ValidatedField id="tag-project" name="projectId" data-cy="project" label={translate('asiraApp.tag.project')} type="select">
                <option value="" key="0" />
                {projects
                  ? projects.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.projName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/tag" replace color="info">
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

export default TagUpdate;
