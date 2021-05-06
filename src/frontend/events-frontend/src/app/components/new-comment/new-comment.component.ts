import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from '../../service/comment.service';
import {Router} from '@angular/router';
import {Comment} from '../../model/comment.moule';
import {TokenStorageService} from '../../service/token-storage.service';
import {User} from '../../model/user.module';
import {UserService} from '../../service/user.service';
import {ContentService} from '../../service/content.service';
import {Events} from '../../model/events.module';

@Component({
  selector: 'app-new-comment',
  templateUrl: './new-comment.component.html',
  styleUrls: ['./new-comment.component.css']
})
export class NewCommentComponent implements OnInit {

  @Input() eventId: number;

  comment: Comment = {};
  isSuccessful: boolean = false;
  isLoggedIn: boolean = false;
  currentUser: User = {};
  currentEvent: Events = {};

  constructor(
    private service: CommentService,
    private router: Router,
    private token: TokenStorageService,
    private userService: UserService,
    private contentService: ContentService
  ) { }

  ngOnInit(): void {
    if (this.token.getToken()){
      this.isLoggedIn = true;
      this.userService.getUser(this.token.getUser().id).subscribe(data => {
        this.currentUser = data;
      });

      this.contentService.getOneEvent(this.eventId).subscribe(
        data => {
          this.currentEvent = data;
        }
      );
    }

    console.log(this.eventId)
  }

  onSubmit() {
    this.comment.user = this.currentUser;
    this.comment.event = this.currentEvent;
    let currDate = Date.now();
    this.comment.postedOn = currDate;

    this.service.addNewComment(this.comment).subscribe(
      data => {
        window.location.reload();
        }
    );
  }
}
