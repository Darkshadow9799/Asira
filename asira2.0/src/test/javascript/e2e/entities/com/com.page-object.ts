import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import ComUpdatePage from './com-update.page-object';

const expect = chai.expect;
export class ComDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('asiraApp.com.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-com'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class ComComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('com-heading'));
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
    await navBarPage.getEntityPage('com');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateCom() {
    await this.createButton.click();
    return new ComUpdatePage();
  }

  async deleteCom() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const comDeleteDialog = new ComDeleteDialog();
    await waitUntilDisplayed(comDeleteDialog.deleteModal);
    expect(await comDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/asiraApp.com.delete.question/);
    await comDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(comDeleteDialog.deleteModal);

    expect(await isVisible(comDeleteDialog.deleteModal)).to.be.false;
  }
}
