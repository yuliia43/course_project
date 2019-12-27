package repair_system.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repair_system.commonlyUsedStrings.PageLocation;
import repair_system.validators.InputDataValidator;
import repair_system.models.Application;
import repair_system.services.repositoryServices.ApplicationsService;

import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 10.12.2019
 * @project repair_system
 */
@Controller
public class MasterApplicationController {
    @Autowired
    private ApplicationsService applicationsService;

    @GetMapping("/master/applications")
    public String doGet(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Application> applications = applicationsService
                .getAllUnfinishedForMasterEmail(authentication.getName());
        model.addAttribute("applications", applications);
        return PageLocation.MASTERS_FINISHING_PAGE;
    }

    @PostMapping("/master/applications")
    public String doPost(String[] applicationId, Model model) {
        if (!InputDataValidator.somethingIsChosen(applicationId)) {
            model.addAttribute("notChecked", true);
            return doGet(model);
        } else {
            applicationsService.updateAll(applicationId);
        }
        return doGet(model);
    }
}
