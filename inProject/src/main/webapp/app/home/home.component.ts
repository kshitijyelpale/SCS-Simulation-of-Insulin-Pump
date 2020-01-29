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

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
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
              chart.data.datasets.forEach(function(dataset: any) {
                dataset.data.push({
                  x: Date.now(),

                  y: Math.floor(Math.random() * (200 - 60 + 1)) + 100
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
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private authServer: AuthServerProvider,
    private http: HttpClient,
    private bsl: Bsl
  ) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));

    //this.batterybar = document.getElementById("batterybar")?.offsetWidth;
    //console.log("width",this.batterybar);
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  getBsl(): Observable<void> {
    var HeadersForBsl = new HttpHeaders();

    if (this.authServer.getToken()) {
      HeadersForBsl.append('Content-Type', 'application/json');
      HeadersForBsl.append('Authorization', 'Bearer ' + this.authServer.getToken());
    }

    const options = {
      headers: HeadersForBsl
    };

    return this.http.get(SERVER_API_URL + 'api/bsl/' + '4', options).pipe(
      map((res: Response) => {
        this.bsl = JSON.parse(res);
      })
    );
  }
}
