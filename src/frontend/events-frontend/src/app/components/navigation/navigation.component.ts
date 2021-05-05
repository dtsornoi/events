import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../../service/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  currentUser: any;
  isLoggedIn = false;
  roles: string[] = [];

  constructor(
    private token: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.token.getToken()){
      this.isLoggedIn = true;
      this.roles = this.token.getUser().roles;
      this.currentUser = this.token.getUser();
    }

  }

  hasRole(authority){
    for(let role of this.roles){
      return role === authority;
    }
  }

  logout(){
    this.token.signOut();
    window.location.reload();
  }

}
