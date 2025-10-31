package weather.observer;
import weather.main.WeatherData;
import java.util.LinkedHashMap;
import java.util.Map;

public class MultiCityAggregator {
    private final Map<String, WeatherData> latest = new LinkedHashMap<>();
    public void accept(String city, WeatherData data) {
        latest.put(city, data);
    }
        public void printCombinedLine() {
            StringBuilder sb = new StringBuilder();
            for (var e : latest.entrySet()) {
                if (sb.length() > 0) sb.append(" | ");
                sb.append(e.getKey()).append(' ')
                        .append(String.format("%.1f°C", e.getValue().temperatureC));
            }
            System.out.println(sb);
        }
    }