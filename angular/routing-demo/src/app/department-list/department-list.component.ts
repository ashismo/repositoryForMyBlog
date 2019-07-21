import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IDepartment } from '../department';
import { Observable } from 'rxjs';
import { DepartmentService } from '../department.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.scss']
})
export class DepartmentListComponent implements OnInit {

  public departments = [];
  public selectedId;
  constructor(private _departmentService: DepartmentService, 
                  private router: Router, 
                  private route: ActivatedRoute) { }

  ngOnInit() {
    this._departmentService.getDepartments().subscribe(data => this.departments = data);

    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = parseInt(params.get('id'));
      this.selectedId = id;
    });
  }

  onSelect(department) {
    // Ashish - This has a problem once we need to change the route in the application
    //this.router.navigate(['/departments', department.id]);
    
    // Ashish - We should use relative route to make the application more flexible
    this.router.navigate([department.id], {relativeTo: this.route})
  }

  isSelected(department) {
    return department.id === this.selectedId;
  }

}
