import dayjs from 'dayjs';
import { ITag } from 'app/shared/model/tag.model';
import { ISpe } from 'app/shared/model/spe.model';
import { IModules } from 'app/shared/model/modules.model';

export interface ISubModules {
  id?: number;
  smName?: string | null;
  smDesc?: string | null;
  smCreatedDate?: string | null;
  smModifiedDate?: string | null;
  smDueDate?: string | null;
  smFinishedDate?: string | null;
  smCompleted?: boolean | null;
  speNameSmTagId?: string | null;
  smLoggedTime?: number | null;
  tag?: ITag | null;
  spe?: ISpe | null;
  module?: IModules | null;
}

export const defaultValue: Readonly<ISubModules> = {
  smCompleted: false,
};
