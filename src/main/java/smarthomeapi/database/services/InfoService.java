package smarthomeapi.database.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthomeapi.database.entities.Info;
import smarthomeapi.database.repos.InfoRepository;

import java.util.List;

@Service
public class InfoService {

    @Autowired
    InfoRepository infoRepository;

    public List<Info> getRecentInfo(){
        return infoRepository.findAll();
    }

    public Info getLastInfo() {
        return infoRepository.findLastInfo().get(0);
    }

    public Info saveRecentInfo(Info info){
        return infoRepository.save(info);
    }

}
