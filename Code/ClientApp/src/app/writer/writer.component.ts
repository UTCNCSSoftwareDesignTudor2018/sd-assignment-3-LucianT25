import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {HttpHeaders} from '@angular/common/http';
import { Response } from '@angular/http';

@Component({
  selector: 'app-writer',
  templateUrl: './writer.component.html',
  styleUrls: ['./writer.component.css']
})
export class WriterComponent{

  model: any = {};
  username: string;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private httpClient : HttpClient){
      this.route.params.subscribe(res => this.username = res.user);
  }
  
  postArticle() {
    let headers = new HttpHeaders();
    headers.set('Accept', 'text/plain').set('Content-Type', 'application/json');
    
    var info = JSON.stringify({title:this.model.title, abstr:this.model.abstract, body:this.model.body, author: null});
    console.log(info);
    const req = this.httpClient.post('http://localhost:8080/'+this.username+'/post', info, {headers : headers})
      .subscribe(
        err => {
          console.log("Error occured");
          console.log(err);
        }
      );
  }
}

