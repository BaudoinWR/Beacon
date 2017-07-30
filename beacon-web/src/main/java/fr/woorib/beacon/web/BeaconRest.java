package fr.woorib.beacon.web;

import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.persistance.hsql.HsqlStore;
import fr.woorib.beacon.services.BeaconService;
import fr.woorib.beacon.services.BeaconServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Veryeld on 02/04/2017.
 */
@Controller
public class BeaconRest {

    private BeaconService beaconService;

    public BeaconRest() {
        beaconService = new BeaconServiceImpl(new HsqlStore());
    }

    @RequestMapping("beaconForUser")
    @ResponseBody
    public List<BeaconEntry> getBeaconForUser(@RequestParam(value="userId", required=true) Integer userId, Model model) {
        return beaconService.getUserBeacons(userId);
    }
}
