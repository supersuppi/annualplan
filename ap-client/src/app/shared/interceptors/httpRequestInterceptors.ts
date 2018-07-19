import { HttpInterceptor, HttpEvent, HttpRequest, HttpHandler } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

/**
 * This class basically adds headers to all the request sent.
 */
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if(localStorage.getItem('validUser') === 'true') {
            request = request.clone({
                setHeaders: {
                    Authorization: localStorage.getItem('token')
                }
            });
        }

        request = request.clone({
            setHeaders: {
                "Access-Control-Allow-Origin" :  "*",
                "Access-Control-Allow-Credentials" : "true",
                "Content-Type" :  "application/json"
            }
        });

        return next.handle(request);
    }

}