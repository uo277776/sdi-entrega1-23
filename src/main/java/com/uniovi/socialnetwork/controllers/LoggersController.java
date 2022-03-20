package com.uniovi.socialnetwork.controllers;

import com.uniovi.socialnetwork.entities.Log;
import com.uniovi.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoggersController {

    @Autowired
    private LoggerService loggerService;


    @RequestMapping("/log/list")
    public String getList(Model model){
        List<Log> logs = loggerService.getAllLogs();
        model.addAttribute("logs",logs);
        return "admin/logs";
    }

    @RequestMapping("/log/delete")
    public String deleteLogs(){
        loggerService.deleteLogs();
        return "redirect:/log/list";
    }



}
