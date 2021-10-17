import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import OrgUpdatePage from './org-update.page-object';

const expect = chai.expect;
export class OrgDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('asiraApp.org.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-org'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class OrgComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('org-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage) {
    await navBarPage.getEntityPage('org');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateOrg() {
    await this.createButton.click();
    return new OrgUpdatePage();
  }

  async deleteOrg() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const orgDeleteDialog = new OrgDeleteDialog();
    await waitUntilDisplayed(orgDeleteDialog.deleteModal);
    expect(await orgDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/asiraApp.org.delete.question/);
    await orgDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(orgDeleteDialog.deleteModal);

    expect(await isVisible(orgDeleteDialog.deleteModal)).to.be.false;
  }
}
