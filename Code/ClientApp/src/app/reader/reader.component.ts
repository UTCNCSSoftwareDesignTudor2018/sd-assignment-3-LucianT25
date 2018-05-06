import { Component, OnInit } from '@angular/core';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import $ from 'jquery';

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent implements OnInit {


  private serverUrl = 'http://localhost:8080/socket'
  private title = 'Articles';
  private stompClient;

  constructor(){
    this.initializeWebSocketConnection();
  }

  ngOnInit() {
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/topic/articlesList", (message) => {
        if(message.body) {
          console.log(message.body);
          that.buildList(message);
        }
      });
    });
  }

  buildList(articlesList) {

  }
  getArticles(message){
    this.stompClient.send("/articles/all" , {}, message);
    $('#input').val('');
  }
}
