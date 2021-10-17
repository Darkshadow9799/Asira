import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import SpeProjUpdatePage from './spe-proj-update.page-object';

const expect = chai.expect;
export class SpeProjDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('asiraApp.speProj.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-speProj'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class SpeProjComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('spe-proj-heading'));
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
    await navBarPage.getEntityPage('spe-proj');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateSpeProj() {
    await this.createButton.click();
    return new SpeProjUpdatePage();
  }

  async deleteSpeProj() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const speProjDeleteDialog = new SpeProjDeleteDialog();
    await waitUntilDisplayed(speProjDeleteDialog.deleteModal);
    expect(await speProjDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/asiraApp.speProj.delete.question/);
    await speProjDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(speProjDeleteDialog.deleteModal);

    expect(await isVisible(speProjDeleteDialog.deleteModal)).to.be.false;
  }
}
