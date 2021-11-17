import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ComComponentsPage from './com.page-object';
import ComUpdatePage from './com-update.page-object';
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

describe('Com e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let comComponentsPage: ComComponentsPage;
  let comUpdatePage: ComUpdatePage;
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
    comComponentsPage = new ComComponentsPage();
    comComponentsPage = await comComponentsPage.goToPage(navBarPage);
  });

  it('should load Coms', async () => {
    expect(await comComponentsPage.title.getText()).to.match(/Coms/);
    expect(await comComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Coms', async () => {
    const beforeRecordsCount = (await isVisible(comComponentsPage.noRecords)) ? 0 : await getRecordsCount(comComponentsPage.table);
    comUpdatePage = await comComponentsPage.goToCreateCom();
    await comUpdatePage.enterData();
    expect(await isVisible(comUpdatePage.saveButton)).to.be.false;

    expect(await comComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(comComponentsPage.table);
    await waitUntilCount(comComponentsPage.records, beforeRecordsCount + 1);
    expect(await comComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await comComponentsPage.deleteCom();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(comComponentsPage.records, beforeRecordsCount);
      expect(await comComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(comComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
