import { Component, OnInit } from '@angular/core';
import { MatchService } from '../../services/match.service';
import { Match } from '../../classes/match.model';
@Component({
  selector: 'app-poten-match-list',
  templateUrl: './poten-match-list.component.html',
  styleUrls: ['./poten-match-list.component.css']
})
export class PotenMatchListComponent implements OnInit {

  matchList: Match[] = new Array();

  profileId: number = 48;

  constructor(private matchService: MatchService) {}

  ngOnInit(): void {
    this.matchService.readAllPotentialMatcheByProfileId(this.profileId).subscribe( (res) => {
      this.matchList = res;
    });
  }

}
