/**
 * Paquet de définition
 **/
package fr.woorib.beacon.persistance.hibernate;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.Store;
import fr.woorib.beacon.persistance.entity.Beacon;
import fr.woorib.beacon.persistance.entity.User;

/**
 * Implémentation Hibernate du Store
 */
@Transactional(readOnly = true)
public class HibernateStore implements Store {

  @Inject
  private SessionFactory sessionFactory;

  protected Session getSession() {
    return this.sessionFactory.getCurrentSession();
  }

  @Override
  @Transactional(readOnly = false)
  public Integer saveBeacon(Integer userId, Double latitude, Double longitude) {
    Beacon beacon = new Beacon();
    beacon.setLongitude(longitude);
    beacon.setLatitude(latitude);
    User owner = new User();
    owner.setId(userId);
    //TODO
    //beacon.setOwner(owner);
    Integer id = (Integer) this.getSession().save(beacon);
    return id;
  }

  @Override
  public BeaconEntry getBeaconByBeaconId(Integer beaconId) {
    Beacon beacon = this.getSession().get(Beacon.class, beaconId);
    return beacon == null ? null : Beacon.getBeaconEntry(beacon);
  }

  @Override
  public List<BeaconEntry> getBeaconByUserId(Integer userId) {
    return null;
  }
}
 
