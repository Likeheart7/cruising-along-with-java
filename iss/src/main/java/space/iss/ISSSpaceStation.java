package space.iss;

import space.SpaceStation;
import space.SpaceStationInfo;
import space.iss.info.ISSLocation;
import space.iss.info.ISSPeople;

public class ISSSpaceStation implements SpaceStation {
    @Override
    public SpaceStationInfo lookup() {
        return new SpaceStationInfo(
            new ISSLocation().lookupLocation(),
            new ISSPeople().lookupPeople()
        );
    }
}
