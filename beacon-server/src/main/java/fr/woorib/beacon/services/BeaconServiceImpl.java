package fr.woorib.beacon.services;

import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.Store;
import fr.woorib.beacon.persistance.StoreHSQL;

import java.util.List;

/**
 * Implementation of the BeaconService interface
 */
public class BeaconServiceImpl implements BeaconService {

    Store store;

    public BeaconServiceImpl(Store store) {
        this.store = store;
    }

    public void setBeacon(Integer userId, Double latitude, Double longitude) {
        store.save(new BeaconEntry(userId, latitude, longitude));
    }

    public BeaconEntry getBeacon(Integer id) {
        return store.getBeaconByBeaconId(id);
    }

    public List<BeaconEntry> getUserBeacons(Integer userId) {
        return store.getBeaconByUserId(userId);
    }

    public static void main(String[] args) {
        BeaconServiceImpl bsi = new BeaconServiceImpl(new StoreHSQL());
        BeaconEntry beacon = bsi.getBeacon(0);
        System.out.println(beacon);
        System.out.println(bsi.getUserBeacons(1));
    }

}