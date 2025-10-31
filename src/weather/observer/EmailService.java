package weather.observer;
import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class EmailService implements WeatherObserver {
    private final String email;
    public EmailService(String email){
        this.email = email;
    }

    @Override
    public void onUpdate(WeatherData d) {
        System.out.printf("📩Email->%s Weather: %.1f°C, hum %.0f%%, wind %.1f m/s%n",
                email, d.temperatureC, d.humidity, d.windSpeed);

    }
}
