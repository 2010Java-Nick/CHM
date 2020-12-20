import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Photo } from '../../classes/photo.model';
import { PhotoService } from '../../services/photo.service';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {

  pic = {} as Photo;
  selectedFile : File;

  onFileSelected(event) {
      this.selectedFile = <File> event.target.files[0];
      this.pic.profileId = 1;
  }

  onUpload() {
    const fd = new FormData();
    fd.append('photo', this.selectedFile, "photo");
    this.pic.profileId = 1;
    this.photoService.createPhoto(this.pic).subscribe();
    this.photoService.updatePhoto(7, fd).subscribe();
  };

  constructor(private http: HttpClient, private photoService : PhotoService) { }

  ngOnInit(): void {
  }

}
