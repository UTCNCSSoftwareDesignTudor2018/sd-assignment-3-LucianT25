import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReaderComponent } from './reader/reader.component';
import { WriterComponent } from './writer/writer.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: 'reader', 
    component: ReaderComponent
  },
  {
    path: 'writer/:user',
    component: WriterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
