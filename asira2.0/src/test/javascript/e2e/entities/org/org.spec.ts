import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import OrgComponentsPage from './org.page-object';
import OrgUpdatePage from './org-update.page-object';
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

describe('Org e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orgComponentsPage: OrgComponentsPage;
  let orgUpdatePage: OrgUpdatePage;
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
    orgComponentsPage = new OrgComponentsPage();
    orgComponentsPage = await orgComponentsPage.goToPage(navBarPage);
  });

  it('should load Orgs', async () => {
    expect(await orgComponentsPage.title.getText()).to.match(/Orgs/);
    expect(await orgComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Orgs', async () => {
    const beforeRecordsCount = (await isVisible(orgComponentsPage.noRecords)) ? 0 : await getRecordsCount(orgComponentsPage.table);
    orgUpdatePage = await orgComponentsPage.goToCreateOrg();
    await orgUpdatePage.enterData();
    expect(await isVisible(orgUpdatePage.saveButton)).to.be.false;

    expect(await orgComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(orgComponentsPage.table);
    await waitUntilCount(orgComponentsPage.records, beforeRecordsCount + 1);
    expect(await orgComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await orgComponentsPage.deleteOrg();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(orgComponentsPage.records, beforeRecordsCount);
      expect(await orgComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(orgComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
