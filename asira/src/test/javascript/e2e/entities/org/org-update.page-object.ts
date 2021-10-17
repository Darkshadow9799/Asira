import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class OrgUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.org.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  orgNameInput: ElementFinder = element(by.css('input#org-orgName'));
  orgEmailIdInput: ElementFinder = element(by.css('input#org-orgEmailId'));
  orgPhoneNumInput: ElementFinder = element(by.css('input#org-orgPhoneNum'));
  createdDateInput: ElementFinder = element(by.css('input#org-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#org-modifiedDate'));
  orgProjNumInput: ElementFinder = element(by.css('input#org-orgProjNum'));
  orgMembersNumInput: ElementFinder = element(by.css('input#org-orgMembersNum'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setOrgNameInput(orgName) {
    await this.orgNameInput.sendKeys(orgName);
  }

  async getOrgNameInput() {
    return this.orgNameInput.getAttribute('value');
  }

  async setOrgEmailIdInput(orgEmailId) {
    await this.orgEmailIdInput.sendKeys(orgEmailId);
  }

  async getOrgEmailIdInput() {
    return this.orgEmailIdInput.getAttribute('value');
  }

  async setOrgPhoneNumInput(orgPhoneNum) {
    await this.orgPhoneNumInput.sendKeys(orgPhoneNum);
  }

  async getOrgPhoneNumInput() {
    return this.orgPhoneNumInput.getAttribute('value');
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

  async setOrgProjNumInput(orgProjNum) {
    await this.orgProjNumInput.sendKeys(orgProjNum);
  }

  async getOrgProjNumInput() {
    return this.orgProjNumInput.getAttribute('value');
  }

  async setOrgMembersNumInput(orgMembersNum) {
    await this.orgMembersNumInput.sendKeys(orgMembersNum);
  }

  async getOrgMembersNumInput() {
    return this.orgMembersNumInput.getAttribute('value');
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
    await this.setOrgNameInput('orgName');
    await waitUntilDisplayed(this.saveButton);
    await this.setOrgEmailIdInput('orgEmailId');
    await waitUntilDisplayed(this.saveButton);
    await this.setOrgPhoneNumInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setOrgProjNumInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setOrgMembersNumInput('5');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
