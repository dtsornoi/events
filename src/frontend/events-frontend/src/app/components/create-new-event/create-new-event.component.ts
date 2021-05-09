import {Component, OnInit} from '@angular/core';
import {Events} from '../../model/events.module';
import {TokenStorageService} from '../../service/token-storage.service';
import {ContentService} from '../../service/content.service';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../model/user.module';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-create-new-event',
  templateUrl: './create-new-event.component.html',
  styleUrls: ['./create-new-event.component.css']
})
export class CreateNewEventComponent implements OnInit {
  isSuccessful = false;
  currentUser: User;

  events: Events = {};

  constructor(
    private token: TokenStorageService,
    private service: ContentService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    if (this.token.getToken()) {
      this.userService.getUser(this.token.getUser().id).subscribe(data => {
        this.currentUser = data;
      });
    }
  }

  onSubmit() {
    this.events.user = this.currentUser;
    this.service.saveEvent(this.events).subscribe(
      result => {
        this.isSuccessful = true;
      }
    );
  }
}
