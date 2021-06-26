import {Component, OnInit} from '@angular/core';
import {EmailService} from '../../service/email.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  msg: string = '';
  isSuccessful: boolean = false;
  hasFailed: boolean = false;
  email: string;

  constructor(
    private emailService: EmailService
  ) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    return this.emailService.sendMail(this.email).subscribe(
      data => {
        this.hasFailed = false;
        this.isSuccessful = true;
        this.msg = 'Mail with new password sent.';
      },
      error => {
        this.isSuccessful = false;
        this.hasFailed = true;
        this.msg = error.error.message;
      }
    );
  }
}
