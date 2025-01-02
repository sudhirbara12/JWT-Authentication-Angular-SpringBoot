import { CanActivateFn, Router } from '@angular/router';
import { LoginServiceService } from './login-service.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {

  const loginService = inject(LoginServiceService );
  const router = inject(Router);

  if (loginService.isLogin()) {
    return true; 
  }
  else {
    router.navigate(['login'])
    return false;
  }
};
