package weather.strategy;
import weather.main.WeatherData;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LiveStrategy implements DataUpdateStrategy{
    @Override
    public String getName() {
        return "LiveSensor";
    }
    @Override
    public List<WeatherData> fetch() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        WeatherData data = new WeatherData.Builder()
                .timestamp(Instant.now())
                .temperatureC(r.nextDouble(-25, 42))
                .humidity(r.nextDouble(10, 95))
                .pressure(r.nextDouble(980, 1035))
                .windSpeed(r.nextDouble(0, 18))
                .build();
        return List.of();
    }
}
