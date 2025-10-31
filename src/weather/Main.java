package weather;

import weather.composite.CityGroup;
import weather.composite.CityStation;
import weather.facade.WeatherSystem;
import weather.factory.StrategyFactory;
import weather.factory.StrategyMode;
import weather.main.WeatherCenter;
import weather.main.WeatherData;
import weather.observer.CityFeedObserver;
import weather.observer.MultiCityAggregator;
import weather.strategy.ManualEntryStrategy;


public class Main {
    public static void main(String[] args) {
        WeatherCenter center = WeatherSystem.defaultCenter();

        // Live
        center.refresh();

        // Batch
        center.setStrategy(StrategyFactory.create(StrategyMode.BATCH));
        center.refresh();

        // Api adapter
        center.setStrategy(StrategyFactory.create(StrategyMode.API));
        center.refresh();

        // Manual
        ManualEntryStrategy manual = (ManualEntryStrategy) StrategyFactory.create(StrategyMode.MANUAL);
        center.setStrategy(manual);
        manual.submit(new WeatherData.Builder()
                .temperatureC(38).humidity(20).pressure(988).windSpeed(14).build());
        center.refresh();

        System.out.println("\nMULTI-CITY ");

        CityStation astana = new CityStation("Astana",
                new WeatherCenter(StrategyFactory.create(StrategyMode.BATCH)));
        CityStation almaty = new CityStation("Almaty",
                new WeatherCenter(StrategyFactory.create(StrategyMode.BATCH)));
        CityStation shymkent = new CityStation("Shymkent",
                new WeatherCenter(StrategyFactory.create(StrategyMode.BATCH)));

        MultiCityAggregator aggregator = new MultiCityAggregator();
        astana.addObserver(new CityFeedObserver("Astana", aggregator));
        almaty.addObserver(new CityFeedObserver("Almaty", aggregator));
        shymkent.addObserver(new CityFeedObserver("Shymkent", aggregator));

        CityGroup kz = new CityGroup("Kazakhstan Cities")
                .add(astana).add(almaty).add(shymkent);
        astana.setVerbose(false);
        almaty.setVerbose(false);
        shymkent.setVerbose(false);

        kz.refresh();
        System.out.print("info: ");
        aggregator.printCombinedLine();

    }
}