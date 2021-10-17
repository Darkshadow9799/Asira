import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class SpeProjUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.speProj.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  projIdInput: ElementFinder = element(by.css('input#spe-proj-projId'));
  speIdInput: ElementFinder = element(by.css('input#spe-proj-speId'));
  orgIdInput: ElementFinder = element(by.css('input#spe-proj-orgId'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setProjIdInput(projId) {
    await this.projIdInput.sendKeys(projId);
  }

  async getProjIdInput() {
    return this.projIdInput.getAttribute('value');
  }

  async setSpeIdInput(speId) {
    await this.speIdInput.sendKeys(speId);
  }

  async getSpeIdInput() {
    return this.speIdInput.getAttribute('value');
  }

  async setOrgIdInput(orgId) {
    await this.orgIdInput.sendKeys(orgId);
  }

  async getOrgIdInput() {
    return this.orgIdInput.getAttribute('value');
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
    await this.setProjIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSpeIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setOrgIdInput('5');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
