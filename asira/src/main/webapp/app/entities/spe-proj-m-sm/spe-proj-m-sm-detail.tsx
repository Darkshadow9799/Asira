import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './spe-proj-m-sm.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SpeProjMSmDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const speProjMSmEntity = useAppSelector(state => state.speProjMSm.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="speProjMSmDetailsHeading">
          <Translate contentKey="asiraApp.speProjMSm.detail.title">SpeProjMSm</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{speProjMSmEntity.id}</dd>
          <dt>
            <span id="speId">
              <Translate contentKey="asiraApp.speProjMSm.speId">Spe Id</Translate>
            </span>
          </dt>
          <dd>{speProjMSmEntity.speId}</dd>
          <dt>
            <span id="projId">
              <Translate contentKey="asiraApp.speProjMSm.projId">Proj Id</Translate>
            </span>
          </dt>
          <dd>{speProjMSmEntity.projId}</dd>
          <dt>
            <span id="mId">
              <Translate contentKey="asiraApp.speProjMSm.mId">M Id</Translate>
            </span>
          </dt>
          <dd>{speProjMSmEntity.mId}</dd>
          <dt>
            <span id="smId">
              <Translate contentKey="asiraApp.speProjMSm.smId">Sm Id</Translate>
            </span>
          </dt>
          <dd>{speProjMSmEntity.smId}</dd>
        </dl>
        <Button tag={Link} to="/spe-proj-m-sm" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/spe-proj-m-sm/${speProjMSmEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SpeProjMSmDetail;
