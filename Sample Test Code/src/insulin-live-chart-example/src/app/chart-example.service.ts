import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class ChartExampleService {

  constructor(private _http:HttpClient) { }

  sampleChart(){
    return this._http.get("https://cors-anywhere.herokuapp.com/https://samples.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=b1b15e88fa797225412429c1c50c122a1")
    .pipe(map(result => result));
  }
}
