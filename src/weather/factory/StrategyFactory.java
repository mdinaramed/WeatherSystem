package weather.factory;
import weather.strategy.*;

public class StrategyFactory {
    public static DataUpdateStrategy create(StrategyMode mode) {
        return switch (mode) {
            case LIVE   -> new LiveSensorStrategy();
            case BATCH  -> new ScheduleBatchStrategy(3);
            case MANUAL -> new ManualEntryStrategy();
            case API    -> new ApiAdapterStrategy();
        };
    }
}