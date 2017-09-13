package fr.woorib.beacon.web;

import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.services.BeaconService;

/**
 * Created by Veryeld on 02/04/2017.
 */
@RestController
@RequestMapping("/beacon")
public class BeaconRest {

    @Inject
    private BeaconService beaconService;

  @GetMapping("/user/{id}")
  public List<BeaconEntry> getBeaconForUser(@PathVariable(required=true) Integer id, Model model) {
    return beaconService.getUserBeacons(id);
  }

  @GetMapping(path = "/{id}")
  public BeaconEntry getBeaconById(@PathVariable(required=true) Integer id, Model model) {
    return beaconService.getBeacon(id);
  }

  @GetMapping("createBeacon")
  public BeaconEntry createBeacon(@RequestParam(value="userId", required=true) Integer userId,
                                  @RequestParam(value="longitude", required=true) BigDecimal longitude,
                                  @RequestParam(value="latitude", required=true) BigDecimal latitude,
                                  Model model) {
    Integer beaconId = beaconService.setBeacon(userId, latitude, longitude);
    return beaconService.getBeacon(beaconId);
  }
}
