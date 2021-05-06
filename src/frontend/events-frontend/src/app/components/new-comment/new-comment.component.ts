import { Component, OnInit } from '@angular/core';
import {CommentService} from '../../service/comment.service';
import {Router} from '@angular/router';
import {Comment} from '../../model/comment.moule';
import {TokenStorageService} from '../../service/token-storage.service';
import {User} from '../../model/user.module';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-new-comment',
  templateUrl: './new-comment.component.html',
  styleUrls: ['./new-comment.component.css']
})
export class NewCommentComponent implements OnInit {

  comment: Comment = {};
  isSuccessful: boolean = false;
  isLoggedIn: boolean = false;
  currentUser: User = {};

  constructor(
    private service: CommentService,
    private router: Router,
    private token: TokenStorageService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    if (this.token.getToken()){
      this.isLoggedIn = true;
      this.userService.getUser(this.token.getUser().id).subscribe(data => {
        this.currentUser = data;
      });
    }
  }

  onSubmit() {
    this.comment.user = this.currentUser;

    let currDate = Date.now();
    this.comment.postedOn = currDate;

    this.service.addNewComment(this.comment).subscribe(
      data => {
        window.location.reload();
        }
    );
  }
}
