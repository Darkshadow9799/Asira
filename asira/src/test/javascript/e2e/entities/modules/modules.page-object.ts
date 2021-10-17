import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import ModulesUpdatePage from './modules-update.page-object';

const expect = chai.expect;
export class ModulesDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('asiraApp.modules.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-modules'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class ModulesComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('modules-heading'));
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
    await navBarPage.getEntityPage('modules');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateModules() {
    await this.createButton.click();
    return new ModulesUpdatePage();
  }

  async deleteModules() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const modulesDeleteDialog = new ModulesDeleteDialog();
    await waitUntilDisplayed(modulesDeleteDialog.deleteModal);
    expect(await modulesDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/asiraApp.modules.delete.question/);
    await modulesDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(modulesDeleteDialog.deleteModal);

    expect(await isVisible(modulesDeleteDialog.deleteModal)).to.be.false;
  }
}
