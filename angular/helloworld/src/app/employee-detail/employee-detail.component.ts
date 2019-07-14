import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.scss']
})
export class EmployeeDetailComponent implements OnInit {
  public employees = [];
  /*public employees = [
    {name: "Ram", age: 10},
    {name: "Sam", age: 20}
  ];*/
  
  constructor(private _employeeService: EmployeeService) { }

  ngOnInit() {
    //this.employees = this._employeeService.getEmployees();

    // Subscribe to the observable.
    this._employeeService.getEmployees().subscribe(data => this.employees = data);
  }

}
