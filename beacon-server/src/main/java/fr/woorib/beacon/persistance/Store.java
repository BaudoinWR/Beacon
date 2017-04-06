package fr.woorib.beacon.persistance;

import fr.woorib.beacon.data.BeaconEntry;

import java.util.List;

/**
 * Created by Veryeld on 30/03/2017.
 */
public interface Store {
    void save(BeaconEntry beaconEntry);

    BeaconEntry getBeaconByBeaconId(Integer beaconId);

    List<BeaconEntry> getBeaconByUserId(Integer userId);
}
