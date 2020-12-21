import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Photo } from '../classes/photo.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  private readonly PHOTO_URL = "http://localhost:9091/photo";

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })};

  constructor(private httpClient: HttpClient) { }


  /*
  *  Post method for creating photo:
  */
  public createPhoto(photo: Photo): Observable<number> {

    return this.httpClient.post<number>(this.PHOTO_URL, JSON.stringify(photo), this.httpOptions);
  }

  /*
  *  Get method for reading photo by Id:
  */
  public readPhoto(photoId: number): Observable<Photo> {

    const readUrl = `${this.PHOTO_URL}/${photoId}`;

    return this.httpClient.get<Photo>(readUrl, this.httpOptions);
  }

  /*
  *  Get method for reading all photos:
  */
  public readAllPhotos(): Observable<Photo[]> {

    return this.httpClient.get<Photo[]>(this.PHOTO_URL);
  }

  public readAllPhotosByProfile(profileId: number): Observable<Photo[]> {
    return this.httpClient.get<Photo[]>(`${this.PHOTO_URL}/profile/${profileId}`)
  }

  /*
  *  Patch method for updating a photo:
  */
  public updatePhoto(photoId: number, fd: FormData): Observable<Photo> {

    return this.httpClient.post<Photo>(`${this.PHOTO_URL}/${photoId}`, fd);
  }

  /*
  *  Delete method for deleting a photo:
  */
  public deleteProfile(photoId: number): Observable<boolean> {

    const deleteUrl = `${this.PHOTO_URL}/${photoId}`;

    return this.httpClient.delete<boolean>(deleteUrl, this.httpOptions);
  }
}