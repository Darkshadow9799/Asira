import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import SpeProjMSmUpdatePage from './spe-proj-m-sm-update.page-object';

const expect = chai.expect;
export class SpeProjMSmDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('asiraApp.speProjMSm.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-speProjMSm'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class SpeProjMSmComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('spe-proj-m-sm-heading'));
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
    await navBarPage.getEntityPage('spe-proj-m-sm');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateSpeProjMSm() {
    await this.createButton.click();
    return new SpeProjMSmUpdatePage();
  }

  async deleteSpeProjMSm() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const speProjMSmDeleteDialog = new SpeProjMSmDeleteDialog();
    await waitUntilDisplayed(speProjMSmDeleteDialog.deleteModal);
    expect(await speProjMSmDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/asiraApp.speProjMSm.delete.question/);
    await speProjMSmDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(speProjMSmDeleteDialog.deleteModal);

    expect(await isVisible(speProjMSmDeleteDialog.deleteModal)).to.be.false;
  }
}
