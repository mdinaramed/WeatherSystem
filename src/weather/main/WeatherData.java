package weather.main;
import java.time.Instant;

public class WeatherData {
    public final Instant timestamp;
    public final double temperatureC;
    public final double humidity;
    public final double pressure;
    public final double windSpeed;

    private WeatherData(Builder b) {
        this.timestamp = b.timestamp != null ? b.timestamp : Instant.now();
        this.temperatureC = b.temperatureC;
        this.humidity = b.humidity;
        this.pressure = b.pressure;
        this.windSpeed = b.windSpeed;
        validate();
    }
    private void validate() {
        if (Double.isNaN(temperatureC) || Double.isNaN(humidity) ||
                Double.isNaN(pressure) || Double.isNaN(windSpeed)) {
            throw new IllegalArgumentException("NaN not allowed");
        }
        if (humidity < 0 || humidity > 100) {
            throw new IllegalArgumentException("humidity must be between 0 and 100");
        }
        if (pressure < 300 || pressure > 1100) {
            throw new IllegalArgumentException("pressure must be between 300 and 1100");
        }
    }
    public double temperatureF() {
        return temperatureC * 9 / 5 + 32;
    }

    public double windSpeed() {
        return windSpeed * 3.6;
    }
    public double feelsLikeC() {
       double tC = this.temperatureC;
       double rh = this.humidity;
       double vKmh = this.windSpeed;

       if (tC >=27.0 && rh >=40.0) {
           return heatIndexC(tC, rh);
       }
        if (tC <= 10.0 && vKmh >= 5.0) {
            return windChillC(tC, vKmh);
        }
        return tC;
    }

    private static double heatIndexC(double tC, double rh) {
        double tF = tC * 9.0/5.0 + 32.0;
        double hiF = -42.379
                + 2.04901523 * tF
                + 10.14333127 * rh
                - 0.22475541 * tF * rh
                - 0.00683783 * tF * tF
                - 0.05481717 * rh * rh
                + 0.00122874 * tF * tF * rh
                + 0.00085282 * tF * rh * rh
                - 0.00000199 * tF * tF * rh * rh;
        return (hiF - 32.0) * 5.0/9.0;
    }
    private static double windChillC(double tC, double vKmh) {
        return 13.12 + 0.6215 * tC - 11.37 * Math.pow(vKmh, 0.16)
                + 0.3965 * tC * Math.pow(vKmh, 0.16);
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "t=" + timestamp +
                ", temp=" + String.format("%.1f", temperatureC) +
                ", hum%=" + String.format("%.0f", humidity) +
                ", p=" + String.format("%.0f", pressure) +
                ", wind=" + String.format("%.1f", windSpeed) +
                '}';
    }

    public static class Builder { private Instant timestamp;
        private double temperatureC, humidity, pressure, windSpeed;

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        public Builder temperatureC(double temperatureC) {
            this.temperatureC = temperatureC;
            return this;
        }
        public Builder humidity(double humidity) {
            this.humidity = humidity;
            return this;
        }
        public Builder pressure(double pressure) {
            this.pressure = pressure;
            return this;
        }
        public Builder windSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }
        public WeatherData build() {
            return new WeatherData(this);
        }
    }

}
