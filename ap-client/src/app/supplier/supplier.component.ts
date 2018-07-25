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
        "pname": "Premier Plus Package",
        "pcode": "DC1",
        "prate": 500,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC1DM1",
                "value": 0
            },
            {
                "id": "DC1DM2",
                "value": 0
            },
            {
                "id": "DC1DM3",
                "value": 0
            },
            {
                "id": "DC1DM4",
                "value": 0
            },
            {
                "id": "DC1DM5",
                "value": 0
            },
            {
                "id": "DC1DM6",
                "value": 0
            },
            {
                "id": "DC1DM7",
                "value": 0
            },
            {
                "id": "DC1DM8",
                "value": 0
            },
            {
                "id": "DC1DM9",
                "value": 0
            },
            {
                "id": "DC1DM10",
                "value": 0
            },
            {
                "id": "DC1DM11",
                "value": 0
            },
            {
                "id": "DC1DM12",
                "value": 0
            },
            {
                "id": "DC1DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Premier Package",
        "pcode": "DC2",
        "prate": 450,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC2DM1",
                "value": 0
            },
            {
                "id": "DC2DM2",
                "value": 0
            },
            {
                "id": "DC2DM3",
                "value": 0
            },
            {
                "id": "DC2DM4",
                "value": 0
            },
            {
                "id": "DC2DM5",
                "value": 0
            },
            {
                "id": "DC2DM6",
                "value": 0
            },
            {
                "id": "DC2DM7",
                "value": 0
            },
            {
                "id": "DC2DM8",
                "value": 0
            },
            {
                "id": "DC2DM9",
                "value": 0
            },
            {
                "id": "DC2DM10",
                "value": 0
            },
            {
                "id": "DC2DM11",
                "value": 0
            },
            {
                "id": "DC2DM12",
                "value": 0
            },
            {
                "id": "DC2DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Supplier Stands",
        "pcode": "DC3",
        "prate": 400,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC3DM1",
                "value": 0
            },
            {
                "id": "DC3DM2",
                "value": 0
            },
            {
                "id": "DC3DM3",
                "value": 0
            },
            {
                "id": "DC3DM4",
                "value": 0
            },
            {
                "id": "DC3DM5",
                "value": 0
            },
            {
                "id": "DC3DM6",
                "value": 0
            },
            {
                "id": "DC3DM7",
                "value": 0
            },
            {
                "id": "DC3DM8",
                "value": 0
            },
            {
                "id": "DC3DM9",
                "value": 0
            },
            {
                "id": "DC3DM10",
                "value": 0
            },
            {
                "id": "DC3DM11",
                "value": 0
            },
            {
                "id": "DC3DM12",
                "value": 0
            },
            {
                "id": "DC3DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Unichem Only Eventing",
        "pcode": "DC4",
        "prate": 350,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC4DM1",
                "value": 0
            },
            {
                "id": "DC4DM2",
                "value": 0
            },
            {
                "id": "DC4DM3",
                "value": 0
            },
            {
                "id": "DC4DM4",
                "value": 0
            },
            {
                "id": "DC4DM5",
                "value": 0
            },
            {
                "id": "DC4DM6",
                "value": 0
            },
            {
                "id": "DC4DM7",
                "value": 0
            },
            {
                "id": "DC4DM8",
                "value": 0
            },
            {
                "id": "DC4DM9",
                "value": 0
            },
            {
                "id": "DC4DM10",
                "value": 0
            },
            {
                "id": "DC4DM11",
                "value": 0
            },
            {
                "id": "DC4DM12",
                "value": 0
            },
            {
                "id": "DC4DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Gondola Fins",
        "pcode": "DC5",
        "prate": 300,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC5DM1",
                "value": 0
            },
            {
                "id": "DC5DM2",
                "value": 0
            },
            {
                "id": "DC5DM3",
                "value": 0
            },
            {
                "id": "DC5DM4",
                "value": 0
            },
            {
                "id": "DC5DM5",
                "value": 0
            },
            {
                "id": "DC5DM6",
                "value": 0
            },
            {
                "id": "DC5DM7",
                "value": 0
            },
            {
                "id": "DC5DM8",
                "value": 0
            },
            {
                "id": "DC5DM9",
                "value": 0
            },
            {
                "id": "DC5DM10",
                "value": 0
            },
            {
                "id": "DC5DM11",
                "value": 0
            },
            {
                "id": "DC5DM12",
                "value": 0
            },
            {
                "id": "DC5DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Impulse Bins",
        "pcode": "DC6",
        "prate": 250,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC6DM1",
                "value": 0
            },
            {
                "id": "DC6DM2",
                "value": 0
            },
            {
                "id": "DC6DM3",
                "value": 0
            },
            {
                "id": "DC6DM4",
                "value": 0
            },
            {
                "id": "DC6DM5",
                "value": 0
            },
            {
                "id": "DC6DM6",
                "value": 0
            },
            {
                "id": "DC6DM7",
                "value": 0
            },
            {
                "id": "DC6DM8",
                "value": 0
            },
            {
                "id": "DC6DM9",
                "value": 0
            },
            {
                "id": "DC6DM10",
                "value": 0
            },
            {
                "id": "DC6DM11",
                "value": 0
            },
            {
                "id": "DC6DM12",
                "value": 0
            },
            {
                "id": "DC6DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Counter Units",
        "pcode": "DC7",
        "prate": 200,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC7DM1",
                "value": 0
            },
            {
                "id": "DC7DM2",
                "value": 0
            },
            {
                "id": "DC7DM3",
                "value": 0
            },
            {
                "id": "DC7DM4",
                "value": 0
            },
            {
                "id": "DC7DM5",
                "value": 0
            },
            {
                "id": "DC7DM6",
                "value": 0
            },
            {
                "id": "DC7DM7",
                "value": 0
            },
            {
                "id": "DC7DM8",
                "value": 0
            },
            {
                "id": "DC7DM9",
                "value": 0
            },
            {
                "id": "DC7DM10",
                "value": 0
            },
            {
                "id": "DC7DM11",
                "value": 0
            },
            {
                "id": "DC7DM12",
                "value": 0
            },
            {
                "id": "DC7DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Full Page",
        "pcode": "DC8",
        "prate": 150,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC8DM1",
                "value": 0
            },
            {
                "id": "DC8DM2",
                "value": 0
            },
            {
                "id": "DC8DM3",
                "value": 0
            },
            {
                "id": "DC8DM4",
                "value": 0
            },
            {
                "id": "DC8DM5",
                "value": 0
            },
            {
                "id": "DC8DM6",
                "value": 0
            },
            {
                "id": "DC8DM7",
                "value": 0
            },
            {
                "id": "DC8DM8",
                "value": 0
            },
            {
                "id": "DC8DM9",
                "value": 0
            },
            {
                "id": "DC8DM10",
                "value": 0
            },
            {
                "id": "DC8DM11",
                "value": 0
            },
            {
                "id": "DC8DM12",
                "value": 0
            },
            {
                "id": "DC8DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Half Page",
        "pcode": "DC9",
        "prate": 100,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC9DM1",
                "value": 0
            },
            {
                "id": "DC9DM2",
                "value": 0
            },
            {
                "id": "DC9DM3",
                "value": 0
            },
            {
                "id": "DC9DM4",
                "value": 0
            },
            {
                "id": "DC9DM5",
                "value": 0
            },
            {
                "id": "DC9DM6",
                "value": 0
            },
            {
                "id": "DC9DM7",
                "value": 0
            },
            {
                "id": "DC9DM8",
                "value": 0
            },
            {
                "id": "DC9DM9",
                "value": 0
            },
            {
                "id": "DC9DM10",
                "value": 0
            },
            {
                "id": "DC9DM11",
                "value": 0
            },
            {
                "id": "DC9DM12",
                "value": 0
            },
            {
                "id": "DC9DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Triple tile Range / GWP Offer",
        "pcode": "DC10",
        "prate": 75,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC10DM1",
                "value": 0
            },
            {
                "id": "DC10DM2",
                "value": 0
            },
            {
                "id": "DC10DM3",
                "value": 0
            },
            {
                "id": "DC10DM4",
                "value": 0
            },
            {
                "id": "DC10DM5",
                "value": 0
            },
            {
                "id": "DC10DM6",
                "value": 0
            },
            {
                "id": "DC10DM7",
                "value": 0
            },
            {
                "id": "DC10DM8",
                "value": 0
            },
            {
                "id": "DC10DM9",
                "value": 0
            },
            {
                "id": "DC10DM10",
                "value": 0
            },
            {
                "id": "DC10DM11",
                "value": 0
            },
            {
                "id": "DC10DM12",
                "value": 0
            },
            {
                "id": "DC10DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Double tile Range / GWP Offer",
        "pcode": "DC11",
        "prate": 70,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC11DM1",
                "value": 0
            },
            {
                "id": "DC11DM2",
                "value": 0
            },
            {
                "id": "DC11DM3",
                "value": 0
            },
            {
                "id": "DC11DM4",
                "value": 0
            },
            {
                "id": "DC11DM5",
                "value": 0
            },
            {
                "id": "DC11DM6",
                "value": 0
            },
            {
                "id": "DC11DM7",
                "value": 0
            },
            {
                "id": "DC11DM8",
                "value": 0
            },
            {
                "id": "DC11DM9",
                "value": 0
            },
            {
                "id": "DC11DM10",
                "value": 0
            },
            {
                "id": "DC11DM11",
                "value": 0
            },
            {
                "id": "DC11DM12",
                "value": 0
            },
            {
                "id": "DC11DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "Standard Tile",
        "pcode": "DC12",
        "prate": 60,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC12DM1",
                "value": 0
            },
            {
                "id": "DC12DM2",
                "value": 0
            },
            {
                "id": "DC12DM3",
                "value": 0
            },
            {
                "id": "DC12DM4",
                "value": 0
            },
            {
                "id": "DC12DM5",
                "value": 0
            },
            {
                "id": "DC12DM6",
                "value": 0
            },
            {
                "id": "DC12DM7",
                "value": 0
            },
            {
                "id": "DC12DM8",
                "value": 0
            },
            {
                "id": "DC12DM9",
                "value": 0
            },
            {
                "id": "DC12DM10",
                "value": 0
            },
            {
                "id": "DC12DM11",
                "value": 0
            },
            {
                "id": "DC12DM12",
                "value": 0
            },
            {
                "id": "DC12DM13",
                "value": 0
            }
        ]
    },
    {
        "pname": "NASH",
        "pcode": "DC13",
        "prate": 50,
        "isEditable": true,
        "dualmailers": [
            {
                "id": "DC13DM1",
                "value": 0
            },
            {
                "id": "DC13DM2",
                "value": 0
            },
            {
                "id": "DC13DM3",
                "value": 0
            },
            {
                "id": "DC13DM4",
                "value": 0
            },
            {
                "id": "DC13DM5",
                "value": 0
            },
            {
                "id": "DC13DM6",
                "value": 0
            },
            {
                "id": "DC13DM7",
                "value": 0
            },
            {
                "id": "DC13DM8",
                "value": 0
            },
            {
                "id": "DC13DM9",
                "value": 0
            },
            {
                "id": "DC13DM10",
                "value": 0
            },
            {
                "id": "DC13DM11",
                "value": 0
            },
            {
                "id": "DC13DM12",
                "value": 0
            },
            {
                "id": "DC13DM13",
                "value": 0
            }
        ]
    }
];

  constructor() {}

  ngOnInit() {
  }

  editDM(rowid,id) {
    console.log(rowid+"=="+id);
    console.log(this.rows[rowid].dualmailers[id]);
  }

  saveTable() {
    console.log(this.rows);
  }

}
