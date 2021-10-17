import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './project.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProjectDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const projectEntity = useAppSelector(state => state.project.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="projectDetailsHeading">
          <Translate contentKey="asiraApp.project.detail.title">Project</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{projectEntity.id}</dd>
          <dt>
            <span id="projName">
              <Translate contentKey="asiraApp.project.projName">Proj Name</Translate>
            </span>
          </dt>
          <dd>{projectEntity.projName}</dd>
          <dt>
            <span id="projCreatedDate">
              <Translate contentKey="asiraApp.project.projCreatedDate">Proj Created Date</Translate>
            </span>
          </dt>
          <dd>
            {projectEntity.projCreatedDate ? (
              <TextFormat value={projectEntity.projCreatedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="projModifiedDate">
              <Translate contentKey="asiraApp.project.projModifiedDate">Proj Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {projectEntity.projModifiedDate ? (
              <TextFormat value={projectEntity.projModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="projAdminId">
              <Translate contentKey="asiraApp.project.projAdminId">Proj Admin Id</Translate>
            </span>
          </dt>
          <dd>{projectEntity.projAdminId}</dd>
          <dt>
            <span id="orgId">
              <Translate contentKey="asiraApp.project.orgId">Org Id</Translate>
            </span>
          </dt>
          <dd>{projectEntity.orgId}</dd>
          <dt>
            <span id="projMemberNumber">
              <Translate contentKey="asiraApp.project.projMemberNumber">Proj Member Number</Translate>
            </span>
          </dt>
          <dd>{projectEntity.projMemberNumber}</dd>
        </dl>
        <Button tag={Link} to="/project" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/project/${projectEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProjectDetail;
