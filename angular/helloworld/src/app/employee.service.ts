import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IEmployee } from './employee';
import { Observable } from 'rxjs';
// Imported the catch operator
//import 'rxjs/add/operator/catch';  
// Imported the throw operator
//import 'rxjs/add/observable/throw'; 

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private _url: string = "/assets/data/employeeData.json";
  constructor(private http: HttpClient) { }

  getEmployees(): Observable<IEmployee[]> {
    return this.http.get<IEmployee[]>(this._url);
              //.catch(this.errorHandler);


    /*return [
      {name: "Ram", age: 10},
      {name: "Sam", age: 20}
    ];*/
  };

  errorHandler(error: HttpErrorResponse) {
    return Observable.throw(error.message || "Server error");
  }
}
