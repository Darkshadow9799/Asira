import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './spe.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SpeDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const speEntity = useAppSelector(state => state.spe.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="speDetailsHeading">
          <Translate contentKey="asiraApp.spe.detail.title">Spe</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{speEntity.id}</dd>
          <dt>
            <span id="speFirstName">
              <Translate contentKey="asiraApp.spe.speFirstName">Spe First Name</Translate>
            </span>
          </dt>
          <dd>{speEntity.speFirstName}</dd>
          <dt>
            <span id="speLastName">
              <Translate contentKey="asiraApp.spe.speLastName">Spe Last Name</Translate>
            </span>
          </dt>
          <dd>{speEntity.speLastName}</dd>
          <dt>
            <span id="speEmailId">
              <Translate contentKey="asiraApp.spe.speEmailId">Spe Email Id</Translate>
            </span>
          </dt>
          <dd>{speEntity.speEmailId}</dd>
          <dt>
            <span id="spePhoneNumber">
              <Translate contentKey="asiraApp.spe.spePhoneNumber">Spe Phone Number</Translate>
            </span>
          </dt>
          <dd>{speEntity.spePhoneNumber}</dd>
          <dt>
            <span id="speOrgVerified">
              <Translate contentKey="asiraApp.spe.speOrgVerified">Spe Org Verified</Translate>
            </span>
          </dt>
          <dd>{speEntity.speOrgVerified ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="asiraApp.spe.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>{speEntity.createdDate ? <TextFormat value={speEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="asiraApp.spe.modifiedDate">Modified Date</Translate>
            </span>
          </dt>
          <dd>{speEntity.modifiedDate ? <TextFormat value={speEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="asiraApp.spe.org">Org</Translate>
          </dt>
          <dd>{speEntity.org ? speEntity.org.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/spe" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/spe/${speEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SpeDetail;
