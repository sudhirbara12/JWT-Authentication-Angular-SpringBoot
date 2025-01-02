import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../../services/login-service.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';

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
  
  constructor(private loginservice : LoginServiceService,
    private router : Router
  ){}

  ngOnInit(): void {
  }

  user : User;

  onSubmit(){
    if((this.credential.username!="" && this.credential.password!="" ) && (this.credential.username!=null && this.credential.password!=null)){
      this.loginservice.generateToken(this.credential).subscribe({
        next : (response) => {
          this.user = response;
          this.loginservice.login(this.user.jwtToken);
          this.router.navigate(['dashboard'])
        },
        error : (error) => console.log('Login Failed')
      });
    }
    else {
      console.log("Form is Empty");
    }
  }

  reset(){
    this.credential.username = "";
    this.credential.password = "";
  }

}
