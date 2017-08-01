package fr.woorib.beacon.data;

/**
 * Created by Veryeld on 30/03/2017.
 */
public interface BeaconEntry {

    Integer getUserId();
    Double getLongitude();
    Double getLatitude();
    int getBeaconId();
    String getDescription();
}
