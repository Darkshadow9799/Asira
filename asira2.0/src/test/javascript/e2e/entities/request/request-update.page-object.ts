import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class RequestUpdatePage {
  pageTitle: ElementFinder = element(by.id('asiraApp.request.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  rFromInput: ElementFinder = element(by.css('input#request-rFrom'));
  rToInput: ElementFinder = element(by.css('input#request-rTo'));
  rPendingStateInput: ElementFinder = element(by.css('input#request-rPendingState'));
  rAcceptedInput: ElementFinder = element(by.css('input#request-rAccepted'));
  rRejectedInput: ElementFinder = element(by.css('input#request-rRejected'));
  speNotifiedInput: ElementFinder = element(by.css('input#request-speNotified'));
  orgNotifiedInput: ElementFinder = element(by.css('input#request-orgNotified'));
  createdDateInput: ElementFinder = element(by.css('input#request-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#request-modifiedDate'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setRFromInput(rFrom) {
    await this.rFromInput.sendKeys(rFrom);
  }

  async getRFromInput() {
    return this.rFromInput.getAttribute('value');
  }

  async setRToInput(rTo) {
    await this.rToInput.sendKeys(rTo);
  }

  async getRToInput() {
    return this.rToInput.getAttribute('value');
  }

  getRPendingStateInput() {
    return this.rPendingStateInput;
  }
  getRAcceptedInput() {
    return this.rAcceptedInput;
  }
  getRRejectedInput() {
    return this.rRejectedInput;
  }
  getSpeNotifiedInput() {
    return this.speNotifiedInput;
  }
  getOrgNotifiedInput() {
    return this.orgNotifiedInput;
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
    await this.setRFromInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setRToInput('5');
    await waitUntilDisplayed(this.saveButton);
    const selectedRPendingState = await this.getRPendingStateInput().isSelected();
    if (selectedRPendingState) {
      await this.getRPendingStateInput().click();
    } else {
      await this.getRPendingStateInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedRAccepted = await this.getRAcceptedInput().isSelected();
    if (selectedRAccepted) {
      await this.getRAcceptedInput().click();
    } else {
      await this.getRAcceptedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedRRejected = await this.getRRejectedInput().isSelected();
    if (selectedRRejected) {
      await this.getRRejectedInput().click();
    } else {
      await this.getRRejectedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedSpeNotified = await this.getSpeNotifiedInput().isSelected();
    if (selectedSpeNotified) {
      await this.getSpeNotifiedInput().click();
    } else {
      await this.getSpeNotifiedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedOrgNotified = await this.getOrgNotifiedInput().isSelected();
    if (selectedOrgNotified) {
      await this.getOrgNotifiedInput().click();
    } else {
      await this.getOrgNotifiedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
