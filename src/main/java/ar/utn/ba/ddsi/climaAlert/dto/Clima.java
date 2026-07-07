package ar.utn.ba.ddsi.climaAlert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clima {
    private Long id;
    private CurrentData current;

    public double temperatura() {
        return current != null ? current.getTempC() : 0.0;
    }
    public double humedad() {
        return current != null ? current.getHumidity() : 0.0;
    }


    public String mensajeDetalladoDelClima() {
            if (current == null) {
                return "Datos del clima no disponibles.";
            }

            String mensaje = "CLIMA \n " +
                    "- Temperatura: %.2f°C\n" +
                    "- Humedad: %.2f%%\n" +
                    "- Viento: %.2f MPH (%.2f KPH) con dirección '%s' (%s°)\n" +
                    "- Presión: %.2f mb (%.2f in)\n" +
                    "- Precipitación: %.2f mm (%.2f in)";

            return String.format(mensaje,
                    current.getTempC(),
                    current.getHumidity(),
                    current.getWind_mph(),
                    current.getWind_kph(),
                    current.getWind_dir(),
                    String.valueOf(current.getWind_degree()),
                    current.getPressure_mb(),
                    current.getPressure_in(),
                    current.getPrecip_in(),
                    current.getPrecip_mm()
            );

    }
}