package fr.woorib.beacon.services;

import java.util.List;
import fr.woorib.backand.client.exception.BackandException;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.Store;
import fr.woorib.beacon.persistance.backand.BackandStore;

/**
 * Implementation of the BeaconService interface
 */
public class BeaconServiceImpl implements BeaconService {

    Store store;

    public BeaconServiceImpl(Store store) {
        this.store = store;
    }

    public Integer setBeacon(Integer userId, Double latitude, Double longitude) {
        return store.saveBeacon(userId, latitude, longitude);
    }

    public BeaconEntry getBeacon(Integer id) {
        return store.getBeaconByBeaconId(id);
    }

    public List<BeaconEntry> getUserBeacons(Integer userId) {
        return store.getBeaconByUserId(userId);
    }

    public static void main(String[] args) throws BackandException {
        BeaconServiceImpl bsi = new BeaconServiceImpl(new BackandStore(args[0], args[1]));
        BeaconEntry beacon = bsi.getBeacon(7);
        System.out.println(beacon);
        System.out.println(bsi.getUserBeacons(1));
    }

}