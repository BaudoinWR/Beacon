package fr.woorib.beacon.services;

import java.util.List;
import fr.woorib.beacon.data.BeaconEntry;

/**
 * Created by Veryeld on 29/03/2017.
 * Service interface to manage the beacons
 */
public interface BeaconService {
    /**
     * Save a beacon, linked to an user.
     * @param userId
     * @param latitude
     * @param longitude
     */
    Integer setBeacon(Integer userId, Double latitude, Double longitude);

    /**
     * Retrieve a beacon by it's ID
     * @param beaconId
     * @return
     */
    BeaconEntry getBeacon(Integer beaconId);

    /**
     * Retrieve all beacons for user userId
     * @param userId
     * @return
     */
    List<BeaconEntry> getUserBeacons(Integer userId);
}
