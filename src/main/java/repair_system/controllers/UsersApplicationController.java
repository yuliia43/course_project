package repair_system.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import repair_system.commonlyUsedStrings.PageLocation;
import repair_system.dtos.FeedbackDto;
import repair_system.dtos.SecureUser;
import repair_system.enums.Role;
import repair_system.facade.UserFacade;
import repair_system.factories.RolesFactory;
import repair_system.models.Application;
import repair_system.models.Feedback;
import repair_system.models.User;
import repair_system.services.repositoryServices.ApplicationsService;
import repair_system.services.repositoryServices.FeedbacksService;
import repair_system.services.repositoryServices.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * @author Yuliia Shcherbakova ON 09.12.2019
 * @project repair_system
 */
@Controller
public class UsersApplicationController {

    @Autowired
    private static UserFacade userFacade;
    @Autowired
    private ApplicationsService applicationsService;
    @Autowired
    private FeedbacksService feedbacksService;

    @GetMapping("/user/applications")
    public String doGet(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecureUser user = userFacade.getUserByEmail(authentication.getName());
        model.addAttribute("applications",
                applicationsService.getAllByUserId(user.getUserId()));
        return PageLocation.USERS_APPLICATION_PAGE;
    }

    @PostMapping("/user/applications")
    public String doPost(FeedbackDto feedbackDto, Model model) {
        Feedback feedback = feedbacksService.getFeedbackById(feedbackDto.getFeedbackId());
        feedback.setFeedback(feedbackDto.getFeedback());
        feedbacksService.setFeedback(feedback);
        return doGet(model);
    }
}
