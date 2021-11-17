import dayjs from 'dayjs';

export interface IOrg {
  id?: number;
  orgName?: string;
  orgEmailId?: string | null;
  orgPhoneNum?: number | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  orgProjNum?: number | null;
  orgMembersNum?: number | null;
}

export const defaultValue: Readonly<IOrg> = {};
