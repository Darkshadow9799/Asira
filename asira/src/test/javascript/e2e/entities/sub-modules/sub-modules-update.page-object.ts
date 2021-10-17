import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class SubModulesUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.subModules.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  smNameInput: ElementFinder = element(by.css('input#sub-modules-smName'));
  smDescInput: ElementFinder = element(by.css('input#sub-modules-smDesc'));
  smMIdInput: ElementFinder = element(by.css('input#sub-modules-smMId'));
  smSpeIdInput: ElementFinder = element(by.css('input#sub-modules-smSpeId'));
  smCreatedDateInput: ElementFinder = element(by.css('input#sub-modules-smCreatedDate'));
  smModifiedDateInput: ElementFinder = element(by.css('input#sub-modules-smModifiedDate'));
  smDueDateInput: ElementFinder = element(by.css('input#sub-modules-smDueDate'));
  smFinishedDateInput: ElementFinder = element(by.css('input#sub-modules-smFinishedDate'));
  smCompletedInput: ElementFinder = element(by.css('input#sub-modules-smCompleted'));
  smTagIDInput: ElementFinder = element(by.css('input#sub-modules-smTagID'));
  speIdSmTagIdInput: ElementFinder = element(by.css('input#sub-modules-speIdSmTagId'));
  smLoggedTimeInput: ElementFinder = element(by.css('input#sub-modules-smLoggedTime'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSmNameInput(smName) {
    await this.smNameInput.sendKeys(smName);
  }

  async getSmNameInput() {
    return this.smNameInput.getAttribute('value');
  }

  async setSmDescInput(smDesc) {
    await this.smDescInput.sendKeys(smDesc);
  }

  async getSmDescInput() {
    return this.smDescInput.getAttribute('value');
  }

  async setSmMIdInput(smMId) {
    await this.smMIdInput.sendKeys(smMId);
  }

  async getSmMIdInput() {
    return this.smMIdInput.getAttribute('value');
  }

  async setSmSpeIdInput(smSpeId) {
    await this.smSpeIdInput.sendKeys(smSpeId);
  }

  async getSmSpeIdInput() {
    return this.smSpeIdInput.getAttribute('value');
  }

  async setSmCreatedDateInput(smCreatedDate) {
    await this.smCreatedDateInput.sendKeys(smCreatedDate);
  }

  async getSmCreatedDateInput() {
    return this.smCreatedDateInput.getAttribute('value');
  }

  async setSmModifiedDateInput(smModifiedDate) {
    await this.smModifiedDateInput.sendKeys(smModifiedDate);
  }

  async getSmModifiedDateInput() {
    return this.smModifiedDateInput.getAttribute('value');
  }

  async setSmDueDateInput(smDueDate) {
    await this.smDueDateInput.sendKeys(smDueDate);
  }

  async getSmDueDateInput() {
    return this.smDueDateInput.getAttribute('value');
  }

  async setSmFinishedDateInput(smFinishedDate) {
    await this.smFinishedDateInput.sendKeys(smFinishedDate);
  }

  async getSmFinishedDateInput() {
    return this.smFinishedDateInput.getAttribute('value');
  }

  getSmCompletedInput() {
    return this.smCompletedInput;
  }
  async setSmTagIDInput(smTagID) {
    await this.smTagIDInput.sendKeys(smTagID);
  }

  async getSmTagIDInput() {
    return this.smTagIDInput.getAttribute('value');
  }

  async setSpeIdSmTagIdInput(speIdSmTagId) {
    await this.speIdSmTagIdInput.sendKeys(speIdSmTagId);
  }

  async getSpeIdSmTagIdInput() {
    return this.speIdSmTagIdInput.getAttribute('value');
  }

  async setSmLoggedTimeInput(smLoggedTime) {
    await this.smLoggedTimeInput.sendKeys(smLoggedTime);
  }

  async getSmLoggedTimeInput() {
    return this.smLoggedTimeInput.getAttribute('value');
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
    await this.setSmNameInput('smName');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmDescInput('smDesc');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmMIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmSpeIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmDueDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmFinishedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    const selectedSmCompleted = await this.getSmCompletedInput().isSelected();
    if (selectedSmCompleted) {
      await this.getSmCompletedInput().click();
    } else {
      await this.getSmCompletedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setSmTagIDInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSpeIdSmTagIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmLoggedTimeInput('5');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
