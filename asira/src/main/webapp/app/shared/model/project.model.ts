import dayjs from 'dayjs';

export interface IProject {
  id?: number;
  projName?: string;
  projCreatedDate?: string | null;
  projModifiedDate?: string | null;
  projAdminId?: number | null;
  orgId?: number | null;
  projMemberNumber?: number | null;
}

export const defaultValue: Readonly<IProject> = {};
