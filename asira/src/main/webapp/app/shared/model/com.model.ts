import dayjs from 'dayjs';

export interface ICom {
  id?: number;
  cCreatedById?: number;
  cCreatedByName?: string | null;
  cDesc?: string | null;
  cCreatedDate?: string | null;
  cModifiedDate?: string | null;
  smId?: number;
}

export const defaultValue: Readonly<ICom> = {};
