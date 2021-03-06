import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/org">
      <Translate contentKey="global.menu.entities.org" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/spe">
      <Translate contentKey="global.menu.entities.spe" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/project">
      <Translate contentKey="global.menu.entities.project" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/spe-proj">
      <Translate contentKey="global.menu.entities.speProj" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/modules">
      <Translate contentKey="global.menu.entities.modules" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/sub-modules">
      <Translate contentKey="global.menu.entities.subModules" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/spe-proj-m-sm">
      <Translate contentKey="global.menu.entities.speProjMSm" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tag">
      <Translate contentKey="global.menu.entities.tag" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/com">
      <Translate contentKey="global.menu.entities.com" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/request">
      <Translate contentKey="global.menu.entities.request" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
