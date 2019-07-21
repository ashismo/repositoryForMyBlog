import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '../department.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

@Component({
  selector: 'app-department-details',
  templateUrl: './department-details.component.html',
  styleUrls: ['./department-details.component.scss']
})
export class DepartmentDetailsComponent implements OnInit {
  public deptId;
  public departments = [];
  constructor(private _departmentService: DepartmentService, 
                private route: ActivatedRoute, 
                private router: Router) { }

  ngOnInit() {
    this._departmentService.getDepartments().subscribe(data => this.departments = data);
    // Ashish: In the snapshot approach the navigation does not work. It does not update the department id in the page
    // let id = parseInt(this.route.snapshot.paramMap.get('id')); 
    //this.deptId = id;
    
    // Ashish: To overcome the above problem the below paramMap observable has been used
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = parseInt(params.get('id'));
      this.deptId = id;
    });
    
  }

  goPrevious() {
    let prevId = this.deptId - 1;
    //this.router.navigate(['/departments', prevId]);
    
    // Ashish - Optional route parameter: Below change is made to make sure that the route is relative
    this.router.navigate(['../',{id: prevId}], {relativeTo: this.route})
  }

  goNext() {
    let nextId = this.deptId + 1;
    // Ashish - Below route is not the relative route. If the URL changes the the aplication will break
    //this.router.navigate(['/departments', nextId]);
    
    // Ashish - Optional route parameter: Below change is made to make sure that the route is relative
    this.router.navigate(['../',{id: nextId}], {relativeTo: this.route})
  }

  // Ashish: Once clicked on the back button then notice the URL which displays "";id"
  // This is called the optional route parameters
  goBackToDepartments() { 
    let selectedId = this.deptId ? this.deptId: null;
    // Ashish - Below route is not the relative route. If the URL changes the the aplication will break
    //this.router.navigate(['/departments',{id: selectedId}])

    // Ashish - Optional route parameter: Below change is made to make sure that the route is relative
    this.router.navigate(['../',{id: selectedId}], {relativeTo: this.route})
  }

  showOverview() {
    this.router.navigate(['overview'], {relativeTo: this.route});
  }

  showContact() {
    this.router.navigate(['contact'], {relativeTo: this.route});
  }
}
