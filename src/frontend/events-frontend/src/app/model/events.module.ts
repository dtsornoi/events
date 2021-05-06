import {User} from './user.module';

export class Events {
  id?: number;
  title?: string;
  description?: string;
  startingFrom?: Date;
  endingOn?: Date;
  user?: User;
  subscribedUsers?: User [];
}
