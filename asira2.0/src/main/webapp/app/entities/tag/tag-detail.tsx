import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './tag.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TagDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const tagEntity = useAppSelector(state => state.tag.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tagDetailsHeading">
          <Translate contentKey="asiraApp.tag.detail.title">Tag</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{tagEntity.id}</dd>
          <dt>
            <span id="tagTitle">
              <Translate contentKey="asiraApp.tag.tagTitle">Tag Title</Translate>
            </span>
          </dt>
          <dd>{tagEntity.tagTitle}</dd>
          <dt>
            <span id="tagDesc">
              <Translate contentKey="asiraApp.tag.tagDesc">Tag Desc</Translate>
            </span>
          </dt>
          <dd>{tagEntity.tagDesc}</dd>
          <dt>
            <span id="tagCreatedById">
              <Translate contentKey="asiraApp.tag.tagCreatedById">Tag Created By Id</Translate>
            </span>
          </dt>
          <dd>{tagEntity.tagCreatedById}</dd>
          <dt>
            <span id="tagCreatedByName">
              <Translate contentKey="asiraApp.tag.tagCreatedByName">Tag Created By Name</Translate>
            </span>
          </dt>
          <dd>{tagEntity.tagCreatedByName}</dd>
          <dt>
            <span id="tagCreatedDate">
              <Translate contentKey="asiraApp.tag.tagCreatedDate">Tag Created Date</Translate>
            </span>
          </dt>
          <dd>{tagEntity.tagCreatedDate ? <TextFormat value={tagEntity.tagCreatedDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="tagModifiedDate">
              <Translate contentKey="asiraApp.tag.tagModifiedDate">Tag Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {tagEntity.tagModifiedDate ? <TextFormat value={tagEntity.tagModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="asiraApp.tag.project">Project</Translate>
          </dt>
          <dd>{tagEntity.project ? tagEntity.project.projName : ''}</dd>
        </dl>
        <Button tag={Link} to="/tag" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tag/${tagEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TagDetail;
