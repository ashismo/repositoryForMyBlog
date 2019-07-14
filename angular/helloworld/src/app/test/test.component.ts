import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-test',
  //templateUrl: './test.component.html',
  template: `
    <div>Inline template - Welcome {{parentData}}</div>
    <h2>{{greetUser()}}</h2>
  `,
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {

  public name = "Ashish";
  constructor() { }

  // Input decorator - Component communication
  @Input('parentData') public parentData;

  @Output() public childEvent = new EventEmitter();
  
  ngOnInit() {
  }

  greetUser() {
    return "Welome " + this.name;
  }
}
