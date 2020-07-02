import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, from } from 'rxjs';
import {User} from './../_models/user';
import { Router } from '@angular/router';
// import OktaAuth from '@okta/okta-auth-js';
@Injectable({
  providedIn: 'root'
})
export class AccountService {
  public userList;
  public user: User;

  public isAuthenticated;
  constructor(private http: HttpClient, private router: Router) {
    this.isAuthenticated = false;
    this.http.get('./assets/userAccounts.json').subscribe(
      data => {
        this.userList = data as string [];	
      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
  }
  async login(email: string, password: string) {
    var user1: User;
    this.user = null;
    user1 = await this.getUserByEmail(email);
    console.log(user1);
    

    if(user1 === undefined){
      this.isAuthenticated = false;
    }else{
      if(user1.password === password){
        this.user = user1;
        this.isAuthenticated = true;
        console.log(this.isAuthenticated);
        this.router.navigate(['/dashboard']);
      }else{
        this.isAuthenticated = false;
      }
    }
  }

      async logout() {
        try {
          this.isAuthenticated = false;
          this.user = null;
          this.router.navigate(['login']);
        } catch (err) {
          console.error(err);
        }
  }
  getUserByEmail(email){
    return this.userList.find(u => u.email === email);
  }

  login1(username, password) {
      console.log(this.userList);
      return this.userList[0];

  }
  emailValidate(){

  }

  // async checkAuthenticated() {
  //       const authenticated = await this.authClient.session.exists();
  //       this.isAuthenticated.next(authenticated);
  //       return authenticated;
  // }
}
