package smarthomeapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import smarthomeapi.database.entities.Info;
import smarthomeapi.database.repos.InfoRepository;
import smarthomeapi.database.services.InfoService;
import smarthomeapi.dto.InfoRequestDTO;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }
    @PostMapping("/upload")
    public ResponseEntity<Info> getInfo(@RequestBody InfoRequestDTO infoRequestDTO) {
        // Criar um objeto Info com os dados da solicitação
        Info info = new Info();
        info.setTemperatura(infoRequestDTO.temperatura());
        info.setHumidade(infoRequestDTO.humidade());
        info.setDisplay(infoRequestDTO.display());
        info.setEstadoRaspBerry_1(infoRequestDTO.estadoRaspBerry_1());
        info.setEstadoRaspBerry_2(infoRequestDTO.estadoRaspBerry_2());
        info.setDia_noite(infoRequestDTO.dia_noite());

        // Definir createdAt e updatedAt, se necessário
        LocalDateTime now = LocalDateTime.now();
        info.setCreatedAt(now);
        info.setUpdatedAt(now);

        // Salvar o objeto Info no banco de dados
        Info savedInfo = infoService.saveRecentInfo(info);

        // Retornar o objeto Info salvo como resposta
        return ResponseEntity.ok(savedInfo);
    }

}
