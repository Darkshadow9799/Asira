import dayjs from 'dayjs';

export interface ISubModules {
  id?: number;
  smName?: string | null;
  smDesc?: string | null;
  smMId?: number;
  smSpeId?: number;
  smCreatedDate?: string | null;
  smModifiedDate?: string | null;
  smDueDate?: string | null;
  smFinishedDate?: string | null;
  smCompleted?: boolean | null;
  smTagID?: number | null;
  speIdSmTagId?: number | null;
  smLoggedTime?: number | null;
}

export const defaultValue: Readonly<ISubModules> = {
  smCompleted: false,
};
