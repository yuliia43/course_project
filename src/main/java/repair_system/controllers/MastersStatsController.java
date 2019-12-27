package repair_system.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repair_system.commonlyUsedStrings.PageLocation;
import repair_system.models.Application;
import repair_system.services.repositoryServices.ApplicationsService;

import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 08.12.2019
 * @project repair_system
 */
@Controller
public class MastersStatsController {

    @Autowired
    private ApplicationsService applicationsService;

    @GetMapping("/mastersStats")
    public String doGet(Model model) {
        List<Application> applications = applicationsService.getAllMastersStats();
        model.addAttribute("applications", applications);
        return PageLocation.MASTERS_STATS_PAGE;
    }

}
