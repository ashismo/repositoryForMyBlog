import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {

  public employees = [];
  constructor(private _employeeService: EmployeeService) { }

  ngOnInit() {
     //this.employees = this._employeeService.getEmployees();

     // Subscribe to the observable.
     this._employeeService.getEmployees().subscribe(data => this.employees = data);
  }

  /*public employees = [
    {name: "Ram", age: 10},
    {name: "Sam", age: 20}
  ];*/

}
