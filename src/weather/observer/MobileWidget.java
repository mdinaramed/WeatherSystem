package weather.observer;
import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class MobileWidget implements WeatherObserver {
    private final String user;

    public MobileWidget(String user) {
        this.user = user;
    }
    @Override
    public void onUpdate(WeatherData data) {
        System.out.println("📲 Mobile:" + user + " " + data.temperatureC + "°C (" + data.temperatureF() + "°F), " + "hum " + data.humidity + "%, " + "wind " + data.windSpeed() + " km/h");
    }
}
