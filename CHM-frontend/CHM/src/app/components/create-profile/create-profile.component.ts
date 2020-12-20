import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, EmailValidator } from '@angular/forms';
import { ProfileService } from '../../services/profile.service';
import { Profile } from '../../classes/profile.model';

@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.css']
})

export class CreateProfileComponent implements OnInit {

    profileForm: FormGroup;

    //birthday!:Date;

    profile = {} as Profile;

    constructor(private profileService: ProfileService) { }

    ngOnInit(): void {

        this.profileForm = new FormGroup({
            firstName: new FormControl(
                '',
                [ Validators.pattern('[a-zA-Z\-]*')]
            ),
            lastName: new FormControl(
                '',
                [Validators.pattern('[a-zA-Z\-]*')]
            ),
            email: new FormControl(
                '',
                Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')
            ),
            phone:new FormControl(
                '+1',
                Validators.pattern('(([+][(]?[0-9]{1,3}[)]?)|([(]?[0-9]{4}[)]?))\s*[)]?[-\s\.]?[(]?[0-9]{1,3}[)]?([-\s\.]?[0-9]{3})([-\s\.]?[0-9]{3,4})')
            )
        });
    }

    public createProfile(){

        this.profile.profileId = 1;
        this.profile.age = 18;
        
        this.profile.firstName = this.profileForm.controls.firstName.value;
        this.profile.lastName = this.profileForm.controls.lastName.value;
        this.profile.email = this.profileForm.controls.email.value;
        

        this.profileService.createProfile(this.profile).subscribe((returnedId) => {
            if(returnedId != -1){
                //this.router.navigate()
            }
        });
    }

    public calculateAge(birthday:Date): number {

        return 1;
    }


    get firstName():any { return this.profileForm.get('firstName'); }

    get lastName():any { return this.profileForm.get('lastName'); }

    get email():any { return this.profileForm.get('email'); }

    get phone():any { return this.profileForm.get('phone'); }

    get birthday():any { return this.profileForm.get('birthday'); }

    get bio():any { return this.profileForm.get('bio'); }

    get icebreaker():any { return this.profileForm.get('icebreaker'); }
}
