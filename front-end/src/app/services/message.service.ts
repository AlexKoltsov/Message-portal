import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Message} from '../models/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  backendUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Message[]>(this.backendUrl + `/messages`);
  }

  getByUserId(id: number) {
    return this.http.get<Message[]>(this.backendUrl + `/messages/` + id);
  }

  delete(id: number) {
    return this.http.delete(this.backendUrl + `/messages/` + id);
  }
}
