package smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import smarthomeapi.database.entities.Log;
import smarthomeapi.database.entities.Sensor;
import smarthomeapi.database.services.LogsService;
import smarthomeapi.database.services.SensorService;
import smarthomeapi.dto.CommandRequestDTO;
import smarthomeapi.dto.LogRequestDTO;
import smarthomeapi.dto.TempRequestDTO;
import smarthomeapi.mqtt.MqttPublisher;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/commands")
public class CommandsController {

    private final MqttPublisher mqttPublisher;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private LogsService logsService;

    public CommandsController(MqttPublisher mqttPublisher) {
        this.mqttPublisher = mqttPublisher;
    }

    @PostMapping("/execute")
    public ResponseEntity<String> execute(@RequestBody CommandRequestDTO commandRequestDTO) {
        LocalDateTime now = LocalDateTime.now();

        Log log = new Log();

        log.setCreatedAt(now);
        log.setUpdatedAt(now);
        log.setTipo("APP");

        Sensor sensor = new Sensor();
        String state = "0";

        switch(commandRequestDTO.command()){
            case "1":
                mqttPublisher.publishCommand("LIGAR LUZ INTERIOR");
                sensor = sensorService.find("LUZ INTERIOR");
                state = "1";
                log.setAcao("Luz interior foi acesa!");
                break;
            case "2":
                mqttPublisher.publishCommand("DESLIGAR LUZ INTERIOR");
                sensor = sensorService.find("LUZ INTERIOR");
                log.setAcao("Luz interior foi apagada!");
                break;
            case "3":
                mqttPublisher.publishCommand("ABRIR PORTA");
                sensor = sensorService.find("PORTA");
                state = "1";
                log.setAcao("Porta da frente foi aberta!");
                break;
            case "4":
                mqttPublisher.publishCommand("FECHAR PORTA");
                sensor = sensorService.find("PORTA");
                log.setAcao("Porta da frente foi fechada!");
                break;
            case "5":
                mqttPublisher.publishCommand("ABRIR JANELA");
                sensor = sensorService.find("JANELA");
                state = "1";
                log.setAcao("Janela da frente foi aberta!");
                break;
            case "6":
                mqttPublisher.publishCommand("FECHAR JANELA");
                sensor = sensorService.find("JANELA");
                log.setAcao("Janela da frente foi fechada!");
                break;
            case "7":
                mqttPublisher.publishCommand("ATIVAR ALARME");
                sensor = sensorService.find("ALARME");
                state = "1";
                log.setAcao("Alarme foi ativado!");
                break;
            case "8":
                mqttPublisher.publishCommand("DESATIVAR ALARME");
                sensor = sensorService.find("ALARME");
                log.setAcao("Alarme foi desativado!");
                break;
            case "9":
                mqttPublisher.publishCommand("LIGAR AUTO");
                sensor = sensorService.find("AUTOMATICO");
                log.setAcao("Modo automatico ligado!");
                break;
            case "10":
                mqttPublisher.publishCommand("DESLIGAR AUTO");
                sensor = sensorService.find("AUTOMATICO");
                log.setAcao("Modo automatico desligado!");
                break;
        }

        sensor.setState(state);

        sensorService.save(sensor);
        logsService.saveLogs(log);

        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/set_temp")
    public ResponseEntity<String> setTemp(@RequestBody TempRequestDTO tempRequestDTO) {

        LocalDateTime now = LocalDateTime.now();

        Log log = new Log();

        log.setCreatedAt(now);
        log.setUpdatedAt(now);
        log.setTipo("APP");
        log.setAcao("Temperatura maxima alterada!");
        logsService.saveLogs(log);

        mqttPublisher.publishCommand("TEMP:" + tempRequestDTO.temp());


        return ResponseEntity.ok("Ok");
    }

}
