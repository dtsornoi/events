import {User} from './user.module';

export class Events {
  id?: string;
  title?: string;
  description?: string;
  startingFrom?: string;
  endingOn?: string;
  user?: User;
  subscribedUsers?: User [];
}
