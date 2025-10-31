package weather.main;
import weather.strategy.DataUpdateStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WeatherCenter {
    private final List<WeatherObserver> observers = new ArrayList<>();
    private DataUpdateStrategy strategy;

    private boolean verbose = true;
    public void setVerbose(boolean v) {
        this.verbose = v;
    }

    public WeatherCenter(DataUpdateStrategy initial) {
        this.strategy = Objects.requireNonNull(initial);
    }
    public void setStrategy(DataUpdateStrategy s) {
        System.out.println();
        System.out.println("_______________________________________________");
        System.out.println("Center Switch strategy -> " + s.getName());
        System.out.println("_______________________________________________");
        this.strategy = Objects.requireNonNull(s);
    }
    public void addObserver(WeatherObserver o) {
        observers.add(o);
    }
    public void removeObserver(WeatherObserver o) {
        observers.remove(o);
    }
    public void refresh() {
        var updates = strategy.fetch();

        if (updates.isEmpty()) {
            System.out.println("No new weather data from source: " + strategy.getName());
            return;
        }

        int i = 1;
        for (WeatherData d : updates) {
            if (verbose) {
                double windKmh = toKmh(d.windSpeed);
                double feels   = feelsLikeC(d.temperatureC, d.humidity, windKmh);

                System.out.printf("%nUpdate id-%d at %s%n", i, d.timestamp);
                System.out.printf("→ Temperature: %.1f°C (%.1f°F), feels like: %.1f°C%n",
                        d.temperatureC, toF(d.temperatureC), feels);
                System.out.printf("→ Humidity: %.0f%%%n", d.humidity);
                System.out.printf("→ Pressure: %.0f hPa%n", d.pressure);
                System.out.printf("→ Wind: %.1f m/s (%.1f km/h)%n", d.windSpeed, windKmh);
                System.out.println("_______________________________________________");
            }
            for (WeatherObserver o : observers) o.onUpdate(d);
            i++;
        }
        if (verbose) System.out.println(" End of " + strategy.getName() + " updates.\n");
    }
    // helpers

    private static double toF(double c)    {
        return c * 9.0 / 5.0 + 32.0;
    }
    private static double toKmh(double ms) {
        return ms * 3.6;
    }

    private static double feelsLikeC(double tC, double rh, double vKmh) {
        if (tC >= 27.0 && rh >= 40.0) return heatIndexC(tC, rh);
        if (tC <= 10.0 && vKmh >= 5.0) return windChillC(tC, vKmh);
        return tC;
    }
    private static double heatIndexC(double tC, double rh) {
        double tF = tC * 9.0/5.0 + 32.0;
        double hiF = -42.379 + 2.04901523*tF + 10.14333127*rh
                - 0.22475541*tF*rh - 0.00683783*tF*tF - 0.05481717*rh*rh
                + 0.00122874*tF*tF*rh + 0.00085282*tF*rh*rh - 0.00000199*tF*tF*rh*rh;
        return (hiF - 32.0) * 5.0/9.0;
    }
    private static double windChillC(double tC, double vKmh) {
        return 13.12 + 0.6215*tC - 11.37*Math.pow(vKmh, 0.16)
                + 0.3965*tC*Math.pow(vKmh, 0.16);
    }
}