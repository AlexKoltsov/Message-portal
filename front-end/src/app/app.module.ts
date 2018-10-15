import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {MainPageComponent} from './main-page/main-page.component';
import {routing} from './app.routing';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthGuard} from './guard/auth.guard';
import {AlertComponent} from './directives/alert.component';
import {AlertService} from './services/alert.service';
import {DataTableModule} from 'angular-6-datatable';
import {MessageService} from './services/message.service';
import {UserService} from './services/user.service';
import {DetailMessageComponent} from './detail-message/detail-message.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ChangePasswordComponent} from './change-password/change-password.component';


@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    MainPageComponent,
    LoginComponent,
    RegisterComponent,
    DetailMessageComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    DataTableModule,
    NgbModule,
    routing
  ],
  entryComponents: [
    DetailMessageComponent,
    ChangePasswordComponent
  ],
  providers: [
    AuthGuard,
    AlertService,
    MessageService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
