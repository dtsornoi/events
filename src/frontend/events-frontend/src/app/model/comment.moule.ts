import {User} from './user.module';
import {Events} from './events.module';

export class Comment {
  id?: number;
  title?: string;
  comment?: string;
  postedOn?: number;
  user?: User;
  event?: Events;
}
