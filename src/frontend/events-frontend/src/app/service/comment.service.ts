import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private COMMENT_URL = '/api/comments';

  constructor(
    private http: HttpClient
  ) {
  }

  getAllComments(): Observable<any> {
    return this.http.get(this.COMMENT_URL);
  }

  addNewComment(comment): Observable<any> {
    return this.http.post(this.COMMENT_URL, comment);
  }

}
