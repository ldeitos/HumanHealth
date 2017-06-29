import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuardComponent }   from './auth/auth-guard.component';
import { MainComponent }   from './hh-main.component';
import { PublicComponent }   from './hh-public.component';

export const ROUTER_PROVIDERS = [
    AuthGuardComponent
];

export const ROUTES: Routes = [
  { path: '', redirectTo: '/public', pathMatch: 'full' },  
  { path: 'public', component: PublicComponent },
  { path: 'main', component: MainComponent, canActivate: [AuthGuardComponent] },  
  { path: '**', redirectTo: '' }
];