package weather.observer;
import weather.main.WeatherData;
import weather.main.WeatherObserver;

public class AlertDecorator implements WeatherObserver {
    private final WeatherObserver inner;
    private final double hiTempC, hiWindMps, lowPressureHpa;

    public AlertDecorator(WeatherObserver inner, double hiTempC, double hiWindMps, double lowPressureHpa) {
        this.inner = inner;
        this.hiTempC = hiTempC;
        this.hiWindMps = hiWindMps;
        this.lowPressureHpa = lowPressureHpa;
    }

    @Override public void onUpdate(WeatherData d) {
        inner.onUpdate(d);
        if (d.temperatureC >= hiTempC)    System.out.println("⚠️Heat alert!");
        if (d.windSpeed      >= hiWindMps)  System.out.println("⚠️Wind alert!");
        if (d.pressure  <= lowPressureHpa) System.out.println("⚠️Low pressure!");
    }
}