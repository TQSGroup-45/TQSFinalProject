import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-applications-table',
  templateUrl: './applications-table.component.html',
  styleUrls: ['./applications-table.component.css']
})
export class ApplicationsTableComponent implements AfterViewInit {

  constructor() { }

  ngOnInit(): void {
  }


  displayedColumns: string[] = ['name', 'email', 'contact', 'profile', 'answer'];

  couriersList = new MatTableDataSource<object>( [
    {name: "José António", email:"joseant@amail.com", contact: 900000000},
    {name: "Manuel Oliveira", email:"moliv@amail.com", contact: 900000001}
  ]);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.couriersList.paginator = this.paginator;
  }
}
