import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {HomeComponent} from './components/home/home.component';
import {CreateNewEventComponent} from './components/create-new-event/create-new-event.component';
import {EventDescriptionComponent} from './components/event-description/event-description.component';
import {UpdateEventComponent} from './components/update-event/update-event.component';
import {ForgotPasswordComponent} from './components/forgot-password/forgot-password.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomeComponent},
  {path: 'add-new', component: CreateNewEventComponent},
  {path: 'event/:id', component: EventDescriptionComponent},
  {path: 'update/:id', component: UpdateEventComponent},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
