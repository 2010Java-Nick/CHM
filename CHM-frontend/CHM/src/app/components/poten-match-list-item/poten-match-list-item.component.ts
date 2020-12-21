import { Component, OnInit, Input } from '@angular/core';
import { Match } from 'src/app/classes/match.model';
import { MatchService } from 'src/app/services/match.service';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: '.app-poten-match-list-item',
  templateUrl: './poten-match-list-item.component.html',
  styleUrls: ['./poten-match-list-item.component.css']
})
export class PotenMatchListItemComponent implements OnInit {

  @Input()
  match!: Match;
  getProfile1() {
    // if (this.match.profile1.profileId === undefined) {
    //   this.profileService.readProfile(this.match.profile1).subscribe( (res) =>
    //   {
    //     this.match.profile1 = res;
    //     this.createMatch();
    //   })
    // } else {
    //   this.createMatch();
    // }
    
  }
  createMatch() {
    this.match.matched = true;
    //this.profileService.readProfile(this.match.profile1)
    this.matchService.createMatch(this.match).subscribe( (res) => {
      window.location.reload();
      window.alert("Congratulations on your new match with " + this.match.profile2.firstName + " !");
    })
  }
  constructor(private matchService: MatchService, private profileService: ProfileService) { }

  ngOnInit(): void {
    //console.log(this.match.profile1.profileId);
  }
}
