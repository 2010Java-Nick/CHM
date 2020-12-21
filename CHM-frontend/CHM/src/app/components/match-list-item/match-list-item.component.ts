import { Component, OnInit, Input } from '@angular/core';
import { Match } from 'src/app/classes/match.model';

@Component({
  selector: '.app-match-list-item',
  templateUrl: './match-list-item.component.html',
  styleUrls: ['./match-list-item.component.css']
})
export class MatchListItemComponent implements OnInit {

  @Input()
  match!: Match;

  constructor() { }

  ngOnInit(): void {
  }

}
