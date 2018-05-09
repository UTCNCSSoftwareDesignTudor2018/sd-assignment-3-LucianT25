import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {HttpHeaders} from '@angular/common/http';
import { Response } from '@angular/http';

interface Article {
  id:number;
  title:string;
  abstr:string;
  body:string;
  author:Author;
}

interface Author {
  id:number;
  username:string;
  name:string;
}

@Component({
  selector: 'app-writer',
  templateUrl: './writer.component.html',
  styleUrls: ['./writer.component.css']
})
export class WriterComponent{

  model: any = {};
  username: string;
  articleToUpdate: Article;
  
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

  selectUpdate() {
    let headers = new HttpHeaders();
    headers.set('Accept', 'text/plain').set('Content-Type', 'application/json');

    const req = this.httpClient.get<Article>('http://localhost:8080/'+this.username+'/get?articleName='+this.model.updateTitle, {headers : headers})
      .subscribe(
        res => {
          console.log(res as Article);
            this.articleToUpdate = res as Article;
        },
        err => {
          console.log("Error occured");
          console.log(err);
        }
      );

      document.getElementById('title').innerHTML = this.articleToUpdate.title;  
      document.getElementById('abstract').innerHTML = this.articleToUpdate.abstr;  
      document.getElementById('article_body').innerHTML = this.articleToUpdate.body;  
  }

  deleteArticle() {
    let headers = new HttpHeaders();
    headers.set('Accept', 'text/plain').set('Content-Type', 'application/json');

    const req = this.httpClient.post('http://localhost:8080/'+this.username+'/delete', this.model.deleteTitle, {headers : headers})
      .subscribe(
        err => {
          console.log("Error occured");
          console.log(err);
        }
      );

  }
}

