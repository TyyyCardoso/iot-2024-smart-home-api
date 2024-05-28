package smarthomeapi.database.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthomeapi.database.entities.Sensor;
import smarthomeapi.database.repos.SensorRepository;

import java.util.List;

@Service
public class SensorService {

    @Autowired
    SensorRepository sensorRepository;


    public List<Sensor> getAllSensor(){
        return sensorRepository.findAll();
    }

    public Sensor save(Sensor sensor){
        return sensorRepository.save(sensor);
    }

    public Sensor find(String name){
        return sensorRepository.findSensorByName(name);
    }
}
