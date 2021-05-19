import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../service/auth.service';
import {User} from '../../model/user.module';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: User = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  isSelected: boolean = false;
  passwordConfirmation = '';

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  checkIfPasswordsMatch(): boolean {
    if (this.form.password.length == 0){
      return false;
    }
    if (this.form.password != this.passwordConfirmation){
      return  true;
    }if (this.form.password === this.passwordConfirmation){
      return  false;
    }
  }

  onSubmit(): void {

    if (this.isSelected) {
      this.form.role = 1;
    }

    this.authService.register(this.form).subscribe(
      data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
