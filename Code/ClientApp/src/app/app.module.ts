import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { WriterComponent } from './writer/writer.component';
import { ReaderComponent } from './reader/reader.component';
import { AppRoutingModule } from './/app-routing.module';
import { StompService } from 'ng2-stomp-service';

@NgModule({
  declarations: [
    AppComponent,
    WriterComponent,
    ReaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
