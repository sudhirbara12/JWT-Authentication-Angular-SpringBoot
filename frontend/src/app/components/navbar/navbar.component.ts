import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../../services/login-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{

  isLoggedIn = false;
  constructor(private loginService :LoginServiceService){}
  ngOnInit(): void {
    this.loginService.loginStatus$.subscribe({
      next : (res) => this.isLoggedIn = res
    });
  }

  logout(){
    this.loginService.logout();
    location.reload();
  }

}
