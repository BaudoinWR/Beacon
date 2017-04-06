package fr.woorib.beacon.data;

/**
 * Created by Veryeld on 30/03/2017.
 */
public class BeaconEntry {

    private Integer beaconId;
    private Integer userId;
    private Double longitude;
    private Double latitude;

    public BeaconEntry(Integer userId, Double latitude, Double longitude) {
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public BeaconEntry(Integer beaconId, Integer userId, Double latitude, Double longitude) {
        this.beaconId = beaconId;
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public Integer getUserId() {
        return userId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public int getBeaconId() {
        return beaconId;
    }

    @Override
    public String toString() {
        return "BeaconEntry{" +
                "beaconId=" + beaconId +
                ", userId=" + userId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
