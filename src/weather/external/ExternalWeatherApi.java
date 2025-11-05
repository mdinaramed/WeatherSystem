package weather.external;
import java.time.Instant;
import java.util.Map;

public class ExternalWeatherApi {
    public Map<String, Object> getSnapshot(){
        return Map.of(
                "time", Instant.now().toEpochMilli(),
                "temp_f", 73.4,
                "rel_hum", 52.0,
                "pressure_mb", 1008.0,
                "wind_kmh", 12.6
        );
    }
}
