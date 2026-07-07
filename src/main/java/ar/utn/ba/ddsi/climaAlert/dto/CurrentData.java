package ar.utn.ba.ddsi.climaAlert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentData {

    @JsonProperty("temp_c")
    private double tempC;
    private double humidity;
    private double wind_mph;
    private double wind_kph;
    private double wind_degree;
    private String wind_dir;
    private double pressure_mb;
    private double pressure_in;
    private double precip_mm;
    private double precip_in;

}
