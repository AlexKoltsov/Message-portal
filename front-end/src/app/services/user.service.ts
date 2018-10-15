import {Injectable} from '@angular/core';
import {User} from '../models/user';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  backendUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<User[]>(this.backendUrl + `/users`);
  }

  getById(id: number) {
    return this.http.get(this.backendUrl + `/users/` + id);
  }

  register(user: User) {
    return this.http.post(this.backendUrl + `/users`, user);
  }

  update(user: User) {
    return this.http.put(this.backendUrl + `/users/` + user.id, user);
  }

  delete(id: number) {
    return this.http.delete(this.backendUrl + `/users/` + id);
  }
}
