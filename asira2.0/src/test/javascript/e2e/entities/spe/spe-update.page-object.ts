import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class SpeUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.spe.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  speFirstNameInput: ElementFinder = element(by.css('input#spe-speFirstName'));
  speLastNameInput: ElementFinder = element(by.css('input#spe-speLastName'));
  speEmailIdInput: ElementFinder = element(by.css('input#spe-speEmailId'));
  spePhoneNumberInput: ElementFinder = element(by.css('input#spe-spePhoneNumber'));
  speOrgVerifiedInput: ElementFinder = element(by.css('input#spe-speOrgVerified'));
  createdDateInput: ElementFinder = element(by.css('input#spe-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#spe-modifiedDate'));
  orgSelect: ElementFinder = element(by.css('select#spe-org'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSpeFirstNameInput(speFirstName) {
    await this.speFirstNameInput.sendKeys(speFirstName);
  }

  async getSpeFirstNameInput() {
    return this.speFirstNameInput.getAttribute('value');
  }

  async setSpeLastNameInput(speLastName) {
    await this.speLastNameInput.sendKeys(speLastName);
  }

  async getSpeLastNameInput() {
    return this.speLastNameInput.getAttribute('value');
  }

  async setSpeEmailIdInput(speEmailId) {
    await this.speEmailIdInput.sendKeys(speEmailId);
  }

  async getSpeEmailIdInput() {
    return this.speEmailIdInput.getAttribute('value');
  }

  async setSpePhoneNumberInput(spePhoneNumber) {
    await this.spePhoneNumberInput.sendKeys(spePhoneNumber);
  }

  async getSpePhoneNumberInput() {
    return this.spePhoneNumberInput.getAttribute('value');
  }

  getSpeOrgVerifiedInput() {
    return this.speOrgVerifiedInput;
  }
  async setCreatedDateInput(createdDate) {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput() {
    return this.createdDateInput.getAttribute('value');
  }

  async setModifiedDateInput(modifiedDate) {
    await this.modifiedDateInput.sendKeys(modifiedDate);
  }

  async getModifiedDateInput() {
    return this.modifiedDateInput.getAttribute('value');
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
    await this.setSpeFirstNameInput('speFirstName');
    await waitUntilDisplayed(this.saveButton);
    await this.setSpeLastNameInput('speLastName');
    await waitUntilDisplayed(this.saveButton);
    await this.setSpeEmailIdInput('speEmailId');
    await waitUntilDisplayed(this.saveButton);
    await this.setSpePhoneNumberInput('5');
    await waitUntilDisplayed(this.saveButton);
    const selectedSpeOrgVerified = await this.getSpeOrgVerifiedInput().isSelected();
    if (selectedSpeOrgVerified) {
      await this.getSpeOrgVerifiedInput().click();
    } else {
      await this.getSpeOrgVerifiedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await this.orgSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
