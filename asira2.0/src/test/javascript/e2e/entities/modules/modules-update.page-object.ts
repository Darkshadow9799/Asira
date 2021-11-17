import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ModulesUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.modules.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  mNameInput: ElementFinder = element(by.css('input#modules-mName'));
  mDescInput: ElementFinder = element(by.css('input#modules-mDesc'));
  mCreatedDateInput: ElementFinder = element(by.css('input#modules-mCreatedDate'));
  mModifiedDateInput: ElementFinder = element(by.css('input#modules-mModifiedDate'));
  mSmNumInput: ElementFinder = element(by.css('input#modules-mSmNum'));
  mDueDateInput: ElementFinder = element(by.css('input#modules-mDueDate'));
  mFinishedDateInput: ElementFinder = element(by.css('input#modules-mFinishedDate'));
  mCompletedInput: ElementFinder = element(by.css('input#modules-mCompleted'));
  speSelect: ElementFinder = element(by.css('select#modules-spe'));
  projectSelect: ElementFinder = element(by.css('select#modules-project'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setMNameInput(mName) {
    await this.mNameInput.sendKeys(mName);
  }

  async getMNameInput() {
    return this.mNameInput.getAttribute('value');
  }

  async setMDescInput(mDesc) {
    await this.mDescInput.sendKeys(mDesc);
  }

  async getMDescInput() {
    return this.mDescInput.getAttribute('value');
  }

  async setMCreatedDateInput(mCreatedDate) {
    await this.mCreatedDateInput.sendKeys(mCreatedDate);
  }

  async getMCreatedDateInput() {
    return this.mCreatedDateInput.getAttribute('value');
  }

  async setMModifiedDateInput(mModifiedDate) {
    await this.mModifiedDateInput.sendKeys(mModifiedDate);
  }

  async getMModifiedDateInput() {
    return this.mModifiedDateInput.getAttribute('value');
  }

  async setMSmNumInput(mSmNum) {
    await this.mSmNumInput.sendKeys(mSmNum);
  }

  async getMSmNumInput() {
    return this.mSmNumInput.getAttribute('value');
  }

  async setMDueDateInput(mDueDate) {
    await this.mDueDateInput.sendKeys(mDueDate);
  }

  async getMDueDateInput() {
    return this.mDueDateInput.getAttribute('value');
  }

  async setMFinishedDateInput(mFinishedDate) {
    await this.mFinishedDateInput.sendKeys(mFinishedDate);
  }

  async getMFinishedDateInput() {
    return this.mFinishedDateInput.getAttribute('value');
  }

  getMCompletedInput() {
    return this.mCompletedInput;
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

  async projectSelectLastOption() {
    await this.projectSelect.all(by.tagName('option')).last().click();
  }

  async projectSelectOption(option) {
    await this.projectSelect.sendKeys(option);
  }

  getProjectSelect() {
    return this.projectSelect;
  }

  async getProjectSelectedOption() {
    return this.projectSelect.element(by.css('option:checked')).getText();
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
    await this.setMNameInput('mName');
    await waitUntilDisplayed(this.saveButton);
    await this.setMDescInput('mDesc');
    await waitUntilDisplayed(this.saveButton);
    await this.setMCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setMModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setMSmNumInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setMDueDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setMFinishedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    const selectedMCompleted = await this.getMCompletedInput().isSelected();
    if (selectedMCompleted) {
      await this.getMCompletedInput().click();
    } else {
      await this.getMCompletedInput().click();
    }
    await this.speSelectLastOption();
    await this.projectSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
