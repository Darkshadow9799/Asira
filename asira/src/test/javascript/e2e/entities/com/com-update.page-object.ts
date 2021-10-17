import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ComUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.com.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  cCreatedByIdInput: ElementFinder = element(by.css('input#com-cCreatedById'));
  cCreatedByNameInput: ElementFinder = element(by.css('input#com-cCreatedByName'));
  cDescInput: ElementFinder = element(by.css('input#com-cDesc'));
  cCreatedDateInput: ElementFinder = element(by.css('input#com-cCreatedDate'));
  cModifiedDateInput: ElementFinder = element(by.css('input#com-cModifiedDate'));
  smIdInput: ElementFinder = element(by.css('input#com-smId'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setCCreatedByIdInput(cCreatedById) {
    await this.cCreatedByIdInput.sendKeys(cCreatedById);
  }

  async getCCreatedByIdInput() {
    return this.cCreatedByIdInput.getAttribute('value');
  }

  async setCCreatedByNameInput(cCreatedByName) {
    await this.cCreatedByNameInput.sendKeys(cCreatedByName);
  }

  async getCCreatedByNameInput() {
    return this.cCreatedByNameInput.getAttribute('value');
  }

  async setCDescInput(cDesc) {
    await this.cDescInput.sendKeys(cDesc);
  }

  async getCDescInput() {
    return this.cDescInput.getAttribute('value');
  }

  async setCCreatedDateInput(cCreatedDate) {
    await this.cCreatedDateInput.sendKeys(cCreatedDate);
  }

  async getCCreatedDateInput() {
    return this.cCreatedDateInput.getAttribute('value');
  }

  async setCModifiedDateInput(cModifiedDate) {
    await this.cModifiedDateInput.sendKeys(cModifiedDate);
  }

  async getCModifiedDateInput() {
    return this.cModifiedDateInput.getAttribute('value');
  }

  async setSmIdInput(smId) {
    await this.smIdInput.sendKeys(smId);
  }

  async getSmIdInput() {
    return this.smIdInput.getAttribute('value');
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setCCreatedByIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setCCreatedByNameInput('cCreatedByName');
    await waitUntilDisplayed(this.saveButton);
    await this.setCDescInput('cDesc');
    await waitUntilDisplayed(this.saveButton);
    await this.setCCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setCModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmIdInput('5');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
