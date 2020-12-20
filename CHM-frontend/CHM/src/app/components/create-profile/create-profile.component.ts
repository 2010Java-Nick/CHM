import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl, ValidatorFn } from '@angular/forms';
import { ProfileService } from '../../services/profile.service';
import { Profile } from '../../classes/profile.model';
import { PhotoUploadComponent } from '../photo-upload/photo-upload.component';

@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.css']
})

export class CreateProfileComponent implements OnInit {

    profileForm = {} as FormGroup;

    minDate:Date = this.getMinDate();

    profile = {} as Profile;

    currentProfileId:number = 0;

    photoUpload:PhotoUploadComponent;

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
            phone: new FormControl(
                '+1',
                Validators.pattern('(([+][(]?[0-9]{1,3}[)]?)|([(]?[0-9]{4}[)]?))\s*[)]?[-\s\.]?[(]?[0-9]{1,3}[)]?([-\s\.]?[0-9]{3})([-\s\.]?[0-9]{3,4})')
            ),
            birthday: new FormControl(),
            bio: new FormControl(
                '',
                Validators.maxLength(150)
            ),
            icebreaker: new FormControl(
                '',
                Validators.maxLength(75)
            )            
        });
    }

    public createProfile(){
        
        this.profile.firstName = this.profileForm.controls.firstName.value;
        this.profile.lastName = this.profileForm.controls.lastName.value;
        this.profile.email = this.profileForm.controls.email.value;
        this.profile.age = this.getAgeFromBirthday(this.profileForm.controls.birthday.value);
        this.profile.phone = this.profileForm.controls.phone.value;
        this.profile.bio = this.profileForm.controls.bio.value;
        this.profile.icebreaker = this.profileForm.controls.icebreaker.value;        
        
        if(this.profileForm.valid) {
            console.log("IN CREATE PROFILE");
            console.log(this.profile);
            this.profile.profileId = 1;
            this.profileService.createProfile(this.profile).subscribe((returnedId) => {
                if(returnedId != -1){
                    this.currentProfileId = returnedId;
                    this.profile.profileId = returnedId;
                    //call onUpload()
                    //this.router.navigate()
                }
            });
        } else {
            window.alert("Please fix invalid fields!");
        }
       
    };

    private getMinDate(): Date {

        const today = new Date();
        today.setFullYear(today.getFullYear() - 18);
        return today;
    }

    private getAgeFromBirthday(date:Date): number {

        var birthday = new Date(date);
        console.log(birthday)
        var today = new Date();
        var age = today.getFullYear() - birthday.getFullYear(); 
        var m = today.getMonth() - birthday.getMonth();    
        if (m < 0 || (m === 0 && today.getDate() < birthday.getDate())) {  
            age--;     
        }     
        return age; 
    }

    get firstName():any { return this.profileForm.get('firstName'); }

    get lastName():any { return this.profileForm.get('lastName'); }

    get email():any { return this.profileForm.get('email'); }

    get phone():any { return this.profileForm.get('phone'); }

    get bio():any { return this.profileForm.get('bio'); }

    get icebreaker():any { return this.profileForm.get('icebreaker'); }

}
