import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './spe-proj.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SpeProjDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const speProjEntity = useAppSelector(state => state.speProj.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="speProjDetailsHeading">
          <Translate contentKey="asiraApp.speProj.detail.title">SpeProj</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{speProjEntity.id}</dd>
          <dt>
            <span id="projId">
              <Translate contentKey="asiraApp.speProj.projId">Proj Id</Translate>
            </span>
          </dt>
          <dd>{speProjEntity.projId}</dd>
          <dt>
            <span id="speId">
              <Translate contentKey="asiraApp.speProj.speId">Spe Id</Translate>
            </span>
          </dt>
          <dd>{speProjEntity.speId}</dd>
          <dt>
            <span id="orgId">
              <Translate contentKey="asiraApp.speProj.orgId">Org Id</Translate>
            </span>
          </dt>
          <dd>{speProjEntity.orgId}</dd>
        </dl>
        <Button tag={Link} to="/spe-proj" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/spe-proj/${speProjEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SpeProjDetail;
