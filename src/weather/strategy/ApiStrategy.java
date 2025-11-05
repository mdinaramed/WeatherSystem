package weather.strategy;
import weather.external.ExternalWeatherApi;
import weather.main.WeatherData;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class ApiStrategy implements DataUpdateStrategy {
    private final ExternalWeatherApi api = new ExternalWeatherApi();

    @Override
    public String getName() {
        return "ApiAdapter";
    }
    @Override
    public List<WeatherData> fetch() {
        Map<String, Object>m = api.getSnapshot();
        double tempF = (double) m.get("temp_f");
        double hum = (double) m.get("rel_hum");
        double pressure = (double) m.get("pressure_mb");
        double windSpeed = (double) m.get("wind_kmh");
        long epochMs = (long) m.get("time");

        WeatherData data = new WeatherData.Builder()
                .timestamp(Instant.ofEpochMilli(epochMs))
                .temperatureC((tempF - 32) * 5 / 9)
                .humidity(hum)
                .pressure(pressure)
                .windSpeed(windSpeed / 3.6)
                .build();
        return List.of();
    }
}
