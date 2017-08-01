package fr.woorib.beacon.services;

import fr.woorib.backand.client.exception.BackandException;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.Store;
import fr.woorib.beacon.persistance.backand.BackandStore;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the BeaconService interface
 */
public class BeaconServiceImpl implements BeaconService {

    Store store;

    public BeaconServiceImpl(Store store) {
        this.store = store;
    }

    public void setBeacon(Integer userId, Double latitude, Double longitude) {
        store.save(userId, latitude, longitude);
    }

    public BeaconEntry getBeacon(Integer id) {
        return store.getBeaconByBeaconId(id);
    }

    public List<BeaconEntry> getUserBeacons(Integer userId) {
        return store.getBeaconByUserId(userId);
    }

    public static void main(String[] args) throws BackandException {
        Handler fh = new ConsoleHandler();
        fh.setLevel (Level.FINEST);
        Logger logger = Logger.getLogger("fr.woorib");
        logger.addHandler(fh);
        logger.setLevel (Level.FINEST);

        BeaconServiceImpl bsi = new BeaconServiceImpl(new BackandStore(args[0], args[1]));
        BeaconEntry beacon = bsi.getBeacon(7);
        System.out.println(beacon);
        System.out.println(bsi.getUserBeacons(1));
    }

}