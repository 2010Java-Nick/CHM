import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ProfileService } from '../../services/profile.service';
import { Profile } from '../../classes/profile.model';

@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.css']
})

export class CreateProfileComponent implements OnInit {

    birthday!:Date;

    profile:Profile = {
        profileId: 1,
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        age: 18,
        bio: "",
        icebreaker: "",
        primaryInterest: "",
    };

    constructor(private profileService: ProfileService) { }

    ngOnInit(): void {
    }

    public createProfile(){

        this.profileService.createProfile(this.profile).subscribe((returnedId) => {
            if(returnedId != -1){
                //this.router.navigate()
            }
        });
    }

    public calculateAge(): number {

        return 1;
    }

}
