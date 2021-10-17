import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SpeProj from './spe-proj';
import SpeProjDetail from './spe-proj-detail';
import SpeProjUpdate from './spe-proj-update';
import SpeProjDeleteDialog from './spe-proj-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SpeProjUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SpeProjUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SpeProjDetail} />
      <ErrorBoundaryRoute path={match.url} component={SpeProj} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SpeProjDeleteDialog} />
  </>
);

export default Routes;
