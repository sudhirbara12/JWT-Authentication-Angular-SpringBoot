import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  credential = {
    username :"",
    password : ""
  }

  ngOnInit(): void {
    
  }

  submit(){
    if((this.credential.username!="" && this.credential.password!="" ) && (this.credential.username!=null && this.credential.password!=null)){
      console.log("Form is submitted");
    }
    else {
      console.log("Form is Empty");
    }
  }

}
