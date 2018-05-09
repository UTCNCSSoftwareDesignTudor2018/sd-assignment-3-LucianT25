import { Component, Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';
import { HttpClient } from '@angular/common/http';
import {Observable, Subject} from 'rxjs/Rx';
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

@Injectable()
export class ArticleService {
  private articles: Article[] = [];
  
  private articlesSubject = new Subject<Article[]>();
  private _articles$ = this.articlesSubject.asObservable();

  constructor(private httpClient : HttpClient) {

  }

  updateArticleList(list: Article[]) {
    this.articles = list;
    this.articlesSubject.next(this.articles);
  }

  getArticless$() {
    return this._articles$;
  }

  populateList() {
      //this.stompClient.send("/app/get/list", {});
      let headers = new HttpHeaders();
      headers.set('Accept', 'application/json').set('Content-Type', 'application/json');
      const req = this.httpClient.get<Article[]>('http://localhost:8080/article/list', {headers : headers})
        .subscribe(
          res => {
            console.log(res as Article[]);
              this.articles = res as Article[];
  
          },
          err => {
            console.log("Error occured");
            console.log(err);
          }
        );
  }
}

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css'],
  providers: [ArticleService]
})
export class ReaderComponent {
  private serverUrl = 'http://localhost:8080/socket'
  private title = 'NewsHub';
  private stompClient;
  private articleList: Observable<Article[]>;

  constructor(private articleService: ArticleService){
    this.initializeWebSocketConnection();
    this.articleService.populateList();
    this.articleList = articleService.getArticless$();
    console.log(articleService.getArticless$());
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/list", (message) => {
        if(message.body) {
          console.log(message.body as Article[]);
          this.articleService.updateArticleList(message.body as Article[]);
        }
      });
    });
  }

  openArticle(i) {
    document.getElementById('at').innerHTML = this.articleList[i].title+"\n";
    document.getElementById('aa').innerHTML = "By "+this.articleList[i].author.name+"\n\n";
    document.getElementById('abs').innerHTML = '"'+this.articleList[i].abstr+'"\n\n\n';
    document.getElementById('ab').innerHTML = this.articleList[i].body;
  }

}