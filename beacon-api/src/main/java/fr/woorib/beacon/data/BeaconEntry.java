package fr.woorib.beacon.data;

import java.math.BigDecimal;

/**
 * Created by Veryeld on 30/03/2017.
 */
public interface BeaconEntry {

    public Integer getUserId();

    public BigDecimal getLongitude();

    public BigDecimal getLatitude();

    public int getBeaconId();

}
