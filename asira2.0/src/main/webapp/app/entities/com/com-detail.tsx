import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './com.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ComDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const comEntity = useAppSelector(state => state.com.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="comDetailsHeading">
          <Translate contentKey="asiraApp.com.detail.title">Com</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{comEntity.id}</dd>
          <dt>
            <span id="cCreatedById">
              <Translate contentKey="asiraApp.com.cCreatedById">C Created By Id</Translate>
            </span>
          </dt>
          <dd>{comEntity.cCreatedById}</dd>
          <dt>
            <span id="cCreatedByName">
              <Translate contentKey="asiraApp.com.cCreatedByName">C Created By Name</Translate>
            </span>
          </dt>
          <dd>{comEntity.cCreatedByName}</dd>
          <dt>
            <span id="cDesc">
              <Translate contentKey="asiraApp.com.cDesc">C Desc</Translate>
            </span>
          </dt>
          <dd>{comEntity.cDesc}</dd>
          <dt>
            <span id="cCreatedDate">
              <Translate contentKey="asiraApp.com.cCreatedDate">C Created Date</Translate>
            </span>
          </dt>
          <dd>{comEntity.cCreatedDate ? <TextFormat value={comEntity.cCreatedDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="cModifiedDate">
              <Translate contentKey="asiraApp.com.cModifiedDate">C Modified Date</Translate>
            </span>
          </dt>
          <dd>{comEntity.cModifiedDate ? <TextFormat value={comEntity.cModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="asiraApp.com.subModules">Sub Modules</Translate>
          </dt>
          <dd>{comEntity.subModules ? comEntity.subModules.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/com" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/com/${comEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ComDetail;
