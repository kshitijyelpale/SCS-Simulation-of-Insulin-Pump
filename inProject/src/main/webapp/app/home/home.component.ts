import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription, Observable } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';

import { Account } from 'app/core/user/account.model';
import 'chartjs-plugin-streaming';
import 'chartjs-plugin-annotation';
import { AuthServerProvider } from '../core/auth/auth-jwt.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Bsl } from './bsl';
import { SERVER_API_URL } from '../app.constants';
import { map } from 'rxjs/operators';
import * as $ from 'jquery';

var abc;

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})

export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  identity: any;
  authSubscription?: Subscription;
  HeadersForBsl: any;
  bsl:  Observable<Bsl>;
  
  //batterybar: any;

  datasets: any[] = [{
    
    data: [],

    label: 'Blood Sugar level',

    //lineTension: 0,

    //borderDash: [8, 4],

    fill: false

  }/*, {

    data: [],

    label: 'Dataset 2'

  }, {

    data: [],

    label: 'Dataset 3'

  }*/

];

  options: any = {
    
    scales: {

      xAxes: [{

        type: 'realtime',

        realtime: {

          onRefresh (chart: any) {
             //console.log("Self thiss abc", abc );
             
            chart.data.datasets.forEach(function (dataset: any) {

              dataset.data.push({

                x: Date.now(),

                y: abc.numnum()

              });

            });
        //     chart.config.options.scales.yAxes[0].ticks.min  =
        //     chart.helpers.niceNum(20);
        // chart.config.options.scales.yAxes[0].ticks.max =
        //     chart.helpers.niceNum(180);
          },

          delay: 2000

        }

      }]

    }, 
    annotation: {
      annotations: [{
          type: 'line',
          mode: 'horizontal',
          scaleID: 'y-axis-0',
          value: '150',
          borderColor: 'tomato',
          borderWidth: 2
      },
      {
        type: 'line',
        mode: 'horizontal',
        scaleID: 'y-axis-0',
        value: '90',
        borderColor: 'blue',
        borderWidth: 2
    }],
      //drawTime: "afterDraw" // (default)
  }};

  constructor( private http: HttpClient, private accountService: AccountService, private loginModalService: LoginModalService,  private authServer: AuthServerProvider) {
    var self = this;
    abc = this;
    this.numnum = this.numnum.bind(this);
    //console.log("self cons",self);
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
     //console.log("serv",this.authServer.getToken());
     
    //this.batterybar = document.getElementById("batterybar")?.offsetWidth;
    //console.log("width",this.batterybar);
  }

  isAuthenticated(): boolean {
  
    return this.accountService.isAuthenticated();
    
  }

  // numnum = (): number => {
  //  return 100;
  // }

  numnum(): number{
     return 100;
    }

  login(): void {
    this.loginModalService.open();
  }

  refillInsulin(): void {
    console.log("BSL Testing", this.bsl);
  }

  getBsl(): Observable<Bsl> {
    console.log("this",this);
    console.log("account", this.account);
     this.HeadersForBsl = new HttpHeaders();
    if (this.authServer.getToken()) {
      this.HeadersForBsl.append('Content-Type', 'application/json');
      this.HeadersForBsl.append('Authorization', 'Bearer ' + this.authServer.getToken());
    }
    console.log("header", this.HeadersForBsl);
    const options = {
      headers: this.HeadersForBsl
    };

    this.bsl = this.http.get<Bsl>(SERVER_API_URL + 'api/bsl/' + '4', options).pipe(
      map((res: Bsl) => {
        //this.bsl = JSON.parse(res);
        return res;
      })
    );
     
    //console.log("BSLLLLL", this.bsl);
    return this.bsl;

    
  }
  
  
  drainbattery() : any {
    
    setInterval(() => this.drainbatterytimer(),2000);
  }

  drainbatterytimer() :any {
   
    let w = $("#batterybar").width();
        if(w>50){
      $("#batterybar").width(w-20);
    
  }

    //console.log("Widthhh",w);
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  
}
