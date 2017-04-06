package fr.woorib.beacon.persistance;

import fr.woorib.beacon.data.BeaconEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veryeld on 30/03/2017.
 */
public class StoreHSQL implements Store {
    Connection c;

    public StoreHSQL() {
//        Connection c = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
        try {
            c = DriverManager.getConnection("jdbc:hsqldb:file:d:/beacondb/beacdb", "SA", "pipou");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(BeaconEntry beaconEntry) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("insert into Beacon(userid, latitude, longitude) values (?, ?, ?)");
            preparedStatement.setInt(1, beaconEntry.getUserId());
            preparedStatement.setDouble(2, beaconEntry.getLatitude());
            preparedStatement.setDouble(3, beaconEntry.getLongitude());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BeaconEntry getBeaconByBeaconId(Integer id) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("select beaconId, userId, latitude, longitude from beacon where beaconId = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                return new BeaconEntry(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BeaconEntry> getBeaconByUserId(Integer userId) {
        List<BeaconEntry> results = new ArrayList<BeaconEntry>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("select beaconId, userId, latitude, longitude from beacon where userId = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                results.add(new BeaconEntry(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4)));

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
        StoreHSQL storeHSQL = new StoreHSQL();
        storeHSQL.setupDB();
        storeHSQL.save(new BeaconEntry(1, 45.792186, 4.792958));
        storeHSQL.save(new BeaconEntry(3, 2.56423d,41.56d));
        storeHSQL.save(new BeaconEntry(3, 2.54566d,2.545689d));
        storeHSQL.save(new BeaconEntry(1, 52.54565d,24.65659d));
        storeHSQL.save(new BeaconEntry(1, 25.54668d,32.5464d));
        storeHSQL.shutdown();
    }
}
