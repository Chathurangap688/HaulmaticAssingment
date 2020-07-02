import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AccountService } from './../_service/account.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { AuthService } from '../auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  public loginInvalid: boolean;
  private formSubmitAttempt: boolean;
  private returnUrl: string;
  constructor(
      private fb: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private accountService: AccountService
    ) {
    }
    // tslint:disable-next-line:typedef
    async ngOnInit() {
          this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/game';
          this.form = this.fb.group({
            username: ['', Validators.email],
            password: ['', Validators.required]
          });

        }
    // tslint:disable-next-line:typedef
    async onSubmit() {
      this.loginInvalid = false;
      this.formSubmitAttempt = false;
      if (this.form.valid) {
        try {
          const username = this.form.get('username').value;
          const password = this.form.get('password').value;
          await this.accountService.login(username, password);
          if (this.accountService.isAuthenticated){
            this.loginInvalid = false;
          }else{
            this.loginInvalid = true;
          }
        } catch (err) {
          this.loginInvalid = true;
        }
      } else {
        this.formSubmitAttempt = true;
      }
    }
}
