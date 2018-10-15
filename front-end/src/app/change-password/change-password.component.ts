import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {first} from 'rxjs/operators';
import {AlertService} from '../services/alert.service';
import {AuthenticationService} from '../services/authentication.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html'
})
export class ChangePasswordComponent implements OnInit {
  submitted = false;

  changePasswordForm: FormGroup;
  @Input() user;

  constructor(
    private formBuilder: FormBuilder,
    public activeModal: NgbActiveModal,
    private authService: AuthenticationService,
    private alertService: AlertService) {
  }

  ngOnInit() {
    this.changePasswordForm = this.formBuilder.group({
      currentPassword: ['', [Validators.required, Validators.pattern(this.user.password)]],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      retypedPassword: ['', [Validators.required]]
    }, {validator: this.checkPasswords});
  }

  checkPasswords(AC: AbstractControl) {
    const password = AC.get('newPassword').value;
    if (AC.get('retypedPassword').touched || AC.get('retypedPassword').dirty) {
      const verifyPassword = AC.get('retypedPassword').value;

      if (password !== verifyPassword) {
        AC.get('retypedPassword').setErrors({MatchPassword: true});
      } else {
        return null;
      }
    }
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.changePasswordForm.controls;
  }

  changePassword() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.changePasswordForm.invalid) {
      return;
    }

    this.authService.changePassword(this.user.login, this.f.retypedPassword.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Password changed for user ' + this.user.login, true);
        },
        error => {
          this.alertService.error(error['error']['message']);
        });
    this.closeWindow();
  }

  closeWindow() {
    this.activeModal.close('Close click');
  }

}
