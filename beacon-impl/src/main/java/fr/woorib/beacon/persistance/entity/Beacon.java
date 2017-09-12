package fr.woorib.beacon.persistance.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import fr.woorib.backand.client.api.BackandManyToMany;
import fr.woorib.backand.client.api.BackandObject;
import fr.woorib.beacon.data.BeaconEntry;

/**
 * Beacon bean
 */
@Entity
@Table(name = "BEACON")
@BackandObject(table="beacons")
public class Beacon implements Serializable {
  @Column(name = "LATITUDE", nullable = false)
  private Double latitude;
  @Column(name = "LONGITUDE", nullable = false)
  private Double longitude;
  @Column(name = "DESCRIPTION")
  private String description;
  @ManyToOne(targetEntity = User.class)
  private User owner;
  @Transient
  private Collection<User> targets;
  @Id
  @Column(name = "BEACON_ID")
  private int id;

  public static BeaconEntry getBeaconEntry(final Beacon beacon) {
      return new BeaconEntry() {
          public Integer getUserId() {
            User owner = beacon.getOwner();
            return owner != null ? owner.getId() : null;
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

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @BackandManyToMany(parameter="target", reference="beacon")
  public Collection<User> getTargets() {
    return targets;
  }

  public void setTargets(Collection<User> targets) {
    this.targets = targets;
  }

  @Override
  public String toString() {
    return "Beacon{" +
      "latitude=" + latitude +
      ", longitude=" + longitude +
      ", description='" + description + '\'' +
      ", owner=" + owner +
      ", targets=" + targets +
      ", id=" + id +
      '}';
  }
}

