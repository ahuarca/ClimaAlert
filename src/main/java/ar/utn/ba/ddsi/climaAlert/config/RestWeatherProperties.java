package ar.utn.ba.ddsi.climaAlert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather.api")
@Data
public class RestWeatherProperties {
    private String url;
    private String key;
    private String location;
}
