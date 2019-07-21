import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DepartmentListComponent } from './department-list/department-list.component';
import { DepartmentDetailsComponent } from './department-details/department-details.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { EmployeesComponent } from './employees/employees.component';
import { DepartmentOverviewComponent } from './department-overview/department-overview.component';
import { DepartmentContactsComponent } from './department-contacts/department-contacts.component';

const routes: Routes = [
  {path: '', redirectTo: '/department-list', pathMatch: 'full'}, // Ashish other values - prefix
  {path: 'department-list', component: DepartmentListComponent},
  // Ashish : Topic: Child route
  {
    path: 'department-list/:id', 
    component: DepartmentDetailsComponent,
    children: [
      {path:'overview', component: DepartmentOverviewComponent},
      {path:'contact', component: DepartmentContactsComponent},
    ]
  },
  
  {path: 'employees', component: EmployeesComponent},
  {path: '**', component: PageNotFoundComponent} // Ashish: All other URLs will show path not found page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

// Ashish: Must include all routing components 
export const routingComponents = [DepartmentListComponent,
                                  DepartmentDetailsComponent,
                                  PageNotFoundComponent,
                                  EmployeesComponent,
                                  DepartmentOverviewComponent,
                                  DepartmentContactsComponent];
