import dayjs from 'dayjs';
import { ISpe } from 'app/shared/model/spe.model';
import { IProject } from 'app/shared/model/project.model';

export interface IModules {
  id?: number;
  mName?: string | null;
  mDesc?: string | null;
  mCreatedDate?: string | null;
  mModifiedDate?: string | null;
  mSmNum?: number | null;
  mDueDate?: string | null;
  mFinishedDate?: string | null;
  mCompleted?: boolean | null;
  spe?: ISpe | null;
  project?: IProject | null;
}

export const defaultValue: Readonly<IModules> = {
  mCompleted: false,
};
