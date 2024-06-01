package smarthomeapi.database.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthomeapi.database.entities.Info;
import smarthomeapi.database.entities.Log;
import smarthomeapi.database.repos.InfoRepository;
import smarthomeapi.database.repos.LogsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogsService {

    @Autowired
    LogsRepository logsRepository;

    public List<Log> getRecentLogs(){
        return logsRepository.findAllOrderByCreatedAt();
    }

    public Log saveLogs(Log log){
        return logsRepository.save(log);
    }
}
