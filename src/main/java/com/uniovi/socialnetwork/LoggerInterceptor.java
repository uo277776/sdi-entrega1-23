package com.uniovi.socialnetwork;

import com.uniovi.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggerService loggerService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        String url = request.getRequestURL().toString();
        if(url.contains("http://localhost:8090/user")){
            loggerService.add("PET","UsersController URL "+request.getRequestURL().toString()+" , method: "+request.getMethod()+" parameters:"+request.getParameterMap().keySet());
        }
        else if(url.contains("http://localhost:8090/invitation")){
            loggerService.add("PET","InvitationsController URL "+request.getRequestURL().toString()+" , method: "+request.getMethod()+ " parameters:"+request.getParameterMap().keySet());
        }
        else if(url.contains("http://localhost:8090/post")){
            loggerService.add("PET","PostController URL "+request.getRequestURL().toString()+" , method: "+request.getMethod()+ " parameters:"+request.getParameterMap().keySet());
        }
        else if(url.contains("http://localhost:8090/signup")){
            loggerService.add("PET","UsersController URL: "+request.getRequestURL().toString()+" , method: "+request.getMethod()+ " parameters:"+request.getParameterMap().keySet());
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        if(url.contains("http://localhost:8090/signup")) {
            loggerService.add("ALTA","UsersController URL: "+request.getRequestURL().toString()+" , method: "+request.getMethod()+ " parameters:"+request.getParameterMap().keySet().toString());
        }
    }
}
