import { Component, Input, OnInit, ɵɵtrustConstantResourceUrl } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Photo } from '../../classes/photo.model';
import { PhotoService } from '../../services/photo.service';
import { DomSanitizer } from "@angular/platform-browser";
import { Profile } from '../../classes/profile.model';

@Component({
  selector: 'app-photo-upload',
  templateUrl: './photo-upload.component.html',
  styleUrls: ['./photo-upload.component.css']
})

export class PhotoUploadComponent implements OnInit {

  private _currentProfileId:number

  profile = {} as Profile;

  @Input() set currentProfileId(value:number) { 
    if (value > 1){
      this._currentProfileId=value;
      this.profile.profileId = value;
      this.pic.profile = this.profile;
      console.log("currentProfileId change");
      
      this.onUpload();
    }
    
  }

  // get profileId(): number { return this._profileId; }

  imgURL: any;
  imgURLUploaded: any;
  public message: string;
  pic = {} as Photo;
 
  preview(files) {
    this.imgURLUploaded = null;
    if (files.length === 0)
      return;
 
    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported.";
      return;
    }
 
    var reader1 = new FileReader();
    reader1.readAsDataURL(files[0]); 
    reader1.onload = (_event) => { 
      this.imgURL = reader1.result; 
    }

    var reader = new FileReader();
    var fileByteArray = [];
    reader.readAsArrayBuffer(files[0]);
    reader.onloadend = function (evt) {
      if (evt.target.readyState == FileReader.DONE) {
        var arrayBuffer = evt.target.result,
        array = new Uint8Array(arrayBuffer);
        for (var i = 0; i < array.length; i++) {
          fileByteArray.push(array[i]);
        }
        
      }
    }
    this.pic.photo = fileByteArray;
    // console.log("this is the pic:");
    // console.log(this.pic);
  }

  onUpload() {
    // console.log("Called onUpload");
    // this.photoService.createPhoto(this.pic).subscribe( (res) => {
    //   this.pic.photoId = res;
    //   console.log("in subscription");
    // });
  };

  // afterUpload() {
  //   this.imgURL = null;
  //   this.photoService.readPhoto(this.pic.photoId).subscribe ( (res) =>
  //   {
  //     console.log("Changing image ");
  //     console.log(this.imgURL);
  //     this.imgURLUploaded = res.photo;
  //   });
  // }

  formatImage(img: any): any {
    return 'data:image/jpeg;base64,' + img;
  }

  constructor(private http: HttpClient, private photoService : PhotoService, private sanitizer : DomSanitizer) { }

  ngOnInit(): void {
  }

}
