import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './request.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const RequestDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const requestEntity = useAppSelector(state => state.request.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="requestDetailsHeading">
          <Translate contentKey="asiraApp.request.detail.title">Request</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{requestEntity.id}</dd>
          <dt>
            <span id="rFrom">
              <Translate contentKey="asiraApp.request.rFrom">R From</Translate>
            </span>
          </dt>
          <dd>{requestEntity.rFrom}</dd>
          <dt>
            <span id="rTo">
              <Translate contentKey="asiraApp.request.rTo">R To</Translate>
            </span>
          </dt>
          <dd>{requestEntity.rTo}</dd>
          <dt>
            <span id="rPendingState">
              <Translate contentKey="asiraApp.request.rPendingState">R Pending State</Translate>
            </span>
          </dt>
          <dd>{requestEntity.rPendingState ? 'true' : 'false'}</dd>
          <dt>
            <span id="rAccepted">
              <Translate contentKey="asiraApp.request.rAccepted">R Accepted</Translate>
            </span>
          </dt>
          <dd>{requestEntity.rAccepted ? 'true' : 'false'}</dd>
          <dt>
            <span id="rRejected">
              <Translate contentKey="asiraApp.request.rRejected">R Rejected</Translate>
            </span>
          </dt>
          <dd>{requestEntity.rRejected ? 'true' : 'false'}</dd>
          <dt>
            <span id="speNotified">
              <Translate contentKey="asiraApp.request.speNotified">Spe Notified</Translate>
            </span>
          </dt>
          <dd>{requestEntity.speNotified ? 'true' : 'false'}</dd>
          <dt>
            <span id="orgNotified">
              <Translate contentKey="asiraApp.request.orgNotified">Org Notified</Translate>
            </span>
          </dt>
          <dd>{requestEntity.orgNotified ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="asiraApp.request.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            {requestEntity.createdDate ? <TextFormat value={requestEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="asiraApp.request.modifiedDate">Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {requestEntity.modifiedDate ? <TextFormat value={requestEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/request" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/request/${requestEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RequestDetail;
