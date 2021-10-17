import dayjs from 'dayjs';

export interface ITag {
  id?: number;
  tagTitle?: string | null;
  tagDesc?: string | null;
  tagCreatedById?: number | null;
  tagCreatedByName?: string | null;
  tagCreatedDate?: string | null;
  tagModifiedDate?: string | null;
  projId?: number;
}

export const defaultValue: Readonly<ITag> = {};
