package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.entities.Log;
import com.uniovi.socialnetwork.repositories.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoggerService {

    @Autowired
    private LogsRepository logsRepository;

    public void addLog(String type,String message){
        Log log = new Log(type,message);
        logsRepository.save(log);
    }

    public void deleteLogs(){
        logsRepository.deleteAll();
    }

    public List<Log> getByType(String type){
        return logsRepository.findByType(type);
    }

    public List<Log> getAllLogs(){
        List<Log> logs = new ArrayList<Log>();
        logsRepository.findAll().forEach(logs::add);
        return logs;
    }

}
