import {User} from './user';
import {DatePipe} from '@angular/common';

export class Message {
  id: number;
  user: User;
  subject: string;
  content: string;
  from: User;
  to: User;
  dateTime: DatePipe;
}
