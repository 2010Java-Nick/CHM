import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-home',
  templateUrl: './view-home.component.html',
  styleUrls: ['./view-home.component.css']
})
export class ViewHomeComponent implements OnInit {

  showPayment:boolean;
  showProfile:boolean;

  constructor() { }

  ngOnInit(): void {
  }

  
}
