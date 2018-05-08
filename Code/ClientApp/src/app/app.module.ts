import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { WriterComponent } from './writer/writer.component';
import { ReaderComponent } from './reader/reader.component';
import { AppRoutingModule } from './/app-routing.module';
import { StompService } from 'ng2-stomp-service';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    WriterComponent,
    ReaderComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
