import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale from './locale';
import authentication from './authentication';
import applicationProfile from './application-profile';

import administration from 'app/modules/administration/administration.reducer';
import userManagement from 'app/modules/administration/user-management/user-management.reducer';
import register from 'app/modules/account/register/register.reducer';
import activate from 'app/modules/account/activate/activate.reducer';
import password from 'app/modules/account/password/password.reducer';
import settings from 'app/modules/account/settings/settings.reducer';
import passwordReset from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import org from 'app/entities/org/org.reducer';
// prettier-ignore
import spe from 'app/entities/spe/spe.reducer';
// prettier-ignore
import project from 'app/entities/project/project.reducer';
// prettier-ignore
import modules from 'app/entities/modules/modules.reducer';
// prettier-ignore
import tag from 'app/entities/tag/tag.reducer';
// prettier-ignore
import subModules from 'app/entities/sub-modules/sub-modules.reducer';
// prettier-ignore
import com from 'app/entities/com/com.reducer';
// prettier-ignore
import request from 'app/entities/request/request.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  org,
  spe,
  project,
  modules,
  tag,
  subModules,
  com,
  request,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
