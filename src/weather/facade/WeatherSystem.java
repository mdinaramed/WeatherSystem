package weather.facade;

import weather.main.WeatherCenter;
import weather.main.WeatherObserver;
import weather.factory.StrategyFactory;
import weather.factory.StrategyMode;
import weather.observer.*;

public class WeatherSystem {
    public static WeatherCenter defaultCenter() {
        WeatherCenter center = new WeatherCenter(StrategyFactory.create(StrategyMode.LIVE));

        WeatherObserver wall = new OfficeWallDisplay("Expo-2017");
        WeatherObserver mobile = new MobileAppDisplay("Dinara");
        WeatherObserver email = new EmailService("yume@company.com");

        center.addObserver(new AlertDecorator(wall, 35.0, 12.0, 990.0));
        center.addObserver(mobile);
        center.addObserver(email);
        return center;
    }
}
