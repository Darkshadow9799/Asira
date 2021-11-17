import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './sub-modules.reducer';
import { ISubModules } from 'app/shared/model/sub-modules.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SubModules = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const subModulesList = useAppSelector(state => state.subModules.entities);
  const loading = useAppSelector(state => state.subModules.loading);
  const totalItems = useAppSelector(state => state.subModules.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { match } = props;

  return (
    <div>
      <h2 id="sub-modules-heading" data-cy="SubModulesHeading">
        <Translate contentKey="asiraApp.subModules.home.title">Sub Modules</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="asiraApp.subModules.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="asiraApp.subModules.home.createLabel">Create new Sub Modules</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {subModulesList && subModulesList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="asiraApp.subModules.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('smName')}>
                  <Translate contentKey="asiraApp.subModules.smName">Sm Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('smDesc')}>
                  <Translate contentKey="asiraApp.subModules.smDesc">Sm Desc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('smCreatedDate')}>
                  <Translate contentKey="asiraApp.subModules.smCreatedDate">Sm Created Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('smModifiedDate')}>
                  <Translate contentKey="asiraApp.subModules.smModifiedDate">Sm Modified Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('smDueDate')}>
                  <Translate contentKey="asiraApp.subModules.smDueDate">Sm Due Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('smFinishedDate')}>
                  <Translate contentKey="asiraApp.subModules.smFinishedDate">Sm Finished Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('smCompleted')}>
                  <Translate contentKey="asiraApp.subModules.smCompleted">Sm Completed</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('speNameSmTagId')}>
                  <Translate contentKey="asiraApp.subModules.speNameSmTagId">Spe Name Sm Tag Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('smLoggedTime')}>
                  <Translate contentKey="asiraApp.subModules.smLoggedTime">Sm Logged Time</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="asiraApp.subModules.tag">Tag</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="asiraApp.subModules.spe">Spe</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="asiraApp.subModules.module">Module</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {subModulesList.map((subModules, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${subModules.id}`} color="link" size="sm">
                      {subModules.id}
                    </Button>
                  </td>
                  <td>{subModules.smName}</td>
                  <td>{subModules.smDesc}</td>
                  <td>
                    {subModules.smCreatedDate ? <TextFormat type="date" value={subModules.smCreatedDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {subModules.smModifiedDate ? (
                      <TextFormat type="date" value={subModules.smModifiedDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{subModules.smDueDate ? <TextFormat type="date" value={subModules.smDueDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>
                    {subModules.smFinishedDate ? (
                      <TextFormat type="date" value={subModules.smFinishedDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{subModules.smCompleted ? 'true' : 'false'}</td>
                  <td>{subModules.speNameSmTagId}</td>
                  <td>{subModules.smLoggedTime}</td>
                  <td>{subModules.tag ? <Link to={`tag/${subModules.tag.id}`}>{subModules.tag.id}</Link> : ''}</td>
                  <td>{subModules.spe ? <Link to={`spe/${subModules.spe.id}`}>{subModules.spe.id}</Link> : ''}</td>
                  <td>{subModules.module ? <Link to={`modules/${subModules.module.id}`}>{subModules.module.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${subModules.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${subModules.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${subModules.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
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
              <Translate contentKey="asiraApp.subModules.home.notFound">No Sub Modules found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={subModulesList && subModulesList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default SubModules;
