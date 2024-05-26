package smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import smarthomeapi.database.entities.Log;
import smarthomeapi.database.services.LogsService;
import smarthomeapi.dto.CommandRequestDTO;
import smarthomeapi.dto.LogRequestDTO;
import smarthomeapi.mqtt.MqttPublisher;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/commands")
public class CommandsController {

    private final MqttPublisher mqttPublisher;

    public CommandsController(MqttPublisher mqttPublisher) {
        this.mqttPublisher = mqttPublisher;
    }

    @PostMapping("/execute")
    public ResponseEntity<String> uploadLog(@RequestBody CommandRequestDTO commandRequestDTO) {

        switch(commandRequestDTO.command()){
            case "1":
                mqttPublisher.publishCommand("LIGAR LUZ");
                break;
            case "2":
                mqttPublisher.publishCommand("DESLIGAR LUZ");
                break;
        }

        return ResponseEntity.ok("Ok");
    }

}
