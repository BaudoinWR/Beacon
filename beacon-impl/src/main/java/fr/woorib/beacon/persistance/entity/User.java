package fr.woorib.beacon.persistance.entity;

import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import fr.woorib.backand.client.api.BackandManyToMany;
import fr.woorib.backand.client.api.BackandObject;

/**
 * User bean
 */
@Entity
@Table(name = "USER")
@BackandObject(table="users")
public class User {
  @Column(name = "EMAIL")
  private String email = "";
  @Transient
  private Collection<Beacon> beacons = Collections.emptyList();
  @Transient
  private Collection<Beacon> seen_beacons = Collections.emptyList();
  @Id
  @Column(name = "USER_ID")
  private int id = -1;
  public User() {
    email = "";
    beacons = Collections.emptyList();
    seen_beacons = Collections.emptyList();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Collection<Beacon> getBeacons() {
    return beacons;
  }

  public void setBeacons(Collection<Beacon> beacons) {
    this.beacons = beacons;
  }

  @BackandManyToMany(parameter = "beacon", reference = "target")
  public Collection<Beacon> getSeen_beacons() {
    return seen_beacons;
  }

  public void setSeen_beacons(Collection<Beacon> seen_beacons) {
    this.seen_beacons = seen_beacons;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "User{" +
      "email='" + email + '\'' +
      ", beacons=" + beacons +
      ", seen_beacons=" + seen_beacons +
      ", id=" + id +
      '}';
  }
}

