import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './spe-proj.reducer';
import { ISpeProj } from 'app/shared/model/spe-proj.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SpeProj = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const speProjList = useAppSelector(state => state.speProj.entities);
  const loading = useAppSelector(state => state.speProj.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="spe-proj-heading" data-cy="SpeProjHeading">
        <Translate contentKey="asiraApp.speProj.home.title">Spe Projs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="asiraApp.speProj.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="asiraApp.speProj.home.createLabel">Create new Spe Proj</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {speProjList && speProjList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="asiraApp.speProj.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.speProj.projId">Proj Id</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.speProj.speId">Spe Id</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.speProj.orgId">Org Id</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {speProjList.map((speProj, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${speProj.id}`} color="link" size="sm">
                      {speProj.id}
                    </Button>
                  </td>
                  <td>{speProj.projId}</td>
                  <td>{speProj.speId}</td>
                  <td>{speProj.orgId}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${speProj.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${speProj.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${speProj.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="asiraApp.speProj.home.notFound">No Spe Projs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default SpeProj;
