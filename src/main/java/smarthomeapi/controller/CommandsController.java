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
import smarthomeapi.dto.TempRequestDTO;
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
    public ResponseEntity<String> execute(@RequestBody CommandRequestDTO commandRequestDTO) {

        switch(commandRequestDTO.command()){
            case "1":
                mqttPublisher.publishCommand("LIGAR LUZ INTERIOR");
                break;
            case "2":
                mqttPublisher.publishCommand("DESLIGAR LUZ INTERIOR");
                break;
            case "3":
                mqttPublisher.publishCommand("ABRIR PORTA");
                break;
            case "4":
                mqttPublisher.publishCommand("FECHAR PORTA");
                break;
            case "5":
                mqttPublisher.publishCommand("ABRIR JANELA");
                break;
            case "6":
                mqttPublisher.publishCommand("FECHAR JANELA");
                break;
            case "7":
                mqttPublisher.publishCommand("ATIVAR ALARME");
                break;
            case "8":
                mqttPublisher.publishCommand("DESATIVAR ALARME");
                break;
        }

        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/set_temp")
    public ResponseEntity<String> setTemp(@RequestBody TempRequestDTO tempRequestDTO) {
        mqttPublisher.publishCommand("TEMP:" + tempRequestDTO.temp());
        return ResponseEntity.ok("Ok");
    }

}
