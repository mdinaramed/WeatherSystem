package weather.strategy;
import weather.main.WeatherData;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BatchStrategy implements DataUpdateStrategy {
    private final int batch;
    public BatchStrategy(int batch) {
        this.batch = Math.max(1, batch);
    }
    @Override
    public String getName() {
        return "Batch(" + batch + ")";
    }
    @Override
    public List<WeatherData> fetch() {
        List <WeatherData> list = new ArrayList<>(batch);
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < this.batch; i++) {
            list.add(new WeatherData.Builder()
                    .timestamp(Instant.now().minusSeconds((long)(batch - i) * 60))
                    .temperatureC(r.nextDouble(-20, 40))
                    .humidity(r.nextDouble(20, 90))
                    .pressure(r.nextDouble(985, 1030))
                    .windSpeed(r.nextDouble(0, 12))
                    .build());
        }
        return list;
    }
}
