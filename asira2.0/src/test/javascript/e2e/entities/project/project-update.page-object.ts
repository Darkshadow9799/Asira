import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ProjectUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.project.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  projNameInput: ElementFinder = element(by.css('input#project-projName'));
  projCreatedDateInput: ElementFinder = element(by.css('input#project-projCreatedDate'));
  projModifiedDateInput: ElementFinder = element(by.css('input#project-projModifiedDate'));
  projMemberNumberInput: ElementFinder = element(by.css('input#project-projMemberNumber'));
  orgSelect: ElementFinder = element(by.css('select#project-org'));
  speSelect: ElementFinder = element(by.css('select#project-spe'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setProjNameInput(projName) {
    await this.projNameInput.sendKeys(projName);
  }

  async getProjNameInput() {
    return this.projNameInput.getAttribute('value');
  }

  async setProjCreatedDateInput(projCreatedDate) {
    await this.projCreatedDateInput.sendKeys(projCreatedDate);
  }

  async getProjCreatedDateInput() {
    return this.projCreatedDateInput.getAttribute('value');
  }

  async setProjModifiedDateInput(projModifiedDate) {
    await this.projModifiedDateInput.sendKeys(projModifiedDate);
  }

  async getProjModifiedDateInput() {
    return this.projModifiedDateInput.getAttribute('value');
  }

  async setProjMemberNumberInput(projMemberNumber) {
    await this.projMemberNumberInput.sendKeys(projMemberNumber);
  }

  async getProjMemberNumberInput() {
    return this.projMemberNumberInput.getAttribute('value');
  }

  async orgSelectLastOption() {
    await this.orgSelect.all(by.tagName('option')).last().click();
  }

  async orgSelectOption(option) {
    await this.orgSelect.sendKeys(option);
  }

  getOrgSelect() {
    return this.orgSelect;
  }

  async getOrgSelectedOption() {
    return this.orgSelect.element(by.css('option:checked')).getText();
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
    await this.setProjNameInput('projName');
    await waitUntilDisplayed(this.saveButton);
    await this.setProjCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setProjModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setProjMemberNumberInput('5');
    await this.orgSelectLastOption();
    await this.speSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
