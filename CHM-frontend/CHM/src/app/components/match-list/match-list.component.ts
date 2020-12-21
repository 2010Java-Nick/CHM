import { Component, OnInit } from '@angular/core';
import { MatchService } from '../../services/match.service';
import { Match } from '../../classes/match.model';

@Component({
  selector: 'app-match-list',
  templateUrl: './match-list.component.html',
  styleUrls: ['./match-list.component.css']
})
export class MatchListComponent implements OnInit {

  matchList: Match[] = new Array();

  profileId: number = 48;

  constructor(private matchService: MatchService) {}

  ngOnInit(): void {
    this.matchService.readMatchesByProfileId(this.profileId).subscribe( (res) => {
      this.matchList = res;
    });
  }
}
