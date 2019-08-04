import { Component } from '@angular/core';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'tdf-template-driven-form';

  topics = ['Angular','React','Vue'];
  userModel = new User('Ashish', 'a@b.com', '111111','Angular', 'morning', true);

  onSubmit() {
    console.log(this.userModel);
  }
}
