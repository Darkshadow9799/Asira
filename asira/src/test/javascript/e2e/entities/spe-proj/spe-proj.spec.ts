import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SpeProjComponentsPage from './spe-proj.page-object';
import SpeProjUpdatePage from './spe-proj-update.page-object';
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

describe('SpeProj e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let speProjComponentsPage: SpeProjComponentsPage;
  let speProjUpdatePage: SpeProjUpdatePage;
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
    speProjComponentsPage = new SpeProjComponentsPage();
    speProjComponentsPage = await speProjComponentsPage.goToPage(navBarPage);
  });

  it('should load SpeProjs', async () => {
    expect(await speProjComponentsPage.title.getText()).to.match(/Spe Projs/);
    expect(await speProjComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete SpeProjs', async () => {
    const beforeRecordsCount = (await isVisible(speProjComponentsPage.noRecords)) ? 0 : await getRecordsCount(speProjComponentsPage.table);
    speProjUpdatePage = await speProjComponentsPage.goToCreateSpeProj();
    await speProjUpdatePage.enterData();
    expect(await isVisible(speProjUpdatePage.saveButton)).to.be.false;

    expect(await speProjComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(speProjComponentsPage.table);
    await waitUntilCount(speProjComponentsPage.records, beforeRecordsCount + 1);
    expect(await speProjComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await speProjComponentsPage.deleteSpeProj();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(speProjComponentsPage.records, beforeRecordsCount);
      expect(await speProjComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(speProjComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
