import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import SubModulesUpdatePage from './sub-modules-update.page-object';

const expect = chai.expect;
export class SubModulesDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('asiraApp.subModules.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-subModules'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class SubModulesComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('sub-modules-heading'));
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
    await navBarPage.getEntityPage('sub-modules');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateSubModules() {
    await this.createButton.click();
    return new SubModulesUpdatePage();
  }

  async deleteSubModules() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const subModulesDeleteDialog = new SubModulesDeleteDialog();
    await waitUntilDisplayed(subModulesDeleteDialog.deleteModal);
    expect(await subModulesDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/asiraApp.subModules.delete.question/);
    await subModulesDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(subModulesDeleteDialog.deleteModal);

    expect(await isVisible(subModulesDeleteDialog.deleteModal)).to.be.false;
  }
}
