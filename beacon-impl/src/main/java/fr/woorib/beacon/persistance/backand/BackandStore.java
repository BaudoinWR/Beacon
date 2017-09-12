package fr.woorib.beacon.persistance.backand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import fr.woorib.backand.client.BackandClientImpl;
import fr.woorib.backand.client.api.BackandClient;
import fr.woorib.backand.client.exception.BackandException;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.Store;
import fr.woorib.beacon.persistance.entity.Beacon;
import fr.woorib.beacon.persistance.entity.User;

/**
 * Created by Veryeld on 28/07/2017.
 */
public class BackandStore implements Store {
    private final BackandClient client;

    public BackandStore(String username, String password) throws BackandException {
        client = BackandClientImpl.get();
        client.establishConnection(username, password,"beecon");
    }

    public Integer saveBeacon(Integer userId, Double latitude, Double longitude) {
      try {
            Beacon beacon = new Beacon();
            beacon.setOwner(client.retrieveBackandObjectFromId(userId, User.class));
            beacon.setLatitude(latitude);
            beacon.setLongitude(longitude);
            Beacon result = client.insertNewObject(beacon);
            return result.getId();
        } catch (BackandException e) {
            e.printStackTrace();
        }
      return null;
    }

    public BeaconEntry getBeaconByBeaconId(Integer beaconId) {
        try {
            Beacon beacon = client.retrieveBackandObjectFromId(beaconId, Beacon.class);
            return Beacon.getBeaconEntry(beacon);
        } catch (BackandException e) {
            e.printStackTrace();
        }
        return null;
    }

  public List<BeaconEntry> getBeaconByUserId(Integer userId) {
        List<BeaconEntry> result = new ArrayList<BeaconEntry>();
        try {
            User user = client.retrieveBackandObjectFromId(userId, User.class);
            Collection<Beacon> beacons = user.getBeacons();
            for (Beacon beacon: beacons) {
                result.add(Beacon.getBeaconEntry(beacon));
            }
        } catch (BackandException e) {
            e.printStackTrace();
        }
        return result;
    }
}
