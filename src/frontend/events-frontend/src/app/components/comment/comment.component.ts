import { Component, OnInit } from '@angular/core';
import {CommentService} from '../../service/comment.service';
import {Comment} from '../../model/comment.moule';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  comments: Comment[] = [];

  constructor(
    private service: CommentService
  ) { }

  ngOnInit(): void {
    this.service.getAllComments().subscribe(
      data => {
        this.comments = data;
      }
    )
  }

}
