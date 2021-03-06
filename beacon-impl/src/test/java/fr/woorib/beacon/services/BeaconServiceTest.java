package fr.woorib.beacon.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.Store;

/**
 * Created by Veryeld on 01/04/2017.
 */
public class BeaconServiceTest {
    private static final BigDecimal LATITUDE = new BigDecimal(45.2);
    private static final BigDecimal LONGITUDE = new BigDecimal(3.7);

    private static BeaconService beaconService;
    private static StoreTest store;

    @Before
    public void setup(){
        store = new StoreTest();
        beaconService = new BeaconServiceImpl(store);
    }

    @Test
    public void testSetBeacon() {
        beaconService.setBeacon(17, LATITUDE,LONGITUDE);
        Assert.assertEquals(1, store.entries.size());
    }


    @Test
    public void testGetBeacon() {
        beaconService.setBeacon(5,LATITUDE,LONGITUDE);
        BeaconEntry beacon  = beaconService.getBeacon(0);
        Assert.assertEquals(0, beacon.getBeaconId());
        Assert.assertEquals(5, (int) beacon.getUserId());
        Assert.assertEquals(LONGITUDE, beacon.getLongitude());
        Assert.assertEquals(LATITUDE, beacon.getLatitude());
    }

    @Test
    public void testGetUserBeacons() {
        beaconService.setBeacon(3, LATITUDE,LONGITUDE);
        beaconService.setBeacon(3, LATITUDE,LONGITUDE);
        beaconService.setBeacon(3, LATITUDE,LONGITUDE);
        beaconService.setBeacon(1, LATITUDE,LONGITUDE);
        beaconService.setBeacon(1, LATITUDE,LONGITUDE);
        Assert.assertEquals(2, beaconService.getUserBeacons(1).size());
        Assert.assertEquals(3, beaconService.getUserBeacons(3).size());
    }

    private static class StoreTest implements Store {
        Integer id = 0;
        public List<BeaconEntry> entries = new ArrayList<BeaconEntry>();

        public Integer saveBeacon(final Integer userId, final BigDecimal latitude, final BigDecimal longitude) {
            final int beaconId = id++;
            entries.add(new BeaconEntry() {
                public Integer getUserId() {
                    return userId;
                }

                public BigDecimal getLongitude() {
                    return longitude;
                }

                public BigDecimal getLatitude() {
                    return latitude;
                }

                public int getBeaconId() {
                    return beaconId;
                }
            });
          return userId;
        }

        public BeaconEntry getBeaconByBeaconId(Integer beaconId) {
            for (BeaconEntry beacon:
                 entries) {
                if (beaconId == beacon.getBeaconId())
                    return beacon;
            }
            return null;
        }

        public List<BeaconEntry> getBeaconByUserId(Integer userId) {
            List<BeaconEntry> results = new ArrayList<BeaconEntry>();
            for (BeaconEntry beacon:
                    entries) {
                if (userId == beacon.getUserId())
                    results.add(beacon);
            }
            return results;
        }
    }
}
