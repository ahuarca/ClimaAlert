package ar.utn.ba.ddsi.climaAlert.schedulers;

import ar.utn.ba.ddsi.climaAlert.dto.Clima;
import ar.utn.ba.ddsi.climaAlert.services.BuscadorDeClimasService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaAlertScheduler {
    private final BuscadorDeClimasService buscadorDeClimas;

    public ClimaAlertScheduler(BuscadorDeClimasService buscadorDeClimas) {
        this.buscadorDeClimas = buscadorDeClimas;
    }

    @Scheduled(fixedRate = 300000) // cada 5 minutos porque 300000 ms son 5 minutos
    public void consultarClima() {
       Clima clima = buscadorDeClimas.consultarTemperaturayHumedad();
       this.buscadorDeClimas.guardarClima(clima);
    }

    @Scheduled(fixedRate = 60000) //  cada 1 minuto analiza el ultimo clima guardado en el repositorio y genera alertas si es necesario
    public void generarAlertas(){
        buscadorDeClimas.analizarInformacionDeClimas();
    }


}
