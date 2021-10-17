import dayjs from 'dayjs';

export interface ISpe {
  id?: number;
  speFirstName?: string | null;
  speLastName?: string | null;
  speEmailId?: string;
  spePhoneNumber?: number | null;
  speOrgId?: number | null;
  speOrgVerified?: boolean | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
}

export const defaultValue: Readonly<ISpe> = {
  speOrgVerified: false,
};
