package repair_system.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repair_system.commonlyUsedStrings.PageLocation;
import repair_system.dtos.SecureUser;
import repair_system.facade.UserFacade;
import repair_system.models.User;
import repair_system.services.repositoryServices.UsersService;

import java.util.Optional;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */
@Controller
public class UserPageController {

    @Autowired
    private static UserFacade userFacade;

    @GetMapping("/userPage")
    public String doGet(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            SecureUser user = userFacade.getUserByEmail(authentication.getName());
            model.addAttribute("user", user);
            return PageLocation.USER_INFO;
        }
        else
            return PageLocation.NOT_AUTHORISED;
    }
}
