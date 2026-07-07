package ar.utn.ba.ddsi.climaAlert;

import ar.utn.ba.ddsi.climaAlert.config.RestWeatherProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(RestWeatherProperties.class)
public class ClimalertApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClimalertApplication.class, args);
    }
}
