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
import smarthomeapi.dto.LogRequestDTO;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    private LogsService logsService;
    @Autowired
    private SensorService sensorService;

    public LogsController(LogsService logsService) {
        this.logsService = logsService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Log> uploadLog(@RequestBody LogRequestDTO logRequestDTO) {
        // Criar um objeto Info com os dados da solicitação
        Log log = new Log();
        log.setAcao(logRequestDTO.acao());
        log.setTipo(logRequestDTO.tipo());

        // Definir createdAt e updatedAt, se necessário
        LocalDateTime now = LocalDateTime.now();
        log.setCreatedAt(now);
        log.setUpdatedAt(now);

        // Salvar o objeto Info no banco de dados
        Log savedLog = logsService.saveLogs(log);

        if(!logRequestDTO.sensor().isEmpty()){
            Sensor sensor = new Sensor();

            switch(logRequestDTO.sensor()){
                case "LUZ INTERIOR":
                     sensor = sensorService.find("LUZ INTERIOR");
                    break;
                case "PORTA":
                    sensor = sensorService.find("PORTA");
                    break;
                case "JANELA":
                    sensor = sensorService.find("JANELA");
                    break;
                case "ALARME":
                    sensor = sensorService.find("ALARME");
                    break;
            }

            sensor.setState(logRequestDTO.state());


            sensorService.save(sensor);
        }

        // Retornar o objeto Info salvo como resposta
        return ResponseEntity.ok(savedLog);
    }

}
