package com.uniovi.socialnetwork.controllers;

import com.uniovi.socialnetwork.entities.Log;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoggersController {

    @Autowired
    private LoggerService loggerService;


    @RequestMapping("/log/list")
    public String getList(Model model,@RequestParam(value= "", required = false)String searchText){
        List<Log> logs;
        if(searchText != null && !searchText.isBlank())
            logs = loggerService.getByType(searchText);
        else
            logs = loggerService.getList();

        model.addAttribute("logs",logs);
        return "admin/logs";
    }

    @RequestMapping(value="/log/delete",method=RequestMethod.POST)
    public String deleteLogs(){
        loggerService.deleteLogs();
        return "redirect:/log/list";
    }





}
