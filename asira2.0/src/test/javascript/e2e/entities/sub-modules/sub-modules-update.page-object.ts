import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class SubModulesUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.subModules.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  smNameInput: ElementFinder = element(by.css('input#sub-modules-smName'));
  smDescInput: ElementFinder = element(by.css('input#sub-modules-smDesc'));
  smCreatedDateInput: ElementFinder = element(by.css('input#sub-modules-smCreatedDate'));
  smModifiedDateInput: ElementFinder = element(by.css('input#sub-modules-smModifiedDate'));
  smDueDateInput: ElementFinder = element(by.css('input#sub-modules-smDueDate'));
  smFinishedDateInput: ElementFinder = element(by.css('input#sub-modules-smFinishedDate'));
  smCompletedInput: ElementFinder = element(by.css('input#sub-modules-smCompleted'));
  speNameSmTagIdInput: ElementFinder = element(by.css('input#sub-modules-speNameSmTagId'));
  smLoggedTimeInput: ElementFinder = element(by.css('input#sub-modules-smLoggedTime'));
  tagSelect: ElementFinder = element(by.css('select#sub-modules-tag'));
  speSelect: ElementFinder = element(by.css('select#sub-modules-spe'));
  moduleSelect: ElementFinder = element(by.css('select#sub-modules-module'));

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
  async setSpeNameSmTagIdInput(speNameSmTagId) {
    await this.speNameSmTagIdInput.sendKeys(speNameSmTagId);
  }

  async getSpeNameSmTagIdInput() {
    return this.speNameSmTagIdInput.getAttribute('value');
  }

  async setSmLoggedTimeInput(smLoggedTime) {
    await this.smLoggedTimeInput.sendKeys(smLoggedTime);
  }

  async getSmLoggedTimeInput() {
    return this.smLoggedTimeInput.getAttribute('value');
  }

  async tagSelectLastOption() {
    await this.tagSelect.all(by.tagName('option')).last().click();
  }

  async tagSelectOption(option) {
    await this.tagSelect.sendKeys(option);
  }

  getTagSelect() {
    return this.tagSelect;
  }

  async getTagSelectedOption() {
    return this.tagSelect.element(by.css('option:checked')).getText();
  }

  async speSelectLastOption() {
    await this.speSelect.all(by.tagName('option')).last().click();
  }

  async speSelectOption(option) {
    await this.speSelect.sendKeys(option);
  }

  getSpeSelect() {
    return this.speSelect;
  }

  async getSpeSelectedOption() {
    return this.speSelect.element(by.css('option:checked')).getText();
  }

  async moduleSelectLastOption() {
    await this.moduleSelect.all(by.tagName('option')).last().click();
  }

  async moduleSelectOption(option) {
    await this.moduleSelect.sendKeys(option);
  }

  getModuleSelect() {
    return this.moduleSelect;
  }

  async getModuleSelectedOption() {
    return this.moduleSelect.element(by.css('option:checked')).getText();
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
    await this.setSpeNameSmTagIdInput('speNameSmTagId');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmLoggedTimeInput('5');
    await this.tagSelectLastOption();
    await this.speSelectLastOption();
    await this.moduleSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
