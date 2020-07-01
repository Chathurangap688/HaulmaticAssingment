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
  // private authClient = new OktaAuth({
  //     issuer: 'https://{YourOktaDomain}/oauth2/default',
  //     clientId: '{ClientId}'
  // });

  public isAuthenticated = new BehaviorSubject<boolean>(false);
  constructor(private http: HttpClient, private router: Router) {
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
    var user: User;
    user = await this.getUserByEmail(email);
    console.log(user);
    if(user === undefined){
      this.isAuthenticated.next(false);
    }else{
      if(user.password === email){
        this.isAuthenticated.next(true);
      }else{
        this.isAuthenticated.next(false);
      }
    }
    // this.isAuthenticated.next(true);
  }

      async logout(redirect: string) {
        try {
          this.isAuthenticated.next(false);
          this.router.navigate([redirect]);
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
