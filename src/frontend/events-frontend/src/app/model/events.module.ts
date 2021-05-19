import {User} from './user.module';

export class Events {
  id?: string;
  title?: string;
  description?: string;
  startingFrom?: Date;
  endingOn?: Date;
  user?: User;
  subscribedUsers?: User [];
}
