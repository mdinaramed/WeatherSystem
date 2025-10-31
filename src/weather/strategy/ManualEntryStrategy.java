package weather.strategy;
import weather.main.WeatherData;

import java.util.*;

public class ManualEntryStrategy implements DataUpdateStrategy {
    private final Deque<WeatherData> queue = new ArrayDeque<>();
    @Override
    public String getName() {
        return "ManualEntry";
    }
    public void submit(WeatherData d) {
        queue.addLast(d);
    }

    @Override
    public List<WeatherData> fetch() {
        List<WeatherData> out = new ArrayList<>();
        while (!queue.isEmpty())out.add(queue.removeFirst()); {}
        return out;
    }
}
