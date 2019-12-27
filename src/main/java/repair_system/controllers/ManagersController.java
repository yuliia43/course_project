package repair_system.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repair_system.commonlyUsedStrings.PageLocation;
import repair_system.dtos.ManagerApplicationDto;
import repair_system.enums.Status;
import repair_system.facade.UserFacade;
import repair_system.validators.InputDataValidator;
import repair_system.factories.StatusFactory;
import repair_system.models.Application;
import repair_system.services.repositoryServices.ApplicationsService;
import repair_system.services.repositoryServices.UsersService;

import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 09.12.2019
 * @project repair_system
 */
@Controller
public class ManagersController {
    @Autowired
    private ApplicationsService applicationsService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/manager/applications")
    public String doGet(Model model) {
        List<Application> applications = applicationsService.getAllNotViewed();
        model.addAttribute("applications", applications);
        return PageLocation.MANAGERS_APPLICATION_PAGE;
    }

    @PostMapping("/manager/applications")
    public String doPost(ManagerApplicationDto dto, Model model) {
        boolean confirm = dto.isConfirm();
        boolean reject = dto.isReject();
        Status status = null;
        String details = dto.getDetails();
        if (confirm) {
            if (!InputDataValidator.priceNotEmpty(dto.getPrice())) {
                model.addAttribute("emptyFields", true);
                model.addAttribute("details", details);
                return doGet(model);
            } else {
                status = Status.ACCEPTED;
            }
        } else if (reject) {
            if (!InputDataValidator.detailsDataNotEmpty(details)) {
                model.addAttribute("emptyFields", true);
                return doGet(model);
            } else {
                status = Status.REFUSED;
            }
        }

        String statusString = StatusFactory.getStringValue(status);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Application application = applicationsService.getOneById(dto.getApplicationId());
        application.setStatus(statusString);
        application.setManager(usersService.getUserByEmail(authentication.getName()).get());
        application.setManagerDetails(details);
        if (status == Status.ACCEPTED)
            application.setPrice(dto.getPrice());
        applicationsService.update(application);
        return doGet(model);
    }
}
