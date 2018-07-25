import { Component, OnInit, ViewChild, ElementRef, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {

  sample: number;
  rows : any[];

  someKeyboardConfig: any = {
    start: [2010],
    step: 1,
    range: {
      min: 2010,
      max: 2030
    },
    pips: {
      mode: 'count',
      density: 5,
      values: 6,
      stepped: true
    }
  }

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.sample = this.someKeyboardConfig.start;
    this.rows = [
      {sender: "Sam", statusMessage: "Plan is accepted", date: new Date()},
      {sender: "Mark", statusMessage: "Plan is Ok", date: new Date()},
      {sender: "Ram", statusMessage: "Plan is accepted", date: new Date()},
    ];
  }

  onChange(event : any) {
    console.log(this.sample);
  }

}
