import { Component } from '@angular/core';
import { Injector } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from './auth/auth.service';
import { AbstractComponent } from './abstract.component';
import { LanguageService } from './language/language.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent extends AbstractComponent {

  constructor(public router: Router, public auth: AuthService) {
    super();
    auth.handleAuthentication();
  };

  login(): void {
    this.auth.login();      
  }

  logout(): void {
    this.auth.logout();      
  } 

  isAuthenticated(): boolean {    
    return this.auth.isAuthenticated();
  }
}
