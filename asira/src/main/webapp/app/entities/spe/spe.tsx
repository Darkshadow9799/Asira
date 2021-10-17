import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './spe.reducer';
import { ISpe } from 'app/shared/model/spe.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Spe = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const speList = useAppSelector(state => state.spe.entities);
  const loading = useAppSelector(state => state.spe.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="spe-heading" data-cy="SpeHeading">
        <Translate contentKey="asiraApp.spe.home.title">Spes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="asiraApp.spe.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="asiraApp.spe.home.createLabel">Create new Spe</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {speList && speList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="asiraApp.spe.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.spe.speFirstName">Spe First Name</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.spe.speLastName">Spe Last Name</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.spe.speEmailId">Spe Email Id</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.spe.spePhoneNumber">Spe Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.spe.speOrgId">Spe Org Id</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.spe.speOrgVerified">Spe Org Verified</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.spe.createdDate">Created Date</Translate>
                </th>
                <th>
                  <Translate contentKey="asiraApp.spe.modifiedDate">Modified Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {speList.map((spe, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${spe.id}`} color="link" size="sm">
                      {spe.id}
                    </Button>
                  </td>
                  <td>{spe.speFirstName}</td>
                  <td>{spe.speLastName}</td>
                  <td>{spe.speEmailId}</td>
                  <td>{spe.spePhoneNumber}</td>
                  <td>{spe.speOrgId}</td>
                  <td>{spe.speOrgVerified ? 'true' : 'false'}</td>
                  <td>{spe.createdDate ? <TextFormat type="date" value={spe.createdDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{spe.modifiedDate ? <TextFormat type="date" value={spe.modifiedDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${spe.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${spe.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${spe.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="asiraApp.spe.home.notFound">No Spes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Spe;
