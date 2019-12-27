package repair_system.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repair_system.commonlyUsedStrings.PageLocation;
import repair_system.enums.Status;
import repair_system.validators.InputDataValidator;
import repair_system.factories.StatusFactory;
import repair_system.models.Application;
import repair_system.models.User;
import repair_system.services.repositoryServices.ApplicationsService;
import repair_system.services.repositoryServices.UsersService;

import java.util.Optional;

/**
 * @author Yuliia Shcherbakova ON 09.12.2019
 * @project repair_system
 */
@Controller
public class NewApplicationController {
    @Autowired
    private ApplicationsService service;
    @Autowired
    private UsersService usersService;

    @GetMapping("/user/newApplication")
    public String doGet(Model model) {
        return PageLocation.NEW_APPLICATION_PAGE;
    }

    @PostMapping("/user/newApplication")
    public String doPost(String details, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = usersService.getUserByEmail(authentication.getName());
        if (InputDataValidator.detailsDataNotEmpty(details)) {
            service.add(new Application()
                    .builder()
                    .userId(user.get().getUserId())
                    .repairDetails(details)
                    .status(StatusFactory.getStringValue(Status.CREATED))
                    .build());
            return "redirect:/user/applications";
        } else {
            model.addAttribute("emptyFields", true);
            return doGet(model);
        }
    }
}
