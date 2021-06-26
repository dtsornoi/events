import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private URL = 'http://localhost:8080/api/forgot-password';

  constructor(
    private http: HttpClient
  ) {
  }

  sendMail(email): Observable<any> {
    return this.http.post(`${this.URL}/${email}`, {});
  }
}
