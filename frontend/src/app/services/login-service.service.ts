import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService implements OnInit{

  private loginStatus = new BehaviorSubject<boolean>(false);
  loginStatus$ = this.loginStatus.asObservable();

  baseUrl = 'http://localhost:8085'

  setLoginStatus(status: boolean) {
    this.loginStatus.next(status);
  }

  constructor(private http: HttpClient) {}
  ngOnInit(): void {
  }


  login(token : string){
    localStorage.setItem('token',token);
    this.setLoginStatus(true);
    return true;
  }

  generateToken(credential:any): Observable<User>{
    let url = this.baseUrl + '/auth/signin';
    return this.http.post<User>(url,credential);
  }

  logout(){
    localStorage.removeItem('token');
    this.setLoginStatus(false);
    return true;
  }
  
  isLogin(){
    let token = localStorage.getItem('token')
    if(token == null || token === '' || token == undefined){
      return false
    }
    return true
  }
 
  setToken(token : string){
    localStorage.setItem('token',token)
  }

}
