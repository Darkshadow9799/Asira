import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SpeProjMSm from './spe-proj-m-sm';
import SpeProjMSmDetail from './spe-proj-m-sm-detail';
import SpeProjMSmUpdate from './spe-proj-m-sm-update';
import SpeProjMSmDeleteDialog from './spe-proj-m-sm-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SpeProjMSmUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SpeProjMSmUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SpeProjMSmDetail} />
      <ErrorBoundaryRoute path={match.url} component={SpeProjMSm} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SpeProjMSmDeleteDialog} />
  </>
);

export default Routes;
