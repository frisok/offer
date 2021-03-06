import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './login/login.component';
import {OfferComponent} from './offer/offer.component';
import {HeaderComponent} from './header/header.component';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {ReactiveFormsModule} from "@angular/forms";
import {NgxWebstorageModule} from "ngx-webstorage";
import {AuthInterceptor} from "./service/auth-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OfferComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    HttpClientModule,
    MatProgressBarModule,
    ReactiveFormsModule,
    NgxWebstorageModule.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
