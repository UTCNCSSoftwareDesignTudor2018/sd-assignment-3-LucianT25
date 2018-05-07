import { Component } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent {
  private serverUrl = 'http://localhost:8080/socket'
  private title = 'NewsHub';
  private stompClient;

  constructor(){
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/topic/list", (message) => {
        if(message.body) {
          //$(".chat").append("<div class='message'>"+message.body+"</div>")
          console.log(message.body);
        }
      });
    });
  }

  requestArticle(articleName){
    this.stompClient.send("/app/get/article" , {}, articleName);
    $('#input').val('');
  }

  populateList() {
    this.stompClient.send("/app/get/list", {});
    
  }

}