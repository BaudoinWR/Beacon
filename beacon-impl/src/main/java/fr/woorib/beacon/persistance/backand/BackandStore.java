package fr.woorib.beacon.persistance.backand;

import fr.woorib.backand.client.BackandClientImpl;
import fr.woorib.backand.client.api.BackandClient;
import fr.woorib.backand.client.exception.BackandException;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.Store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Veryeld on 28/07/2017.
 */
public class BackandStore implements Store {
    private final BackandClient client;

    public BackandStore(String username, String password) throws BackandException {
        client = BackandClientImpl.get();
        client.establishConnection(username, password,"beecon");
    }

    public void save(Integer userId, Double latitude, Double longitude) {
        try {
            Beacon beacon = new Beacon();
            beacon.setOwner(client.retrieveBackandObjectFromId(userId, User.class));
            beacon.setLatitude(latitude);
            beacon.setLongitude(longitude);
            client.insertNewObject(beacon);
        } catch (BackandException e) {
            e.printStackTrace();
        }
    }

    public BeaconEntry getBeaconByBeaconId(Integer beaconId) {
        try {
            Beacon beacon = client.retrieveBackandObjectFromId(beaconId, Beacon.class);
            return getBeaconEntry(beacon);
        } catch (BackandException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BeaconEntry getBeaconEntry(final Beacon beacon) {
        return new BeaconEntry() {
            public Integer getUserId() {
                return beacon.getOwner().getId();
            }

            public Double getLongitude() {
                return beacon.getLongitude();
            }

            public Double getLatitude() {
                return beacon.getLatitude();
            }

            public int getBeaconId() {
                return beacon.getId();
            }

            @Override
            public String toString() {
                return beacon.toString();
            }
        };
    }

    public List<BeaconEntry> getBeaconByUserId(Integer userId) {
        List<BeaconEntry> result = new ArrayList<BeaconEntry>();
        try {
            User user = client.retrieveBackandObjectFromId(userId, User.class);
            Collection<Beacon> beacons = user.getBeacons();
            for (Beacon beacon: beacons) {
                result.add(getBeaconEntry(beacon));
            }
        } catch (BackandException e) {
            e.printStackTrace();
        }
        return result;
    }
}
