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
import repair_system.models.Feedback;
import repair_system.services.TransactionService;
import repair_system.services.repositoryServices.ApplicationsService;
import repair_system.services.repositoryServices.UsersService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 09.12.2019
 * @project repair_system
 */
@Controller
public class MasterNewApplicationController {
    @Autowired
    private ApplicationsService applicationsService;
    @Autowired
    private UsersService userService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("master/newApplication")
    public String doGet(Model model) {
        List<Application> applications = applicationsService.getAllAccepted();
        model.addAttribute("applications", applications);
        return PageLocation.MASTERS_APPLICATION_PAGE;
    }

    @PostMapping("master/newApplication")
    public String doPost(String[] applicationId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!InputDataValidator.somethingIsChosen(applicationId)) {
            model.addAttribute("notChecked", true);
            return doGet(model);
        } else {
            List<Feedback> feedbacks = new ArrayList<>();
            for (String id : applicationId) {
                feedbacks.add(new Feedback().builder()
                        .applicationId(Integer.valueOf(id))
                        .master(userService.getUserByEmail(authentication.getName()).get())
                        .build());
            }
                transactionService.takeApplication(feedbacks);
            return doGet(model);
        }
    }
}
