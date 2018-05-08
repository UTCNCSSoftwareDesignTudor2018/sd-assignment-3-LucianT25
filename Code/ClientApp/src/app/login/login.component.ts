import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {HttpHeaders} from '@angular/common/http';
import { Response } from '@angular/http';

interface Destination {
  destination: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  returnUrl: string = "/writer";

  constructor(
      private route: ActivatedRoute,
      private router: Router,
      private httpClient : HttpClient) {}

  ngOnInit() {
  }

  login() {
    let headers = new HttpHeaders();
    headers.set('Accept', 'text/plain').set('Content-Type', 'application/json');

    
    var info = JSON.stringify({username:this.model.username, password:this.model.password});
    console.log(info);
    const req = this.httpClient.post<Destination>('http://localhost:8080/login', info, {headers : headers})
      .subscribe(
        res => {
          console.log(res as Destination);
            this.returnUrl = res.destination;
            this.router.navigate([this.returnUrl]);
        },
        err => {
          console.log("Error occured");
          console.log(err);
        }
      );
  }

}
