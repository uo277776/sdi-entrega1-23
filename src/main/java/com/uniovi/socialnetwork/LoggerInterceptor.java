package com.uniovi.socialnetwork;

import com.uniovi.socialnetwork.entities.Log;
import com.uniovi.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggerService loggerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        String url = request.getRequestURL().toString();
        if(url.contains("http://localhost:8090/user")){
            loggerService.addLog("PET","UsersController "+request.getMethod()+ " parameters:"+request.getParameterMap());
        }
        else if(url.contains("http://localhost:8090/invitation")){
            loggerService.addLog("PET","InvitationsController "+request.getMethod()+ " parameters:"+request.getParameterMap());
        }
        else if(url.contains("http://localhost:8090/signup")){
            loggerService.addLog("PET","UsersController "+request.getMethod()+ " parameters:"+request.getParameterMap());
        }
        else if(url.contains("http://localhost:8090/logout")){
            loggerService.addLog("PET","UsersController "+request.getMethod()+ " parameters:"+request.getParameterMap());
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        if(url.contains("http://localhost:8090/login") || url.contentEquals("http://localhost:8090/")){
            String query = request.getQueryString();
            if(query==null)
                loggerService.addLog("“LOGIN-EX","hola");
            else if(query.equals("error"))
                loggerService.addLog("“LOGIN-ERR","hola");
        }
    }
}
