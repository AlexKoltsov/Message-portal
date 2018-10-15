import {Component, OnInit} from '@angular/core';
import {User} from '../models/user';
import {first} from 'rxjs/operators';
import {MessageService} from '../services/message.service';
import {Message} from '../models/message';
import {DetailMessageComponent} from '../detail-message/detail-message.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';
import {ChangePasswordComponent} from '../change-password/change-password.component';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html'
})
export class MainPageComponent implements OnInit {
  currentUser: User;
  messages: Message[] = [];

  constructor(private messageService: MessageService,
              private modalService: NgbModal,
              private authService: AuthenticationService,
              private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadMessagesForUser();
  }

  deleteMessage(id: number) {
    this.messageService.delete(id).pipe(first()).subscribe(() => {
      this.loadMessagesForUser();
    });
  }

  private loadAllMessages() {
    this.messageService.getAll().pipe(first()).subscribe(messages => {
      this.messages = messages;
    });
  }

  private loadMessagesForUser() {
    this.messageService.getByUserId(this.currentUser.id).pipe(first()).subscribe(messages => {
      this.messages = messages;
    });
  }

  openMessage(message: Message) {
    const modalRef = this.modalService.open(DetailMessageComponent);
    modalRef.componentInstance.message = message;
  }

  logout() {
    this.authService.logout(this.currentUser.login);
    this.router.navigate(['/login']);
  }

  changePassword(user: User) {
    const modalRef = this.modalService.open(ChangePasswordComponent);
    modalRef.componentInstance.user = user;
  }
}
