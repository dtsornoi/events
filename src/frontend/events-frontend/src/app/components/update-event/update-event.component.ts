import { Component, OnInit } from '@angular/core';
import {ContentService} from '../../service/content.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../service/token-storage.service';
import {Events} from '../../model/events.module';

@Component({
  selector: 'app-update-event',
  templateUrl: './update-event.component.html',
  styleUrls: ['./update-event.component.css']
})
export class UpdateEventComponent implements OnInit {

  oldEvent: Events = {};
  newEvent: Events = {};
  isSuccessful: boolean = false;

  constructor(
    private service: ContentService,
    private route: ActivatedRoute,
    private router: Router,
    private token: TokenStorageService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.service.getOneEvent(id).subscribe(data => {
      this.oldEvent = data;
    })
  }

  update(){

  }

  onSubmit() {
    return false;
  }
}
