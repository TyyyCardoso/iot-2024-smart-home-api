package smarthomeapi.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "information")
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String temperatura;

    private String humidade;

    private String display;

    private boolean estadoRaspBerry_1;

    private boolean estadoRaspBerry_2;

    private String dia_noite;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;


    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getHumidade() {
        return humidade;
    }

    public void setHumidade(String humidade) {
        this.humidade = humidade;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public boolean isEstadoRaspBerry_1() {
        return estadoRaspBerry_1;
    }

    public void setEstadoRaspBerry_1(boolean estadoRaspBerry_1) {
        this.estadoRaspBerry_1 = estadoRaspBerry_1;
    }

    public boolean isEstadoRaspBerry_2() {
        return estadoRaspBerry_2;
    }

    public void setEstadoRaspBerry_2(boolean estadoRaspBerry_2) {
        this.estadoRaspBerry_2 = estadoRaspBerry_2;
    }

    public String getDia_noite() {
        return dia_noite;
    }

    public void setDia_noite(String dia_noite) {
        this.dia_noite = dia_noite;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}