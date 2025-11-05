package weather.observer;
import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class WallPanel implements WeatherObserver {
    private final String location;

    public WallPanel(String location){
        this.location = location;
    }
    @Override
    public void onUpdate(WeatherData data) {
        System.out.println("Wall:" + location + " " + data.timestamp + " | " + data.temperatureC + "°C | " + data.humidity + "% | " + data.pressure + " hPa | " + data.windSpeed + " m/s");
    }
}
