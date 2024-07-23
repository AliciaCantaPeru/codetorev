import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import * as SockJs from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { Observable, Subject } from 'rxjs';
import { SocketCloseOutDto, SocketOutDto } from '@core/models/socket.model';
@Injectable({
  providedIn: 'root',
})
export class SocketService {
  private stompClient;
  private respuestaSocket = new Subject<string>();

  constructor() {}

  connect(out: SocketOutDto) {
    this.disconnet();
    const socket = new SockJs(environment.url + '/iot-web-socket');
    this.stompClient = Stomp.over(socket);
    this.stompClient.debug = (e) => {
      console.log(e);
    };

    this.stompClient.connect(
      {},
      (frame) => {
        this.subscription(out);
        this.sendDataSubscribe(out);
      },
      (errorMessage: string): void => {
        console.log('connect_socket_reporte_encuesta', errorMessage);
      }
    );
    return this.stompClient;
  }

  private subscription(out: SocketOutDto) {
    const canal: string = out.topic;
    this.stompClient.subscribe(canal, (message) => {
      this.respuestaSocket.next(message.body);
    });
  }

  public sendDataSubscribe(out: SocketOutDto) {
    const json = JSON.stringify(out);
    this.stompClient.send('/app/subscribe', {}, json);
  }
  public sendDataUnsubscribe(outClose: SocketCloseOutDto) {
    const json = JSON.stringify(outClose);
    if (
      this.stompClient !== null &&
      this.stompClient !== undefined &&
      this.stompClient.connected
    ) {
      this.stompClient.send('/app/unsubscribe', {}, json);
    }
  }
  disconnet() {
    if (
      this.stompClient !== null &&
      this.stompClient !== undefined &&
      this.stompClient.connected
    ) {
      this.stompClient.disconnect();
    }
  }

  getData(): Observable<string> {
    return this.respuestaSocket.asObservable();
  }
}
