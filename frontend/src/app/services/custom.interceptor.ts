import { HttpInterceptorFn } from '@angular/common/http';

export const customInterceptor: HttpInterceptorFn = (req, next) => {

  const baseUrl = 'http:localhost:8085'
  const authUrls: string[] = [
    baseUrl + '/auth/sigin'
  ];
  const token = localStorage.getItem('token');
  if(!authUrls.includes(req.url)){
    if (token) {
      const clonedRequest = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
      return next(clonedRequest);
    }
  }
  return next(req);
};
