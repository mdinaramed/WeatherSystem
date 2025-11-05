package weather.observer;

import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class AlertDecorator implements WeatherObserver {
    private final WeatherObserver observer;
    private final double maxTempC;
    private final double maxWindSpeed;
    private final double minPressure;

    public AlertDecorator(WeatherObserver observer, double maxTempC, double maxWindSpeed, double minPressure) {
        this.observer = observer;
        this.maxTempC = maxTempC;
        this.maxWindSpeed = maxWindSpeed;
        this.minPressure = minPressure;
    }

    @Override
    public void onUpdate(WeatherData data) {
        observer.onUpdate(data);

        if (data.temperatureC >= maxTempC) {
            System.out.println("Heat alert! Temperature is too high.");
        }
        if (data.windSpeed >= maxWindSpeed) {
            System.out.println("Wind alert! Wind speed is too high.");
        }
        if (data.pressure <= minPressure) {
            System.out.println("Low pressure alert!");
        }
    }
}