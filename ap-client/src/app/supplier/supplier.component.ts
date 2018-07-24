import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss']
})
export class SupplierComponent implements OnInit {
  closeResult;

  rows = [
    { 
      pname:"Package Name",
      pcode:"DC1",
      prate:"150",
      isEditable:true,
      dualmailers: [
        {
          id:'DCDM1',
          value:0,
        },
        {
          id:'DCDM2',
          value:0
        },
        {
          id:'DCDM3',
          value:0
        },
        {
          id:'DCDM4',
          value:0
        },
        {
          id:'DCDM5',
          value:0
        },
        {
          id:'DCDM6',
          value:0
        },
        {
          id:'DCDM7',
          value:0,
        },
        {
          id:'DCDM8',
          value:0
        },
        {
          id:'DCDM9',
          value:0
        },
        {
          id:'DCDM10',
          value:0
        },
        {
          id:'DCDM11',
          value:0
        },
        {
          id:'DCDM12',
          value:0
        },
        {
          id:'DCDM13',
          value:0
        }
      ],
    } 
  ];

  constructor() {}

  ngOnInit() {
  }

  editDM(id) {
    console.log(this.rows[0].dualmailers[id]);
  }

  saveTable() {
    console.log(this.rows);
  }

}
