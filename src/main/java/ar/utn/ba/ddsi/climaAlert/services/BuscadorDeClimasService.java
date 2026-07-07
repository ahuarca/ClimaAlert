package ar.utn.ba.ddsi.climaAlert.services;

import ar.utn.ba.ddsi.climaAlert.config.RestWeatherProperties;
import ar.utn.ba.ddsi.climaAlert.dto.Clima;
import ar.utn.ba.ddsi.climaAlert.repositories.ClimasRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class BuscadorDeClimasService {

    private final RestTemplate restTemplate;
    private final RestWeatherProperties propiedades;
    private ClimasRepository repository;
    private EmailService emailService;

    public BuscadorDeClimasService(RestTemplate restTemplate, RestWeatherProperties propiedades, ClimasRepository repository, EmailService emailService) {
        this.restTemplate = restTemplate;
        this.propiedades = propiedades;
        this.repository = repository;
        this.emailService = emailService;
    }

    public Clima consultarTemperaturayHumedad() {

        URI uri = UriComponentsBuilder.fromUriString(propiedades.getUrl())
                .path("/current.json")
                .queryParam("key", propiedades.getKey())
                .queryParam("q", propiedades.getLocation())
                .queryParam("aqi", "no")
                .build()
                .toUri();

        try {
            Clima respuestaApi = restTemplate.getForObject(uri, Clima.class);

            if (respuestaApi != null) {
                System.out.println("Temperatura actual: " + respuestaApi.temperatura());
                System.out.println("Humedad actual: " + respuestaApi.humedad());
            }
            return respuestaApi;

        } catch (Exception e) {
            System.err.println("Error al consultar WeatherAPI: " + e.getMessage());
            return null;
        }
    }

    public void guardarClima(Clima clima) {
        if (clima != null) {
            repository.save(clima);

        }
    }

    public void analizarInformacionDeClimas(){
        Long id = this.repository.findAlll().size() - 1L;

        Clima clima = this.repository.findById(id);

        if(clima.temperatura() > 0.0){
            this.emailService.enviarAlerta(clima);
        }
        if(clima.humedad() > 0.0){
            this.emailService.enviarAlerta(clima);
        }
    }



}

