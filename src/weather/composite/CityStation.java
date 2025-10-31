package weather.composite;

import weather.main.WeatherCenter;
import weather.main.WeatherObserver;
import weather.strategy.DataUpdateStrategy;

public class CityStation {
    private final String city;
    private final WeatherCenter center;

    public CityStation(String city, WeatherCenter center) {
        this.city = city;
        this.center = center;
    }
    public String getCity() {
        return city;
    }
    public WeatherCenter getCenter() {
        return center;
    }
    public void setStrategy(DataUpdateStrategy strategy) {
        center.setStrategy(strategy);
    }
    public void addObserver(WeatherObserver observer) {
        center.addObserver(observer);
    }
    public void refresh() {
        center.refresh();
    }
    public void setVerbose(boolean v) {
        center.setVerbose(v);
    }
}
