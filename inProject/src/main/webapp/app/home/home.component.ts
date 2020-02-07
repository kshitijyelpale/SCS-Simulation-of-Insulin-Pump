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
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material/bottom-sheet';
import * as $ from 'jquery';

var homeObject;

var alertMessage : any;
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

      label: ' Current Blood Sugar level',
      type : 'line',
      //borderColor: 'rgba(67, 156, 70, 0.1)',

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
            duration: 25000,
            ttl: 60000,
            refresh: 1000,
            delay: 1000,
            pause: false,
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
                 
              if(homeObject.bsl.currentBsl > 120 && homeObject.bsl.currentBsl < 130){
               
                homeObject.openBottomSheet();
              }
              // chart.config.options.scales.yAxes[0].ticks.min = chart.helpers.niceNum(40);
              // chart.config.options.scales.yAxes[0].ticks.max = chart.helpers.niceNum(180);
            },

           
          },
          scaleLabel: {
            display: true,
            labelString: 'Time'
          }
        }
      ],
      yAxes: [{
				type: 'linear',
        display: true,
        ticks: {
          suggestedMin: 50,
          suggestedMax: 150,
        }, 
				scaleLabel: {
					display: true,
					labelString: 'Blood Sugar Levels mg/dL'
				}
			}]

    },
    tooltips: {
			mode: 'nearest',
			intersect: false
		},
		// hover: {
		// 	mode: 'nearest',
		// 	intersect: false
		// },
    annotation: {
      annotations: [
        {
          type: 'line',
          mode: 'horizontal',
          scaleID: 'y-axis-0',
          value: '120',
          borderColor: 'blue',
          borderWidth: 2,
          label: {
            backgroundColor: "blue",
            content: "Hyper Level",
            enabled: true
          }
        },
        {
          type: 'line',
          mode: 'horizontal',
          scaleID: 'y-axis-0',
          value: '70',
          borderColor: 'orange',
          borderWidth: 2,
          label: {
            backgroundColor: "orange",
            content: "Hypo Level",
            enabled: true
          }
        }
      ]
      //drawTime: "afterDraw" // (default)
    }
  };
    
  public chartColors: any[] = [
    { // first color
      borderColor: 'rgba(67, 156, 70, 1)',
      pointBackgroundColor: 'rgba(67, 156, 70, 1)',
      pointBorderColor: 'rgba(67, 156, 70, 1)'
    }
  ];
  constructor(
    private http: HttpClient,
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private authServer: AuthServerProvider,
    private _bottomSheet: MatBottomSheet
  ) {
    homeObject = this;
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    console.log('serv', this.authServer.getToken());
    this.drainbattery();

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

    this.http
      .get(SERVER_API_URL + 'api/reservoir/refill/' + this.account.id + 'insulin', options)
      .pipe(
        map((res: Response) => {
          //this.bsl = JSON.parse(res);
          return res;
        })
      )
      .subscribe();

    console.log('refillInsulin');
  }

  refillGlucagon(): void {
    console.log('BSL Testing', this.bsl);
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

  drainbattery() : any {
    
    setInterval(() => this.drainbatterytimer(),2000);
  }

  drainbatterytimer() :any {
   
    let w = $("#batterybar").width();
        if(w>25){
      $("#batterybar").width(w-2);
      
      if(w<90){
       
        $("#batterybar").addClass("lowbattery");
      }
  }
}

openBottomSheet(): void {
  this._bottomSheet.open(BottomSheetOverviewExampleSheetComponent);
}

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}

@Component({
  selector: 'jhi-bottom-sheet-overview-example-sheet',
  template:'<div><span mat-line>Google Keep</span><span mat-line>Add to a note</span></div> ',
  styleUrls: ['home.scss']
})
export class BottomSheetOverviewExampleSheetComponent {
  constructor(private _bottomSheetRef: MatBottomSheetRef<BottomSheetOverviewExampleSheetComponent>) {}

  // openLink(event: MouseEvent): void {
  //   this._bottomSheetRef.dismiss();
  //   event.preventDefault();
  // }
}
