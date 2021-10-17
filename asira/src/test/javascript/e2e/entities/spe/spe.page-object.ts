import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import SpeUpdatePage from './spe-update.page-object';

const expect = chai.expect;
export class SpeDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('asiraApp.spe.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-spe'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class SpeComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('spe-heading'));
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
    await navBarPage.getEntityPage('spe');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateSpe() {
    await this.createButton.click();
    return new SpeUpdatePage();
  }

  async deleteSpe() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const speDeleteDialog = new SpeDeleteDialog();
    await waitUntilDisplayed(speDeleteDialog.deleteModal);
    expect(await speDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/asiraApp.spe.delete.question/);
    await speDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(speDeleteDialog.deleteModal);

    expect(await isVisible(speDeleteDialog.deleteModal)).to.be.false;
  }
}
