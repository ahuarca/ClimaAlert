package ar.utn.ba.ddsi.climaAlert.dto;

import lombok.Data;

@Data
public class Email {
    String remitente;
    String destinatario;
    String mensaje;

    public Email(String remitente, String destinatario, String mensaje) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.mensaje = mensaje;
    }
}
