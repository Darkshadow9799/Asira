import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ModulesComponentsPage from './modules.page-object';
import ModulesUpdatePage from './modules-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';

const expect = chai.expect;

describe('Modules e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let modulesComponentsPage: ModulesComponentsPage;
  let modulesUpdatePage: ModulesUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();
    await signInPage.username.sendKeys(username);
    await signInPage.password.sendKeys(password);
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
    await waitUntilDisplayed(navBarPage.adminMenu);
    await waitUntilDisplayed(navBarPage.accountMenu);
  });

  beforeEach(async () => {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    modulesComponentsPage = new ModulesComponentsPage();
    modulesComponentsPage = await modulesComponentsPage.goToPage(navBarPage);
  });

  it('should load Modules', async () => {
    expect(await modulesComponentsPage.title.getText()).to.match(/Modules/);
    expect(await modulesComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Modules', async () => {
    const beforeRecordsCount = (await isVisible(modulesComponentsPage.noRecords)) ? 0 : await getRecordsCount(modulesComponentsPage.table);
    modulesUpdatePage = await modulesComponentsPage.goToCreateModules();
    await modulesUpdatePage.enterData();
    expect(await isVisible(modulesUpdatePage.saveButton)).to.be.false;

    expect(await modulesComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(modulesComponentsPage.table);
    await waitUntilCount(modulesComponentsPage.records, beforeRecordsCount + 1);
    expect(await modulesComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await modulesComponentsPage.deleteModules();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(modulesComponentsPage.records, beforeRecordsCount);
      expect(await modulesComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(modulesComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
