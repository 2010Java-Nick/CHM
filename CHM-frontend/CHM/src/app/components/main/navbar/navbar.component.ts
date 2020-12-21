import { Component, OnInit, Output } from '@angular/core';
import * as $ from 'jquery';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Output() showPayment = false; 
  @Output() showProfile = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
    //Toggle Click Function
    $("#menu-toggle").click(function (e) {

      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  }

  togglePayment(){
    this.showPayment = ! this.showPayment;
  }

  toggleProfile(){
    this.showProfile = !this.showProfile;
    this.router.navigate(['/profile']);
  }

  public logout(){
    //do token deletion
    localStorage.clear();
    this.router.navigate(['/login']);
  }


}
