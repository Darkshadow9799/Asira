import dayjs from 'dayjs';

export interface IModules {
  id?: number;
  mName?: string | null;
  mDesc?: string | null;
  mSpeId?: number;
  mProjId?: number;
  mCreatedDate?: string | null;
  mModifiedDate?: string | null;
  mSmNum?: number | null;
  mDueDate?: string | null;
  mFinishedDate?: string | null;
  mCompleted?: boolean | null;
}

export const defaultValue: Readonly<IModules> = {
  mCompleted: false,
};
