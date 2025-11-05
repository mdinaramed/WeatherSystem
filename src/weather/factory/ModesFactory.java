package weather.factory;
import weather.strategy.*;

public class ModesFactory {
    public static DataUpdateStrategy create(Modes mode) {
        return switch (mode) {
            case LIVE   -> new LiveStrategy();
            case BATCH  -> new BatchStrategy(3);
            case MANUAL -> new ManualStrategy();
            case API    -> new ApiStrategy();
        };
    }
}