import { Component, OnInit } from '@angular/core';
import {EmailService} from '../../service/email.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  email: string;

  constructor(
    private emailService: EmailService
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    return this.emailService.sendMail(this.email).subscribe(
      data => {
        alert("mail sent!");
      }
    );
  }
}
