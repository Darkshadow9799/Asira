import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SpeProjMSmComponentsPage from './spe-proj-m-sm.page-object';
import SpeProjMSmUpdatePage from './spe-proj-m-sm-update.page-object';
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

describe('SpeProjMSm e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let speProjMSmComponentsPage: SpeProjMSmComponentsPage;
  let speProjMSmUpdatePage: SpeProjMSmUpdatePage;
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
    speProjMSmComponentsPage = new SpeProjMSmComponentsPage();
    speProjMSmComponentsPage = await speProjMSmComponentsPage.goToPage(navBarPage);
  });

  it('should load SpeProjMSms', async () => {
    expect(await speProjMSmComponentsPage.title.getText()).to.match(/Spe Proj M Sms/);
    expect(await speProjMSmComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete SpeProjMSms', async () => {
    const beforeRecordsCount = (await isVisible(speProjMSmComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(speProjMSmComponentsPage.table);
    speProjMSmUpdatePage = await speProjMSmComponentsPage.goToCreateSpeProjMSm();
    await speProjMSmUpdatePage.enterData();
    expect(await isVisible(speProjMSmUpdatePage.saveButton)).to.be.false;

    expect(await speProjMSmComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(speProjMSmComponentsPage.table);
    await waitUntilCount(speProjMSmComponentsPage.records, beforeRecordsCount + 1);
    expect(await speProjMSmComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await speProjMSmComponentsPage.deleteSpeProjMSm();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(speProjMSmComponentsPage.records, beforeRecordsCount);
      expect(await speProjMSmComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(speProjMSmComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
