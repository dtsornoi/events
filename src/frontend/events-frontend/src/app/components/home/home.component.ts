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
  searchText: string = '';

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
  converter(text:string, start: number, end:number){
    if (text != null){
      return text.substr(start, end);
    }
  }

  ifNullShowUndefined(text: string): string {
    if (text != null){
      return text;
    }

    return undefined;
  }
}
