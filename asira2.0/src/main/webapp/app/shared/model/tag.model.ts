import dayjs from 'dayjs';
import { IProject } from 'app/shared/model/project.model';

export interface ITag {
  id?: number;
  tagTitle?: string | null;
  tagDesc?: string | null;
  tagCreatedById?: number | null;
  tagCreatedByName?: string | null;
  tagCreatedDate?: string | null;
  tagModifiedDate?: string | null;
  project?: IProject | null;
}

export const defaultValue: Readonly<ITag> = {};
