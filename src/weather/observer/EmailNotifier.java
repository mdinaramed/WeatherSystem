package weather.observer;
import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class EmailNotifier implements WeatherObserver {
    private final String email;

    public EmailNotifier(String email){
        this.email = email;
    }
    @Override
    public void onUpdate(WeatherData data) {
        System.out.println("📩Email->" + email + " Weather: " + data.temperatureC + "°C, hum " + data.humidity + "%, wind " + data.windSpeed + " m/s");

    }
}
