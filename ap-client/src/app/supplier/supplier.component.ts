import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss']
})
export class SupplierComponent implements OnInit {

  settings:any;
  data:any;

  constructor() { }

  myEvent(event) {
    console.log("Confrim cliecked");
    console.log(this.data);
  }


  ngOnInit() {

    this.settings = {
      mode: 'inline',// for event to emit
      actions : 
      {
        columnTitle:'Actions',
        position:'right',
        add:false,
        delete:false
      },
      columns: {
        promo: {
          title: 'Promo Package',
          filter: false,
          editable:false,
          sort:false
        },
        pcode: {
          title: 'Code',
          filter: false,
          editable:false
        },
        rate: {
          title: 'Rate $',
          filter: false,
          editable:false
        },
        dm1: {
          title: 'DM1',
          filter: false
        },
        dm2: {
          title: 'DM2',
          filter: false
        },
        dm3: {
          title: 'DM3',
          filter: false
        },
        dm4: {
          title: 'DM4',
          filter: false
        },
        dm5: {
          title: 'DM5',
          filter: false
        },
        dm6: {
          title: 'DM6',
          filter: false
        },
        dm7: {
          title: 'DM7',
          filter: false
        },
        dm8: {
          title: 'DM8',
          filter: false
        },
        dm9: {
          title: 'DM9',
          filter: false
        },
        dm10: {
          title: 'DM10',
          filter: false
        },
        dm11: {
          title: 'DM11',
          filter: false
        },
        dm12: {
          title: 'DM12',
          filter: false
        },
        dm13: {
          title: 'DM13',
          filter: false
        }
      }
    };

    this.data = [
      {
        promo: "Silver Package",
        pcode: "DC1",
        rate: 750,
        dm1:0,
        dm2:0,
        dm3:0,
        dm4:0,
        dm5:0,
        dm6:0,
        dm7:0,
        dm8:0,
        dm9:0,
        dm10:0,
        dm11:0,
        dm12:0,
        dm13:0,
      }

    ];



  }

}
