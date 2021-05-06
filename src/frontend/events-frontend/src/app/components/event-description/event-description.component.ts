import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ContentService} from '../../service/content.service';
import {Events} from '../../model/events.module';
import {TokenStorageService} from '../../service/token-storage.service';
import {CommentService} from '../../service/comment.service';

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

  constructor(
    private service: ContentService,
    private route: ActivatedRoute,
    private token: TokenStorageService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    if (this.token.getToken()){
      this.isLoggedIn = true;
      this.roles = this.token.getUser().roles;
    }

    const id = this.route.snapshot.paramMap.get('id');
    this.service.getOneEvent(id).subscribe(data => {

      this.event = data;
    })
  }

  hasRole(authority){
    for(let role of this.roles){
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
    this.router.navigate([`update/${id}`])
  }
}
