import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SubModules from './sub-modules';
import SubModulesDetail from './sub-modules-detail';
import SubModulesUpdate from './sub-modules-update';
import SubModulesDeleteDialog from './sub-modules-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SubModulesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SubModulesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SubModulesDetail} />
      <ErrorBoundaryRoute path={match.url} component={SubModules} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SubModulesDeleteDialog} />
  </>
);

export default Routes;
