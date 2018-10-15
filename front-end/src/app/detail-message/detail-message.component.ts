import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal',
  templateUrl: './detail-message.component.html'
})
export class DetailMessageComponent implements OnInit {

  @Input() message;

  constructor(public activeModal: NgbActiveModal) {
  }

  ngOnInit() {
  }

}
