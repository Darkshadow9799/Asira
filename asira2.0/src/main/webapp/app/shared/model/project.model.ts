import dayjs from 'dayjs';
import { IOrg } from 'app/shared/model/org.model';
import { ISpe } from 'app/shared/model/spe.model';

export interface IProject {
  id?: number;
  projName?: string;
  projCreatedDate?: string | null;
  projModifiedDate?: string | null;
  projMemberNumber?: number | null;
  org?: IOrg | null;
  spe?: ISpe | null;
}

export const defaultValue: Readonly<IProject> = {};
