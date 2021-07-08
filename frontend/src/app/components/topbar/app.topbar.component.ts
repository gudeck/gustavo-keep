import { Component } from '@angular/core';
import { Authentication, User } from '@nuvem/angular-base';
import { AppComponent } from '../../app.component';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html',
    styleUrls: ['app.topbar.component.scss']
})
export class AppTopbarComponent {

    constructor(public app: AppComponent, private readonly _authentication: Authentication<User>) {
    }

    get usuario() {
        return this._authentication.getUser();
    }

}
