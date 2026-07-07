package ar.utn.ba.ddsi.climaAlert.services;

import ar.utn.ba.ddsi.climaAlert.dto.Clima;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EmailService {

        @Value("${twilio.auth.token}")
        private String apiKey;

        @Value("${twilio.from.email}")
        private String fromEmail;

        private final List<String> destinatarios = List.of(
                "admin@clima.com",
                "emergencias@clima.com",
                "meteorologia@clima.com",
                "ahuarca@frba.utn.edu.ar"
        );


        public void enviarAlerta(Clima clima) {
                Email from = new Email(fromEmail);

                String asunto = "CLIMALERT: Alerta Critica";


                String mensajeTexto = clima.mensajeDetalladoDelClima();
                Content contenido = new Content("text/plain", mensajeTexto);

                Mail mail = new Mail();
                mail.setFrom(from);
                mail.setSubject(asunto);
                mail.addContent(contenido);


                Personalization personalization = new Personalization();
                for (String destino : destinatarios) {
                    personalization.addTo(new Email(destino));
                }
                mail.addPersonalization(personalization);


                SendGrid sg = new SendGrid(apiKey);
                Request request = new Request();

                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());

                    Response response = sg.api(request);

                    if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                        System.out.println("Mail real enviado con éxito.Código de estado " + response.getStatusCode());
                    } else {
                        System.err.println("El servidor rechazó el mail. Código de error: " + response.getStatusCode());

                    }
                } catch (IOException ex) {
                    System.err.println("Error de red al conectar con Twilio: " + ex.getMessage());
                }
        }


}


