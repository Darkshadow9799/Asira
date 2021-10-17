import dayjs from 'dayjs';

export interface IRequest {
  id?: number;
  rFrom?: number | null;
  rTo?: number | null;
  rPendingState?: boolean | null;
  rAccepted?: boolean | null;
  rRejected?: boolean | null;
  speNotified?: boolean | null;
  orgNotified?: boolean | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
}

export const defaultValue: Readonly<IRequest> = {
  rPendingState: false,
  rAccepted: false,
  rRejected: false,
  speNotified: false,
  orgNotified: false,
};
