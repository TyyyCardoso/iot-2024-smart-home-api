package smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import smarthomeapi.database.entities.Info;
import smarthomeapi.database.entities.Log;
import smarthomeapi.database.services.InfoService;
import smarthomeapi.database.services.LogsService;
import smarthomeapi.dto.InfoRequestDTO;
import smarthomeapi.dto.LogRequestDTO;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    private LogsService logsService;

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

        // Retornar o objeto Info salvo como resposta
        return ResponseEntity.ok(savedLog);
    }

}
