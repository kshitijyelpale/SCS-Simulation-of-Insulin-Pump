import { Component, ViewChild } from '@angular/core';
import { ChartExampleService } from './chart-example.service'
//import { Chart } from 'chart.js'
import 'chartjs-plugin-streaming'
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  //styleUrls: ['./app.component.css']
})
// export class AppComponent {
//   title = 'insulin-simulation-project';

//    chartColors = {
//     red: 'rgb(255, 99, 132)',
//     orange: 'rgb(255, 159, 64)',
//     yellow: 'rgb(255, 205, 86)',
//     green: 'rgb(75, 192, 192)',
//     blue: 'rgb(54, 162, 235)',
//     purple: 'rgb(153, 102, 255)',
//     grey: 'rgb(201, 203, 207)'
//   };

//   chart = [];
//   canvas: any;
//   ctx: any;
//   LQChart: any;
//   @ViewChild('mychart',{static: false}) mychart;
//   //realtimeChart : any;

//   randomScalingFactor() {
//     return (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100);
//   }


//   onRefresh(chart) {
//     chart.config.data.datasets.forEach(function(dataset) {
//       dataset.data.push({
//         x: Date.now(),
//         y: this.randomScalingFactor()
//       });
//     });
//   }

// color = Chart.helpers.color;
// config = {
// 	type: 'line',
// 	data: {
// 		datasets: [{
// 			label: 'Dataset 1 (linear interpolation)',
// 			backgroundColor: this.color(this.chartColors.red).alpha(0.5).rgbString(),
// 			borderColor: this.chartColors.red,
// 			fill: false,
// 			lineTension: 0,
// 			borderDash: [8, 4],
// 			data: []
// 		}, {
// 			label: 'Dataset 2 (cubic interpolation)',
// 			backgroundColor: this.color(this.chartColors.blue).alpha(0.5).rgbString(),
// 			borderColor: this.chartColors.blue,
// 			fill: false,
// 			cubicInterpolationMode: 'monotone',
// 			data: []
// 		}]
// 	},
// 	options: {
// 		title: {
// 			display: true,
// 			text: 'Interactions sample'
// 		},
// 		scales: {
// 			xAxes: [{
// 				type: 'realtime',
// 				realtime: {
// 					duration: 20000,
// 					ttl: 60000,
// 					refresh: 1000,
// 					delay: 2000,
// 					pause: false,
// 					onRefresh: this.onRefresh
// 				}
// 			}],
// 			yAxes: [{
// 				type: 'linear',
// 				display: true,
// 				scaleLabel: {
// 					display: true,
// 					labelString: 'value'
// 				}
// 			}]
// 		},
// 		tooltips: {
// 			mode: 'nearest',
// 			intersect: false
// 		},
// 		hover: {
// 			mode: 'nearest',
// 			intersect: false
// 		},
// 		plugins: {
// 			streaming: {
// 				frameRate: 30
// 			}
// 		}
// 	}
// };

//   constructor(private dataSample : ChartExampleService){

//   }


//   ngOnInit(){

//     //var ctx = document.getElementById('myChart').getContext('2d');


//     this.dataSample.sampleChart()
//     .subscribe(res => {
//       console.log(res);

//       let temp_max = res['list'].map(res => res.temp.max)
//       let temp_min = res['list'].map(res => res.temp.min)
//       let all_dates = res['list'].map(res => res.dt)

//       let weatherDates = []
//       all_dates.forEach((res) => {
//         let jsDate = new Date(res*1000)
//         weatherDates.push(jsDate.toLocaleTimeString('en',{year:'numeric',month:'short',day:'numeric'}))
//       });

//       console.log(weatherDates)

//       this.chart = new Chart('canvas',{
//         type:'line',
//         // data: {
//         //   labels : weatherDates,
//         //   datasets : [
//         //     {
//         //     data: temp_max,
//         //     borderColor: '#3cba9f',
//         //     fill: false
//         //     },
//         //     {
//         //       data: temp_min,
//         //       borderColor: '#ffcc00',
//         //       fill: false
//         //       }
//         //   ]
//         // },
//         data: {
//           datasets: [{
//             label: 'Dataset 1 (linear interpolation)',
//             backgroundColor: this.color(this.chartColors.red).alpha(0.5).rgbString(),
//             borderColor: this.chartColors.red,
//             fill: false,
//             lineTension: 0,
//             borderDash: [8, 4],
//             data: []
//           }, {
//             label: 'Dataset 2 (cubic interpolation)',
//             backgroundColor: this.color(this.chartColors.blue).alpha(0.5).rgbString(),
//             borderColor: this.chartColors.blue,
//             fill: false,
//             cubicInterpolationMode: 'monotone',
//             data: []
//           }]
//         },
//         options: {
//           title: {
//             display: true,
//             text: 'Line chart (hotizontal scroll)'
//           },
//           scales: {
//             xAxes: [{
//               type: 'realtime',
//               // realtime: {
//               //   duration: 20000,
//               //   refresh: 1000,
//               //   delay: 2000,
//               //   onRefresh: this.onRefresh
//               // }
//             }],
//             yAxes: [{
//               scaleLabel: {
//                 display: true,
//                 labelString: 'value'
//               }
//             }]
//           },
//           tooltips: {
//             mode: 'nearest',
//             intersect: false
//           },
//           hover: {
//             mode: 'nearest',
//             intersect: false
//           }
//         }
//         // options: {
//         //   legend: {
//         //     display: false
//         //   },
//         //   scales: {
//         //     xAxes: [{
//         //       display: true
//         //     }],
//         //     yAxes: [{
//         //       display: true
//         //     }]
//         //   }
//         // }
//       })
//     }

//     )
//   }
// }

export class AppComponent {
  title = 'insulin-simulation-project';
  datasets: any[] = [{

    data: [],

    label: 'Dataset 1',

    lineTension: 0,

    borderDash: [8, 4]

  }, {

    data: [],

    label: 'Dataset 2'

  }, {

    data: [],

    label: 'Dataset 3'

  }

];

  options: any = {

    scales: {

      xAxes: [{

        type: 'realtime',

        realtime: {

          onRefresh: function (chart: any) {

            chart.data.datasets.forEach(function (dataset: any) {

              dataset.data.push({

                x: Date.now(),

                y: Math.random()

              });

            });

          },

          delay: 2000

        }

      }]

    }

  };

}