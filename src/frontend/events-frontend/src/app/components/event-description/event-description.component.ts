import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ContentService} from '../../service/content.service';
import {Events} from '../../model/events.module';
import {TokenStorageService} from '../../service/token-storage.service';
import {User} from '../../model/user.module';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-event-description',
  templateUrl: './event-description.component.html',
  styleUrls: ['./event-description.component.css']
})
export class EventDescriptionComponent implements OnInit {
  event: Events = {};
  isLoggedIn: boolean = false;
  roles: string[] = [];
  comment: Comment[];
  isSubscribed: boolean = false;
  currentUser: User = {};
  users: User [] = [];
  eventId;

  constructor(
    private service: ContentService,
    private route: ActivatedRoute,
    private token: TokenStorageService,
    private router: Router,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    if (this.token.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.token.getUser().roles;
      this.userService.getUser(this.token.getUser().id).subscribe(
        data => {
          this.currentUser = data;
        }
      );
    }

    this.eventId = this.route.snapshot.paramMap.get('id');
    this.service.getOneEvent(this.eventId).subscribe(data => {
      this.event = data;
      this.users = this.event.subscribedUsers;

      if (this.users.length != 0) {
        for (let user of this.users) {
          if (user.id === this.currentUser.id) {
            this.isSubscribed = true;
          }
        }
      }
    });
  }

  hasRole(authority) {
    for (let role of this.roles) {
      return role === authority;
    }
  }

  delete(id) {
    if (confirm('Are you sure you want to delete?')) {
      this.service.delete(id).subscribe(data => {
        this.router.navigate(['home']);
      });
    }
  }

  updateLink(id: number) {
    this.router.navigate([`update/${id}`]);
  }

  subscribe() {
    this.service.addSubscriber(this.event.id, this.currentUser).subscribe(
      data => {
        this.isSubscribed = true;
        window.location.reload();
      }
    );
  }

  unSubscribe() {
    this.service.deleteSubscriber(this.event.id, this.currentUser).subscribe(
      data => {
        this.isSubscribed = false;
        window.location.reload();
      }
    );
  }
}
