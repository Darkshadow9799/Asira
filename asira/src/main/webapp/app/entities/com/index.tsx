import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Com from './com';
import ComDetail from './com-detail';
import ComUpdate from './com-update';
import ComDeleteDialog from './com-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ComUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ComUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ComDetail} />
      <ErrorBoundaryRoute path={match.url} component={Com} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ComDeleteDialog} />
  </>
);

export default Routes;
