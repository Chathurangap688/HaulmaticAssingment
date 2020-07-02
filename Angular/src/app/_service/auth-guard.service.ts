import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AccountService } from './../_service/account.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(public accountService: AccountService, public router: Router) {}

  // tslint:disable-next-line:typedef
  async canActivate() {
    if (!await this.accountService.isAuthenticated) {
      await this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}