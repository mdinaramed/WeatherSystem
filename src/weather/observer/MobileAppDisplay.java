package weather.observer;
import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class MobileAppDisplay implements WeatherObserver {
    private final String user;
    public MobileAppDisplay(String user) {
        this.user = user;
    }
    @Override
    public void onUpdate(WeatherData d) {
        System.out.printf("📲Mobile:%s %.1f°C (%.1f°F), hum %.0f%%, wind %.1f km/h%n",
                user, d.temperatureC, d.temperatureF(), d.humidity, d.windSpeed());
    }
}
