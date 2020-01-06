import { Component, OnInit } from '@angular/core';

import { User } from '../_models/index';
import { UserService } from '../_services/index';
import 'chartjs-plugin-streaming'

@Component({
    moduleId: module.id.toString(),
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    currentUser: User;
    users: User[] = [];

    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
        this.loadAllUsers();
    }

    deleteUser(id: number) {
        this.userService.delete(id).subscribe(() => { this.loadAllUsers() });
    }

    private loadAllUsers() {
        this.userService.getAll().subscribe(users => { this.users = users; });
    }

    datasets: any[] = [{

        data: [],
    
        label: 'Dataset 1',
    
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
    
              onRefresh: function (chart: any) {
    
                chart.data.datasets.forEach(function (dataset: any) {
    
                  dataset.data.push({
    
                    x: Date.now(),
    
                    y: Math.floor(Math.random() * (200 - 100 + 1)) + 100
    
                  });
    
                });
    
              },
    
              delay: 2000
    
            }
    
          }]
    
        }
    
      };
}