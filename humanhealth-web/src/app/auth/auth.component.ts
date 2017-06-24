import { Component } from '@angular/core';
import { Injector } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from 'ng2-ui-auth';

import { AbstractComponent } from '../abstract.component';

@Component({
  selector: 'hh-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent extends AbstractComponent {
  constructor(private auth: AuthService, private router: Router) {
    super();
  }

  loginWith(provider: string): void {
    this.auth.authenticate(provider)
      .subscribe({
        error: (err: any) => console.log(err),
        complete: () => this.continue()
      });
  }

  private continue(): void {
    this.router.navigateByUrl('main');
  }
}