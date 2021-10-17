import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SpeComponentsPage from './spe.page-object';
import SpeUpdatePage from './spe-update.page-object';
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

describe('Spe e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let speComponentsPage: SpeComponentsPage;
  let speUpdatePage: SpeUpdatePage;
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
    speComponentsPage = new SpeComponentsPage();
    speComponentsPage = await speComponentsPage.goToPage(navBarPage);
  });

  it('should load Spes', async () => {
    expect(await speComponentsPage.title.getText()).to.match(/Spes/);
    expect(await speComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Spes', async () => {
    const beforeRecordsCount = (await isVisible(speComponentsPage.noRecords)) ? 0 : await getRecordsCount(speComponentsPage.table);
    speUpdatePage = await speComponentsPage.goToCreateSpe();
    await speUpdatePage.enterData();
    expect(await isVisible(speUpdatePage.saveButton)).to.be.false;

    expect(await speComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(speComponentsPage.table);
    await waitUntilCount(speComponentsPage.records, beforeRecordsCount + 1);
    expect(await speComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await speComponentsPage.deleteSpe();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(speComponentsPage.records, beforeRecordsCount);
      expect(await speComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(speComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
