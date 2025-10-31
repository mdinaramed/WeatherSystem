package weather.observer;
import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class OfficeWallDisplay implements WeatherObserver {
    private final String location;
    public OfficeWallDisplay(String location){
        this.location = location;
    }

    @Override
    public void onUpdate(WeatherData d) {
        System.out.printf("⎚Wall:%s %s | %.1f°C | %.0f%% | %.0f hPa | %.1f m/s%n",
                location, d.timestamp, d.temperatureC, d.humidity, d.pressure, d.windSpeed);
    }
}
