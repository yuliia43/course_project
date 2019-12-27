package repair_system.controllers;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repair_system.commonlyUsedStrings.PageLocation;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */
@Controller
public class AuthorisationController {
    private static Logger logger = Logger.getLogger(AuthorisationController.class);

    @GetMapping("/authorisation")
    public String doGet(Model model) {
        return PageLocation.AUTHORISATION_PAGE;
    }

    @PostMapping("/authorisation")
    public String doPost(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            logger.info("Authorisation success: " + authentication.getDetails());
            return "redirect:/userPage";
        } else {
            model.addAttribute("fail", true);
            return doGet(model);
        }
    }
}
