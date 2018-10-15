import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  backendUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    return this.http.post<any>(this.backendUrl + `/login`, {login: username, password: password})
      .pipe(tap(user => localStorage.setItem('currentUser', JSON.stringify(user))));
  }

  logout(username: string) {
    console.log('logout ' + username);
    // remove user from local storage to log user out
    return this.http.post<any>(this.backendUrl + `/logout`, {login: username})
      .pipe(tap(user => {
        console.log('logouted');
        localStorage.removeItem('currentUser');
      }));
  }

  changePassword(username: string, newPassword: string) {
    return this.http.post<any>(this.backendUrl + `/changePassword`, {login: username, password: newPassword});
  }
}
