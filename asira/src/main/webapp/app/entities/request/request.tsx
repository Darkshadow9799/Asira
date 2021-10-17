import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './request.reducer';
import { IRequest } from 'app/shared/model/request.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Request = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const requestList = useAppSelector(state => state.request.entities);
  const loading = useAppSelector(state => state.request.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="request-heading" data-cy="RequestHeading">
        <Translate contentKey="asiraApp.request.home.title">Requests</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="asiraApp.request.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="asiraApp.request.home.createLabel">Create new Request</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {requestList && requestList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="asiraApp.request.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.rFrom">R From</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.rTo">R To</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.rPendingState">R Pending State</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.rAccepted">R Accepted</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.rRejected">R Rejected</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.speNotified">Spe Notified</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.orgNotified">Org Notified</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.createdDate">Created Date</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.request.modifiedDate">Modified Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {requestList.map((request, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${request.id}`} color="link" size="sm">
                      {request.id}
                    </Button>
                  </td>
                  <td>{request.rFrom}</td>
                  <td>{request.rTo}</td>
                  <td>{request.rPendingState ? 'true' : 'false'}</td>
                  <td>{request.rAccepted ? 'true' : 'false'}</td>
                  <td>{request.rRejected ? 'true' : 'false'}</td>
                  <td>{request.speNotified ? 'true' : 'false'}</td>
                  <td>{request.orgNotified ? 'true' : 'false'}</td>
                  <td>{request.createdDate ? <TextFormat type="date" value={request.createdDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{request.modifiedDate ? <TextFormat type="date" value={request.modifiedDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${request.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${request.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${request.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="asiraApp.request.home.notFound">No Requests found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Request;
