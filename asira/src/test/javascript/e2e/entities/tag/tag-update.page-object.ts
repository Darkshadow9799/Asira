import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class TagUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.tag.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  tagTitleInput: ElementFinder = element(by.css('input#tag-tagTitle'));
  tagDescInput: ElementFinder = element(by.css('input#tag-tagDesc'));
  tagCreatedByIdInput: ElementFinder = element(by.css('input#tag-tagCreatedById'));
  tagCreatedByNameInput: ElementFinder = element(by.css('input#tag-tagCreatedByName'));
  tagCreatedDateInput: ElementFinder = element(by.css('input#tag-tagCreatedDate'));
  tagModifiedDateInput: ElementFinder = element(by.css('input#tag-tagModifiedDate'));
  projIdInput: ElementFinder = element(by.css('input#tag-projId'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setTagTitleInput(tagTitle) {
    await this.tagTitleInput.sendKeys(tagTitle);
  }

  async getTagTitleInput() {
    return this.tagTitleInput.getAttribute('value');
  }

  async setTagDescInput(tagDesc) {
    await this.tagDescInput.sendKeys(tagDesc);
  }

  async getTagDescInput() {
    return this.tagDescInput.getAttribute('value');
  }

  async setTagCreatedByIdInput(tagCreatedById) {
    await this.tagCreatedByIdInput.sendKeys(tagCreatedById);
  }

  async getTagCreatedByIdInput() {
    return this.tagCreatedByIdInput.getAttribute('value');
  }

  async setTagCreatedByNameInput(tagCreatedByName) {
    await this.tagCreatedByNameInput.sendKeys(tagCreatedByName);
  }

  async getTagCreatedByNameInput() {
    return this.tagCreatedByNameInput.getAttribute('value');
  }

  async setTagCreatedDateInput(tagCreatedDate) {
    await this.tagCreatedDateInput.sendKeys(tagCreatedDate);
  }

  async getTagCreatedDateInput() {
    return this.tagCreatedDateInput.getAttribute('value');
  }

  async setTagModifiedDateInput(tagModifiedDate) {
    await this.tagModifiedDateInput.sendKeys(tagModifiedDate);
  }

  async getTagModifiedDateInput() {
    return this.tagModifiedDateInput.getAttribute('value');
  }

  async setProjIdInput(projId) {
    await this.projIdInput.sendKeys(projId);
  }

  async getProjIdInput() {
    return this.projIdInput.getAttribute('value');
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
    await this.setTagTitleInput('tagTitle');
    await waitUntilDisplayed(this.saveButton);
    await this.setTagDescInput('tagDesc');
    await waitUntilDisplayed(this.saveButton);
    await this.setTagCreatedByIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setTagCreatedByNameInput('tagCreatedByName');
    await waitUntilDisplayed(this.saveButton);
    await this.setTagCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setTagModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setProjIdInput('5');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
