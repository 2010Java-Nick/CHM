import { Component, OnInit, Input } from '@angular/core';
import { Match } from 'src/app/classes/match.model';
import { MatchService } from 'src/app/services/match.service';
import { ProfileService } from 'src/app/services/profile.service';
import { HttpClient } from '@angular/common/http';
import { Photo } from '../../classes/photo.model';
import { PhotoService } from '../../services/photo.service';
import { DomSanitizer } from "@angular/platform-browser";
import { Profile } from '../../classes/profile.model';

@Component({
  selector: '.app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.css']
})
export class ProfileCardComponent implements OnInit {

  @Input()
  match!: Match;

  imgUploaded: any[];

  getProfile1() {
    if (this.match.profile1.profileId === undefined) {
      this.profileService.readProfile(this.match.profile1).subscribe( (res) =>
      {
        this.match.profile1 = res;
        this.createMatch();
      })
    } else {
      this.createMatch();
    }
    
  }
  createMatch() {
    this.match.matched = true;
    //this.profileService.readProfile(this.match.profile1)
    this.matchService.createMatch(this.match).subscribe( (res) => {
      window.location.reload();
      window.alert("Congratulations on your new match with " + this.match.profile2.firstName + " !");
    })
  }
  constructor(private matchService: MatchService, private profileService: ProfileService, private photoService: PhotoService) { }

  ngOnInit(): void {
    this.photoService.readAllPhotosByProfile(this.match.profile2.profileId).subscribe( (res) => {
      console.log(res);
      this.imgUploaded = res[0].photo;
    });
  }

  formatImage(img: any): any {
    return 'data:image/jpeg;base64,' + img;
  }

}
