package weather.strategy;
import weather.main.WeatherData;
import java.util.List;

public interface DataUpdateStrategy {
    String getName();
    List<WeatherData> fetch();
}
