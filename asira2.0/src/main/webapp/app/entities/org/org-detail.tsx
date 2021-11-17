import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './org.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const OrgDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const orgEntity = useAppSelector(state => state.org.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orgDetailsHeading">
          <Translate contentKey="asiraApp.org.detail.title">Org</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{orgEntity.id}</dd>
          <dt>
            <span id="orgName">
              <Translate contentKey="asiraApp.org.orgName">Org Name</Translate>
            </span>
          </dt>
          <dd>{orgEntity.orgName}</dd>
          <dt>
            <span id="orgEmailId">
              <Translate contentKey="asiraApp.org.orgEmailId">Org Email Id</Translate>
            </span>
          </dt>
          <dd>{orgEntity.orgEmailId}</dd>
          <dt>
            <span id="orgPhoneNum">
              <Translate contentKey="asiraApp.org.orgPhoneNum">Org Phone Num</Translate>
            </span>
          </dt>
          <dd>{orgEntity.orgPhoneNum}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="asiraApp.org.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>{orgEntity.createdDate ? <TextFormat value={orgEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="asiraApp.org.modifiedDate">Modified Date</Translate>
            </span>
          </dt>
          <dd>{orgEntity.modifiedDate ? <TextFormat value={orgEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="orgProjNum">
              <Translate contentKey="asiraApp.org.orgProjNum">Org Proj Num</Translate>
            </span>
          </dt>
          <dd>{orgEntity.orgProjNum}</dd>
          <dt>
            <span id="orgMembersNum">
              <Translate contentKey="asiraApp.org.orgMembersNum">Org Members Num</Translate>
            </span>
          </dt>
          <dd>{orgEntity.orgMembersNum}</dd>
        </dl>
        <Button tag={Link} to="/org" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/org/${orgEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrgDetail;
