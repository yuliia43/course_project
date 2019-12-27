package repair_system.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repair_system.commonlyUsedStrings.PageLocation;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Shcherbakova ON 27.12.2019
 * @project spring_app
 */
@Controller
public class ErrorsController implements ErrorController {
 
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        int httpErrorCode = getErrorCode(httpRequest);
 
        switch (httpErrorCode) {
            case 403: {
                return new ModelAndView(PageLocation.INCORRECT_ROLE);
            }
            case 404: {
                return new ModelAndView(PageLocation.PAGE_NOT_FOUND);
            }
            case 500: {
                return new ModelAndView(PageLocation.SQL_EXCEPTION);
            }
        }
        return new ModelAndView(PageLocation.SQL_EXCEPTION);
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
