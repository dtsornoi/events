import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private USER_URL = '/api/users';

  constructor(private http: HttpClient) {
  }

  getUser(id): Observable<any> {
    return this.http.get(this.USER_URL + id);
  }
}
