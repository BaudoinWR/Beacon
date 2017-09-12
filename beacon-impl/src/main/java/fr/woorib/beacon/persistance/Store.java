package fr.woorib.beacon.persistance;

import java.util.List;
import fr.woorib.beacon.data.BeaconEntry;

/**
 * Created by Veryeld on 30/03/2017.
 */
public interface Store {
    Integer saveBeacon(Integer userId, Double latitude, Double longitude);

    BeaconEntry getBeaconByBeaconId(Integer beaconId);

    List<BeaconEntry> getBeaconByUserId(Integer userId);
}
