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

    private String user;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        String url = request.getRequestURL().toString();
        if(url.contains("http://localhost:8090/user")){
            String parametros="parametros: ";
            Set<String> list = request.getParameterMap().keySet();
            for(String parametro : list){
                parametros+=", "+parametro.toString();
            }
            loggerService.add("PET","UsersController URL "+request.getRequestURL().toString()+" , method: "+request.getMethod()+" "+parametros);
        }
        else if(url.contains("http://localhost:8090/invitation")){
            String parametros="parametros: ";
            Set<String> list = request.getParameterMap().keySet();
            for(String parametro : list){
                parametros+=", "+parametro.toString();
            }
            loggerService.add("PET","InvitationsController URL "+request.getRequestURL().toString()+" , method: "+request.getMethod()+ " "+parametros);
        }
        else if(url.contains("http://localhost:8090/signup")){
            loggerService.add("PET","UsersController URL: "+request.getRequestURL().toString()+" , method: "+request.getMethod()+ " parameters:"+request.getParameterMap().keySet().toString());
        }
        else if(url.contains("http://localhost:8090/logout")){
            loggerService.add("PET","UsersController con URL "+request.getRequestURL().toString()+" , method: "+request.getMethod()+ " parameters:"+request.getParameterMap().keySet().toString());
        }

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        if(url.contains("http://localhost:8090/login") || url.contentEquals("http://localhost:8090/")){
            String query = request.getQueryString();
            if(query==null)
                loggerService.add("“LOGIN-EX",request.getParameter("username"));
            else if(query.equals("error"))
                loggerService.add("LOGIN-ERR","");
        }
        if(url.contains("http://localhost:8090/logout")){
            loggerService.add("LOGOUT","HOLA");
        }
    }
}
