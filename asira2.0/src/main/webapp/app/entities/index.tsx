import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Org from './org';
import Spe from './spe';
import Project from './project';
import Modules from './modules';
import Tag from './tag';
import SubModules from './sub-modules';
import Com from './com';
import Request from './request';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}org`} component={Org} />
      <ErrorBoundaryRoute path={`${match.url}spe`} component={Spe} />
      <ErrorBoundaryRoute path={`${match.url}project`} component={Project} />
      <ErrorBoundaryRoute path={`${match.url}modules`} component={Modules} />
      <ErrorBoundaryRoute path={`${match.url}tag`} component={Tag} />
      <ErrorBoundaryRoute path={`${match.url}sub-modules`} component={SubModules} />
      <ErrorBoundaryRoute path={`${match.url}com`} component={Com} />
      <ErrorBoundaryRoute path={`${match.url}request`} component={Request} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
