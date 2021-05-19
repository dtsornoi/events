import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContentService {

  private API_URL = '/api/events';


  constructor(private http: HttpClient) {
  }

  getAllEvents(): Observable<any> {
    return this.http.get(`${this.API_URL}/all-events`);
  }

  saveEvent(item): Observable<any> {
    return this.http.post(`${this.API_URL}/save-event`, item);
  }

  getOneEvent(id): Observable<any> {
    return this.http.get(`${this.API_URL}/event/${id}`);
  }

  delete(id): Observable<any> {
    return this.http.delete(`${this.API_URL}/event/${id}`);
  }

  updateEvent(id, event): Observable<any> {
    return this.http.put(`${this.API_URL}/event/${id}`, event);
  }

  addSubscriber(id, user): Observable<any> {
    return this.http.post(`${this.API_URL}/subscribe/${id}`, user);
  }

  deleteSubscriber(id, user): Observable<any> {
    return this.http.post(`${this.API_URL}/unsubscribe/${id}`, user);
  }
}
