import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CreateNewEventComponent } from './components/create-new-event/create-new-event.component';
import {authInterceptorProviders} from './helper/auth.interceptor';
import { EventDescriptionComponent } from './components/event-description/event-description.component';
import { UpdateEventComponent } from './components/update-event/update-event.component';
import { CommentComponent } from './components/comment/comment.component';
import { NewCommentComponent } from './components/new-comment/new-comment.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NavigationComponent,
    CreateNewEventComponent,
    EventDescriptionComponent,
    UpdateEventComponent,
    CommentComponent,
    NewCommentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [
    authInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
