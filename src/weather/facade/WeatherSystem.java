package weather.facade;
import weather.main.WeatherCenter;
import weather.main.WeatherObserver;
import weather.factory.ModesFactory;
import weather.factory.Modes;
import weather.observer.*;

public class WeatherSystem {
    public static WeatherCenter defaultCenter() {
        WeatherCenter center = new WeatherCenter(ModesFactory.create(Modes.LIVE));
        WeatherObserver wall = new WallPanel("Expo-2017");
        WeatherObserver mobile = new MobileWidget("Dinara");
        WeatherObserver email = new EmailNotifier("yume@company.com");

        center.addObserver(new AlertDecorator(wall, 35.0, 12.0, 990.0));
        center.addObserver(mobile);
        center.addObserver(email);
        return center;
    }
}
