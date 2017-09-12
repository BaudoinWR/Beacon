package fr.woorib.beacon.persistance;

import java.math.BigDecimal;
import java.util.List;
import fr.woorib.beacon.data.BeaconEntry;

/**
 * Created by Veryeld on 30/03/2017.
 */
public interface Store {
    Integer saveBeacon(Integer userId, BigDecimal latitude, BigDecimal longitude);

    BeaconEntry getBeaconByBeaconId(Integer beaconId);

    List<BeaconEntry> getBeaconByUserId(Integer userId);
}
