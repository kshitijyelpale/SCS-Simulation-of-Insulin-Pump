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

var homeObject;
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
  public bsl: Bsl[];
  //batterybar: any;

  datasets: any[] = [
    {
      data: [],

      label: 'Blood Sugar level',

      //lineTension: 0,

      //borderDash: [8, 4],

      fill: false
    } /*, {

    data: [],

    label: 'Dataset 2'

  }, {

    data: [],

    label: 'Dataset 3'

  }*/
  ];

  options: any = {
    scales: {
      xAxes: [
        {
          type: 'realtime',

          realtime: {
            onRefresh(chart: any) {
              //   setTimeout(() => {
              //     console.log('hello');
              // }, 2000);
              homeObject.getBsl();

              chart.data.datasets.forEach(function(dataset: any) {
                dataset.data.push({
                  x: Date.now(),

                  y: homeObject.bsl.currentBsl
                });
              });
              chart.config.options.scales.yAxes[0].ticks.min = chart.helpers.niceNum(20);
              chart.config.options.scales.yAxes[0].ticks.max = chart.helpers.niceNum(180);
            },

            delay: 2000
          }
        }
      ]
    },
    annotation: {
      annotations: [
        {
          type: 'line',
          mode: 'horizontal',
          scaleID: 'y-axis-0',
          value: '120',
          borderColor: 'tomato',
          borderWidth: 2
        },
        {
          type: 'line',
          mode: 'horizontal',
          scaleID: 'y-axis-0',
          value: '70',
          borderColor: 'blue',
          borderWidth: 2
        }
      ]
      //drawTime: "afterDraw" // (default)
    }
  };

  constructor(
    private http: HttpClient,
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private authServer: AuthServerProvider
  ) {
    homeObject = this;
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    console.log('serv', this.authServer.getToken());

    //this.batterybar = document.getElementById("batterybar")?.offsetWidth;
    //console.log("width",this.batterybar);
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  rand(): Number {
    return Math.floor(Math.random() * (200 - 100 + 1)) + 100;
  }

  refillInsulin(): void {
    this.HeadersForBsl = new HttpHeaders();
    if (this.authServer.getToken()) {
      this.HeadersForBsl.append('Content-Type', 'application/json');
      this.HeadersForBsl.append('Authorization', 'Bearer ' + this.authServer.getToken());
    }

    const options = {
      headers: this.HeadersForBsl
    };

    this.http.post(SERVER_API_URL + 'api/reservoir/refill/' + this.account.id + '/insulin', '', options).subscribe();
  }

  refillGlucagon(): void {
    this.HeadersForBsl = new HttpHeaders();
    if (this.authServer.getToken()) {
      this.HeadersForBsl.append('Content-Type', 'application/json');
      this.HeadersForBsl.append('Authorization', 'Bearer ' + this.authServer.getToken());
    }

    const options = {
      headers: this.HeadersForBsl
    };

    this.http.post(SERVER_API_URL + 'api/reservoir/refill/' + this.account.id + '/glucagon', '', options).subscribe();
  }

  triggerCarbsChangeEvent(value: String): void {
    console.log('in triggerCarbs');
    var carbs = 0;
    switch (value) {
      case '1':
        carbs = -30;
        break;
      case '2':
        carbs = 100;
        break;
      case '3':
        carbs = 40;
        break;
      default:
        break;
    }
    if (carbs) {
      this.getBslForCarbo(carbs);
    }
  }

  getBsl(): void {
    this.HeadersForBsl = new HttpHeaders();
    if (this.authServer.getToken()) {
      this.HeadersForBsl.append('Content-Type', 'application/json');
      this.HeadersForBsl.append('Authorization', 'Bearer ' + this.authServer.getToken());
    }

    const options = {
      headers: this.HeadersForBsl
    };

    this.http
      .get<Bsl[]>(SERVER_API_URL + 'api/bsl/' + this.account.id, options)
      .pipe(
        map((res: Bsl[]) => {
          //this.bsl = JSON.parse(res);
          return res;
        })
      )
      .subscribe(data => (this.bsl = data));

    console.log('BSL ' + JSON.stringify(this.bsl));

    $('#insulinprogressbar')
      .attr('aria-valuenow', this.bsl.insulinInReservoir * 10 + '%')
      .css('width', this.bsl.insulinInReservoir * 10 + '%');

    $('#glucagonprogressbar')
      .attr('aria-valuenow', this.bsl.glucagonInReservoir * 10 + '%')
      .css('width', this.bsl.glucagonInReservoir * 10 + '%');

    //return this.bsl;
  }

  getBslForCarbo(carbs: number): void {
    console.log('in getBslForCarbo' + carbs);

    this.HeadersForBsl = new HttpHeaders();
    if (this.authServer.getToken()) {
      this.HeadersForBsl.append('Content-Type', 'application/json');
      this.HeadersForBsl.append('Authorization', 'Bearer ' + this.authServer.getToken());
    }

    const options = {
      headers: this.HeadersForBsl
    };

    this.http
      .get<Bsl[]>(SERVER_API_URL + 'api/bslCarbs/' + this.account.id + '/' + carbs, options)
      .pipe(
        map((res: Bsl[]) => {
          //this.bsl = JSON.parse(res);
          return res;
        })
      )
      .subscribe(data => (this.bsl = data));

    console.log('BSL ' + JSON.stringify(this.bsl));

    //return this.bsl;
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}
