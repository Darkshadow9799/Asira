import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class SpeProjMSmUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.speProjMSm.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  speIdInput: ElementFinder = element(by.css('input#spe-proj-m-sm-speId'));
  projIdInput: ElementFinder = element(by.css('input#spe-proj-m-sm-projId'));
  mIdInput: ElementFinder = element(by.css('input#spe-proj-m-sm-mId'));
  smIdInput: ElementFinder = element(by.css('input#spe-proj-m-sm-smId'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSpeIdInput(speId) {
    await this.speIdInput.sendKeys(speId);
  }

  async getSpeIdInput() {
    return this.speIdInput.getAttribute('value');
  }

  async setProjIdInput(projId) {
    await this.projIdInput.sendKeys(projId);
  }

  async getProjIdInput() {
    return this.projIdInput.getAttribute('value');
  }

  async setMIdInput(mId) {
    await this.mIdInput.sendKeys(mId);
  }

  async getMIdInput() {
    return this.mIdInput.getAttribute('value');
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
    await this.setSpeIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setProjIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setMIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSmIdInput('5');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
