import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { Injector } from '@angular/core';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LanguageService } from './language/language.service';

import { AppComponent } from './app.component';
import { ROUTES, ROUTER_PROVIDERS } from './app-routing.module';
import { AuthService } from './auth/auth.service';
import { MainComponent } from './hh-main.component';
import { PublicComponent } from './hh-public.component';

import 'hammerjs';

@NgModule({
  providers: [
    AuthService,
    LanguageService,
    ROUTER_PROVIDERS  
  ],
  declarations: [
    AppComponent,    
    MainComponent,
    PublicComponent
  ],
  imports: [    
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(ROUTES, { useHash: true })
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  static injector: Injector;
  constructor(injector: Injector) {
    AppModule.injector = injector;
  }
}
