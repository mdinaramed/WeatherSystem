package weather.observer;
import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class CityFeedObserver implements WeatherObserver {
    private final String city;
    private final MultiCityAggregator aggregator;
    private final boolean quiet;

    public CityFeedObserver(String city, MultiCityAggregator aggregator) {
        this(city, aggregator, true);
    }

    public CityFeedObserver(String city, MultiCityAggregator aggregator, boolean quiet) {
        this.city = city;
        this.aggregator = aggregator;
        this.quiet = quiet;
    }

    @Override
    public void onUpdate(WeatherData d) {
        aggregator.accept(city, d);
        if (!quiet) {
            System.out.printf("[%s] %.1f°C, %.0f%%, %.0f hPa, %.1f m/s%n",
                    city, d.temperatureC, d.humidity, d.pressure, d.windSpeed);
        }
    }
}