package ar.utn.ba.ddsi.climaAlert.services;

import ar.utn.ba.ddsi.climaAlert.dto.Clima;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClimaRunner implements CommandLineRunner {

    private final BuscadorDeClimasService buscadorDeClimas;


    public ClimaRunner(BuscadorDeClimasService buscadorDeClimas) {
        this.buscadorDeClimas = buscadorDeClimas;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Conexión exitosa con WeatherAPI");
        Clima clima = buscadorDeClimas.consultarTemperaturayHumedad();

    }
}
