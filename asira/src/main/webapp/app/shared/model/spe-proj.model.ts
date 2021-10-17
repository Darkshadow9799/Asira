export interface ISpeProj {
  id?: number;
  projId?: number;
  speId?: number | null;
  orgId?: number | null;
}

export const defaultValue: Readonly<ISpeProj> = {};
