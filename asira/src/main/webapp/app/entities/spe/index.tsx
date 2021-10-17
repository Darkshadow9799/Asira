import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Spe from './spe';
import SpeDetail from './spe-detail';
import SpeUpdate from './spe-update';
import SpeDeleteDialog from './spe-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SpeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SpeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SpeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Spe} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SpeDeleteDialog} />
  </>
);

export default Routes;
