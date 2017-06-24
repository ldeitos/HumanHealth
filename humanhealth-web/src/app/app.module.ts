import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { Injector } from '@angular/core';

import { Ng2UiAuthModule } from 'ng2-ui-auth';

import { LanguageService } from './language/language.service';

import { AppComponent } from './app.component';
import { AppRoutingModule, ROUTER_PROVIDERS } from './app-routing.module';
import { AuthComponent } from './auth/auth.component';
import { AuthConfig } from './auth/auth.config';
import { MainComponent } from './hh-main.component';
import { PublicComponent } from './hh-public.component';

import 'hammerjs';

@NgModule({
  providers: [
    LanguageService,
    ROUTER_PROVIDERS
  ],
  declarations: [
    AppComponent,
    AuthComponent,
    MainComponent,
    PublicComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpModule,
    Ng2UiAuthModule.forRoot(AuthConfig),
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  static injector: Injector;
  constructor(injector: Injector) {
    AppModule.injector = injector;
  }
}
