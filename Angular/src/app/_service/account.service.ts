import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import {User} from './../_models/user';
import { Router } from '@angular/router';
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
  // tslint:disable-next-line:typedef
  async login(email: string, password: string) {
    let user1: User;
    this.user = null;
    user1 = await this.getUserByEmail(email);
    console.log(user1);


    if (user1 === undefined){
      this.isAuthenticated = false;
    }else{
      if (user1.password === password){
        this.user = user1;
        this.isAuthenticated = true;
        console.log(this.isAuthenticated);
        this.router.navigate(['/dashboard']);
      }else{
        this.isAuthenticated = false;
      }
    }
  }

  // tslint:disable-next-line:typedef
  async logout() {
    try {
      this.isAuthenticated = false;
      this.user = null;
      this.router.navigate(['login']);
    } catch (err) {
      console.error(err);
    }
  }
  // tslint:disable-next-line:typedef
  getUserByEmail(email){
    return this.userList.find(u => u.email === email);
  }

}
