import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { Router } from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  showPayment = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
    //Toggle Click Function
    $("#menu-toggle").click(function (e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  }

  tooglePayment(){
    this.showPayment = ! this.showPayment;
  }


}
