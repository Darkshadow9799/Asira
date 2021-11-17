import dayjs from 'dayjs';
import { IOrg } from 'app/shared/model/org.model';

export interface ISpe {
  id?: number;
  speFirstName?: string | null;
  speLastName?: string | null;
  speEmailId?: string;
  spePhoneNumber?: number | null;
  speOrgVerified?: boolean | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  org?: IOrg | null;
}

export const defaultValue: Readonly<ISpe> = {
  speOrgVerified: false,
};
