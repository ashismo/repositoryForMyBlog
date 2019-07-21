import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IDepartment } from './department';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private _url: string = "/assets/data/department-details.json";
  constructor(private http: HttpClient) { }

  public getDepartments(): Observable<IDepartment[]> {
    return this.http.get<IDepartment[]>(this._url);
  }
}
