package weather;
import weather.facade.WeatherSystem;
import weather.factory.ModesFactory;
import weather.factory.Modes;
import weather.main.WeatherCenter;
import weather.main.WeatherData;
import weather.strategy.ManualStrategy;

public class Main {
    public static void main(String[] args) {
        WeatherCenter center = WeatherSystem.defaultCenter();
        center.refresh();//лайв
        center.setStrategy(ModesFactory.create(Modes.BATCH));
        center.refresh();//батч
        center.setStrategy(ModesFactory.create(Modes.API));
        center.refresh();//api

        ManualStrategy manual = (ManualStrategy) ModesFactory.create(Modes.MANUAL);
        center.setStrategy(manual);
        manual.submit(new WeatherData.Builder()
                .temperatureC(38)
                .humidity(20)
                .pressure(988)
                .windSpeed(14)
                .build());
        center.refresh();
        System.out.println("\nEND OF WEATHER SYSTEM");
    }
}