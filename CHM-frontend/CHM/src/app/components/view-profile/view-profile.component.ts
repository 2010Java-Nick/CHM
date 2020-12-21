import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile.service';
import { Profile } from '../../classes/profile.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {

  profileId : number = Number(localStorage.getItem('profileId'));
  profile = {} as Profile;

  constructor(private profileService: ProfileService,
    private router : Router) { }

  ngOnInit(): void {

    this.profileService.readProfile(this.profileId).subscribe(
      returnedProfile => {
        this.profile = returnedProfile;
      }
    );

  }

}
