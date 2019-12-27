package repair_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import repair_system.commonlyUsedStrings.PageLocation;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Shcherbakova ON 05.12.2019
 * @project repair_system
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String doGet(HttpServletRequest req){
        return PageLocation.MAIN;
    }
}
