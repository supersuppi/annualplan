import { Component, OnInit } from '@angular/core';
import { ControlContainer, NgForm } from '@angular/forms';

@Component({
  selector: 'app-dualmailer',
  templateUrl: './dualmailer.component.html',
  styleUrls: ['./dualmailer.component.scss'],
  viewProviders: [ { provide: ControlContainer, useExisting: NgForm } ]
})
export class DualmailerComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
