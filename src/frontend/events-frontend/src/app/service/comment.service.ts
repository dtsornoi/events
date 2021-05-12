import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


const COMMENT_URL = '/api/comments';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(
    private http: HttpClient
  ) {
  }

  getAllComments(): Observable<any> {
    return this.http.get(COMMENT_URL);
  }

  addNewComment(comment): Observable<any> {
    return this.http.post(COMMENT_URL, comment);
  }

}
