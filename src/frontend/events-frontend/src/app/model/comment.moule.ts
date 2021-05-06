import {User} from './user.module';

export class Comment {
  id?: number;
  title?: string;
  comment?: string;
  postedOn?: number;
  user?: User
}
