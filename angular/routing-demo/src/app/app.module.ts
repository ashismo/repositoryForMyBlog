import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
//import { DepartmentListComponent } from './department-list/department-list.component';
//import { DepartmentDetailsComponent } from './department-details/department-details.component';
import { HttpClientModule } from '@angular/common/http';
import { DepartmentService } from './department.service';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { EmployeesComponent } from './employees/employees.component';
import { DepartmentOverviewComponent } from './department-overview/department-overview.component';
import { DepartmentContactsComponent } from './department-contacts/department-contacts.component';

@NgModule({
  declarations: [
    AppComponent,
    //DepartmentListComponent,
    //DepartmentDetailsComponent,
    routingComponents, // Ashish: Removed the above two lines and added the routingComponents constant
    PageNotFoundComponent, EmployeesComponent, DepartmentOverviewComponent, DepartmentContactsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, // Ashish - This is required to integrate Routing 
    HttpClientModule
  ],
  providers: [DepartmentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
