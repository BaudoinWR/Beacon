package fr.woorib.beacon.persistance.hsql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.Store;

/**
 * Created by Veryeld on 30/03/2017.
 */
public class HsqlStore implements Store {
    Connection c;

    public HsqlStore() {
        try {
            c = DriverManager.getConnection("jdbc:hsqldb:file:d:/beacondb/beacdb", "SA", "pipou");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer saveBeacon(Integer userId, BigDecimal latitude, BigDecimal longitude) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("insert into Beacon(userid, latitude, longitude) values (?, ?, ?)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setBigDecimal(2, latitude);
            preparedStatement.setBigDecimal(3, longitude);
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            return Integer.valueOf(generatedKeys.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }

    public BeaconEntry getBeaconByBeaconId(Integer id) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("select beaconId, userId, latitude, longitude from beacon where beaconId = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BeaconEntry beaconEntry = extractBeacon(resultSet);
                return beaconEntry;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BeaconEntry extractBeacon(ResultSet resultSet) throws SQLException {
        final int beaconid = resultSet.getInt(1);
        final int userid = resultSet.getInt(2);
        final BigDecimal latitude = resultSet.getBigDecimal(3);
        final BigDecimal longitude = resultSet.getBigDecimal(4);

        return new BeaconEntry() {
            public Integer getUserId() {
                return userid;
            }

            public BigDecimal getLongitude() {
                return longitude;
            }

            public BigDecimal getLatitude() {
                return latitude;
            }

            public int getBeaconId() {
                return beaconid;
            }
        };
    }

    public List<BeaconEntry> getBeaconByUserId(Integer userId) {
        List<BeaconEntry> results = new ArrayList<BeaconEntry>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("select beaconId, userId, latitude, longitude from beacon where userId = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                results.add(extractBeacon(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    private void setupDB() {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("drop table if exists beacon");
            preparedStatement.execute();
            Statement statement = c.createStatement();
            statement.execute("create table beacon(" +
                    "beaconId integer identity primary key," +
                    "userId integer," +
                    "latitude double," +
                    "longitude double);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void shutdown() {
        try {
            c.createStatement().execute("shutdown");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        HsqlStore hsqlStore = new HsqlStore();
        hsqlStore.setupDB();
        hsqlStore.saveBeacon(1, new BigDecimal(45.792186), new BigDecimal(4.792958));
        hsqlStore.saveBeacon(3, new BigDecimal(2.56423d), new BigDecimal(41.56d));
        hsqlStore.saveBeacon(3, new BigDecimal(2.54566d), new BigDecimal(2.545689d));
        hsqlStore.saveBeacon(1, new BigDecimal(52.54565d), new BigDecimal(24.65659d));
        hsqlStore.saveBeacon(1, new BigDecimal(25.54668d), new BigDecimal(32.5464d));
        hsqlStore.shutdown();
    }
}
