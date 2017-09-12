package fr.woorib.beacon.web;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.services.BeaconService;

/**
 * Created by Veryeld on 02/04/2017.
 */
@Controller
public class BeaconRest {

    @Inject
    private BeaconService beaconService;

  @RequestMapping("beaconForUser")
  @ResponseBody
  public List<BeaconEntry> getBeaconForUser(@RequestParam(value="userId", required=true) Integer userId, Model model) {
    return beaconService.getUserBeacons(userId);
  }

  @RequestMapping("beaconById")
  @ResponseBody
  public BeaconEntry getBeaconById(@RequestParam(value="beaconId", required=true) Integer beaconId, Model model) {
    return beaconService.getBeacon(beaconId);
  }

  @RequestMapping("createBeacon")
  @ResponseBody
  public BeaconEntry createBeacon(@RequestParam(value="userId", required=true) Integer userId,
                                  @RequestParam(value="longitude", required=true) Double longitude,
                                  @RequestParam(value="latitude", required=true) Double latitude,
                                  Model model) {
    Integer beaconId = beaconService.setBeacon(userId, latitude, longitude);
    return beaconService.getBeacon(beaconId);
  }
}
