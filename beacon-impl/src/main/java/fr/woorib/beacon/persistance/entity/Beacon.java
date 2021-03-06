package fr.woorib.beacon.persistance.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;

import fr.woorib.backand.client.api.BackandManyToMany;
import fr.woorib.backand.client.api.BackandObject;
import fr.woorib.beacon.data.BeaconEntry;

/**
 * Beacon bean
 */
@Entity
@Table(name = "T_BEACON")
@BackandObject(table="beacons")
public class Beacon implements Serializable {
  @Column(name = "LATITUDE", nullable = false)
  private BigDecimal latitude;
  @Column(name = "LONGITUDE", nullable = false)
  private BigDecimal longitude;
  @Column(name = "DESCRIPTION")
  private String description;
  @ManyToOne(targetEntity = User.class)
  private User owner;
  @Transient
  private Collection<User> targets;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "BEACON_ID")
  private int id;

  public static BeaconEntry getBeaconEntry(final Beacon beacon) {
      return new BeaconEntry() {
          public Integer getUserId() {
            User owner = beacon.getOwner();
            return owner != null ? owner.getId() : null;
          }

          public BigDecimal getLongitude() {
              return beacon.getLongitude();
          }

          public BigDecimal getLatitude() {
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

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
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

