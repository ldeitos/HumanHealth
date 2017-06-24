import { Component } from '@angular/core';
import { Injector } from '@angular/core';
import {  Router } from '@angular/router';

import { AbstractComponent } from './abstract.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent extends AbstractComponent {  
  constructor(private router: Router){
    super();
  };

  goMain():void {
    this.router.navigateByUrl('main');
  }
}
