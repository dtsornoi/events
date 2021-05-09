import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_URL = 'http://localhost:8080/api/events';

@Injectable({
  providedIn: 'root'
})
export class ContentService {

  constructor(private http: HttpClient) {
  }

  getAllEvents(): Observable<any> {
    return this.http.get(`${API_URL}/all-events`);
  }

  saveEvent(item): Observable<any> {
    return this.http.post(`${API_URL}/save-event`, item);
  }

  getOneEvent(id): Observable<any> {
    return this.http.get(`${API_URL}/event/${id}`);
  }

  delete(id): Observable<any> {
    return this.http.delete(`${API_URL}/event/${id}`);
  }

  updateEvent(id, event): Observable<any> {
    return this.http.put(`${API_URL}/event/${id}`, event);
  }

  addSubscriber(id, event): Observable<any> {
    return this.http.post(`${API_URL}/subscribe/${id}`, event);
  }

  deleteSubscriber(id, event): Observable<any> {
    return this.http.post(`${API_URL}/unsubscribe/${id}`, event);
  }
}
