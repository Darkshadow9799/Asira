import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './sub-modules.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SubModulesDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const subModulesEntity = useAppSelector(state => state.subModules.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="subModulesDetailsHeading">
          <Translate contentKey="asiraApp.subModules.detail.title">SubModules</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{subModulesEntity.id}</dd>
          <dt>
            <span id="smName">
              <Translate contentKey="asiraApp.subModules.smName">Sm Name</Translate>
            </span>
          </dt>
          <dd>{subModulesEntity.smName}</dd>
          <dt>
            <span id="smDesc">
              <Translate contentKey="asiraApp.subModules.smDesc">Sm Desc</Translate>
            </span>
          </dt>
          <dd>{subModulesEntity.smDesc}</dd>
          <dt>
            <span id="smCreatedDate">
              <Translate contentKey="asiraApp.subModules.smCreatedDate">Sm Created Date</Translate>
            </span>
          </dt>
          <dd>
            {subModulesEntity.smCreatedDate ? (
              <TextFormat value={subModulesEntity.smCreatedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="smModifiedDate">
              <Translate contentKey="asiraApp.subModules.smModifiedDate">Sm Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {subModulesEntity.smModifiedDate ? (
              <TextFormat value={subModulesEntity.smModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="smDueDate">
              <Translate contentKey="asiraApp.subModules.smDueDate">Sm Due Date</Translate>
            </span>
          </dt>
          <dd>
            {subModulesEntity.smDueDate ? <TextFormat value={subModulesEntity.smDueDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="smFinishedDate">
              <Translate contentKey="asiraApp.subModules.smFinishedDate">Sm Finished Date</Translate>
            </span>
          </dt>
          <dd>
            {subModulesEntity.smFinishedDate ? (
              <TextFormat value={subModulesEntity.smFinishedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="smCompleted">
              <Translate contentKey="asiraApp.subModules.smCompleted">Sm Completed</Translate>
            </span>
          </dt>
          <dd>{subModulesEntity.smCompleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="speNameSmTagId">
              <Translate contentKey="asiraApp.subModules.speNameSmTagId">Spe Name Sm Tag Id</Translate>
            </span>
          </dt>
          <dd>{subModulesEntity.speNameSmTagId}</dd>
          <dt>
            <span id="smLoggedTime">
              <Translate contentKey="asiraApp.subModules.smLoggedTime">Sm Logged Time</Translate>
            </span>
          </dt>
          <dd>{subModulesEntity.smLoggedTime}</dd>
          <dt>
            <Translate contentKey="asiraApp.subModules.tag">Tag</Translate>
          </dt>
          <dd>{subModulesEntity.tag ? subModulesEntity.tag.id : ''}</dd>
          <dt>
            <Translate contentKey="asiraApp.subModules.spe">Spe</Translate>
          </dt>
          <dd>{subModulesEntity.spe ? subModulesEntity.spe.id : ''}</dd>
          <dt>
            <Translate contentKey="asiraApp.subModules.module">Module</Translate>
          </dt>
          <dd>{subModulesEntity.module ? subModulesEntity.module.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/sub-modules" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sub-modules/${subModulesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SubModulesDetail;
