import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './modules.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ModulesDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const modulesEntity = useAppSelector(state => state.modules.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="modulesDetailsHeading">
          <Translate contentKey="asiraApp.modules.detail.title">Modules</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{modulesEntity.id}</dd>
          <dt>
            <span id="mName">
              <Translate contentKey="asiraApp.modules.mName">M Name</Translate>
            </span>
          </dt>
          <dd>{modulesEntity.mName}</dd>
          <dt>
            <span id="mDesc">
              <Translate contentKey="asiraApp.modules.mDesc">M Desc</Translate>
            </span>
          </dt>
          <dd>{modulesEntity.mDesc}</dd>
          <dt>
            <span id="mSpeId">
              <Translate contentKey="asiraApp.modules.mSpeId">M Spe Id</Translate>
            </span>
          </dt>
          <dd>{modulesEntity.mSpeId}</dd>
          <dt>
            <span id="mProjId">
              <Translate contentKey="asiraApp.modules.mProjId">M Proj Id</Translate>
            </span>
          </dt>
          <dd>{modulesEntity.mProjId}</dd>
          <dt>
            <span id="mCreatedDate">
              <Translate contentKey="asiraApp.modules.mCreatedDate">M Created Date</Translate>
            </span>
          </dt>
          <dd>
            {modulesEntity.mCreatedDate ? <TextFormat value={modulesEntity.mCreatedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="mModifiedDate">
              <Translate contentKey="asiraApp.modules.mModifiedDate">M Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {modulesEntity.mModifiedDate ? <TextFormat value={modulesEntity.mModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="mSmNum">
              <Translate contentKey="asiraApp.modules.mSmNum">M Sm Num</Translate>
            </span>
          </dt>
          <dd>{modulesEntity.mSmNum}</dd>
          <dt>
            <span id="mDueDate">
              <Translate contentKey="asiraApp.modules.mDueDate">M Due Date</Translate>
            </span>
          </dt>
          <dd>{modulesEntity.mDueDate ? <TextFormat value={modulesEntity.mDueDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="mFinishedDate">
              <Translate contentKey="asiraApp.modules.mFinishedDate">M Finished Date</Translate>
            </span>
          </dt>
          <dd>
            {modulesEntity.mFinishedDate ? <TextFormat value={modulesEntity.mFinishedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="mCompleted">
              <Translate contentKey="asiraApp.modules.mCompleted">M Completed</Translate>
            </span>
          </dt>
          <dd>{modulesEntity.mCompleted ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/modules" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/modules/${modulesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ModulesDetail;
