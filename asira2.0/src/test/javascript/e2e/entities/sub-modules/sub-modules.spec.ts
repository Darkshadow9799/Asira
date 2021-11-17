import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SubModulesComponentsPage from './sub-modules.page-object';
import SubModulesUpdatePage from './sub-modules-update.page-object';
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

describe('SubModules e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subModulesComponentsPage: SubModulesComponentsPage;
  let subModulesUpdatePage: SubModulesUpdatePage;
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
    subModulesComponentsPage = new SubModulesComponentsPage();
    subModulesComponentsPage = await subModulesComponentsPage.goToPage(navBarPage);
  });

  it('should load SubModules', async () => {
    expect(await subModulesComponentsPage.title.getText()).to.match(/Sub Modules/);
    expect(await subModulesComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete SubModules', async () => {
    const beforeRecordsCount = (await isVisible(subModulesComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(subModulesComponentsPage.table);
    subModulesUpdatePage = await subModulesComponentsPage.goToCreateSubModules();
    await subModulesUpdatePage.enterData();
    expect(await isVisible(subModulesUpdatePage.saveButton)).to.be.false;

    expect(await subModulesComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(subModulesComponentsPage.table);
    await waitUntilCount(subModulesComponentsPage.records, beforeRecordsCount + 1);
    expect(await subModulesComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await subModulesComponentsPage.deleteSubModules();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(subModulesComponentsPage.records, beforeRecordsCount);
      expect(await subModulesComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(subModulesComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
