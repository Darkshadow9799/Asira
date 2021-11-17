import dayjs from 'dayjs';
import { ISubModules } from 'app/shared/model/sub-modules.model';

export interface ICom {
  id?: number;
  cCreatedById?: number;
  cCreatedByName?: string | null;
  cDesc?: string | null;
  cCreatedDate?: string | null;
  cModifiedDate?: string | null;
  subModules?: ISubModules | null;
}

export const defaultValue: Readonly<ICom> = {};
