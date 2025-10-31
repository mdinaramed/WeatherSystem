package weather.composite;
import weather.main.WeatherObserver;
import weather.strategy.DataUpdateStrategy;

import java.util.ArrayList;
import java.util.List;

public class CityGroup {
    private final String name;
    private final List<CityStation> children = new ArrayList<>();

    public CityGroup(String name) {
        this.name = name;
    }
    public CityGroup add(CityStation node) {
        children.add(node);
        return this;
    }
    public void setStrategy(DataUpdateStrategy strategy) {
        for (var n : children) n.setStrategy(strategy);
    }
    public void addObserver(WeatherObserver observer) {
        for (var n : children) n.addObserver(observer);
    }
    public void refresh(){
        for (var n : children) n.refresh();
    }
}
