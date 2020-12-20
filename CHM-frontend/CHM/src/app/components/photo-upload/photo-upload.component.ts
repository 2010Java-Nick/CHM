import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Photo } from '../../classes/photo.model';
import { PhotoService } from '../../services/photo.service';

@Component({
  selector: 'app-photo-upload',
  templateUrl: './photo-upload.component.html',
  styleUrls: ['./photo-upload.component.css']
})
export class PhotoUploadComponent implements OnInit {

  pic = {} as Photo;
  selectedFile : File;
  fd = new FormData();

  onFileSelected(event) {
      this.selectedFile = <File> event.target.files[0];
      this.pic.profileId = 1;
  }

  onUpload() {
    this.photoService.createPhoto(this.pic).subscribe( (res) => {
      this.pic.photoId = res;
      this.callBack();
    });
  };

  callBack() {
    this.fd.append('photo', this.selectedFile, "photo");
    this.photoService.updatePhoto(this.pic.photoId, this.fd).subscribe( (res => {
        this.refresh();
    }));
  }

  refresh() {
    window.location.reload();
  }

  constructor(private http: HttpClient, private photoService : PhotoService) { }

  ngOnInit(): void {
  }

}
