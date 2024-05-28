package smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import smarthomeapi.database.entities.Log;
import smarthomeapi.database.entities.Sensor;
import smarthomeapi.database.services.SensorService;
import smarthomeapi.dto.LogRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Sensor>> getSensorStates() {

        List<Sensor> listOfSensor = sensorService.getAllSensor();

        return ResponseEntity.ok(listOfSensor);
    }

}
