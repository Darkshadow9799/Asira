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
  projAdminIdInput: ElementFinder = element(by.css('input#project-projAdminId'));
  orgIdInput: ElementFinder = element(by.css('input#project-orgId'));
  projMemberNumberInput: ElementFinder = element(by.css('input#project-projMemberNumber'));

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

  async setProjAdminIdInput(projAdminId) {
    await this.projAdminIdInput.sendKeys(projAdminId);
  }

  async getProjAdminIdInput() {
    return this.projAdminIdInput.getAttribute('value');
  }

  async setOrgIdInput(orgId) {
    await this.orgIdInput.sendKeys(orgId);
  }

  async getOrgIdInput() {
    return this.orgIdInput.getAttribute('value');
  }

  async setProjMemberNumberInput(projMemberNumber) {
    await this.projMemberNumberInput.sendKeys(projMemberNumber);
  }

  async getProjMemberNumberInput() {
    return this.projMemberNumberInput.getAttribute('value');
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
    await this.setProjAdminIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setOrgIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setProjMemberNumberInput('5');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
