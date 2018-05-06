import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { WriterComponent } from './writer/writer.component';
import { ReaderComponent } from './reader/reader.component';


@NgModule({
  declarations: [
    AppComponent,
    WriterComponent,
    ReaderComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
