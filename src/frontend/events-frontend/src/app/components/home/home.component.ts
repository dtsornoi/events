import { Component, OnInit } from '@angular/core';
import {ContentService} from '../../service/content.service';
import {Events} from '../../model/events.module';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  content: Events []= [];

  constructor(private service: ContentService) { }

  ngOnInit(): void {
    this.service.getAllEvents().subscribe(
      data => {
        this.content = data;
      },
      error => {
        this.content = JSON.parse(error.error).message;
      }
    );
  }

}
