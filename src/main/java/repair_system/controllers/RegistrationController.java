package repair_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repair_system.commonlyUsedStrings.PageLocation;
import repair_system.dtos.UserRegistrationDto;
import repair_system.encryption.UserEncryptor;
import repair_system.utils.SecurityEncryptor;
import repair_system.validators.InputDataValidator;
import repair_system.validators.MatchingValidator;
import repair_system.models.User;
import repair_system.services.repositoryServices.UsersService;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */
@Controller
public class RegistrationController {

    @Autowired
    private UsersService service;

    @GetMapping("/registration")
    public String doGet(Model model) {
        return PageLocation.REGISTRATION_PAGE;
    }

    @PostMapping("/registration")
    public String doPost(UserRegistrationDto userDto,
                         Model model) {
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String role = userDto.getRole();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String passwordConfirmation = userDto.getPasswordConfirmation();
        if (!InputDataValidator.registrationDataNotEmpty(firstName, lastName, role, email,
                password, passwordConfirmation)) {
            model.addAttribute("emptyFields", true);
            setAttributes(model, firstName, lastName, role, email);
            return doGet(model);
        }
        if (!InputDataValidator.registrationPasswordsEqual(password, passwordConfirmation)) {
            model.addAttribute("passwordsNotEqual", true);
            setAttributes(model, firstName, lastName, role, email);
            return doGet(model);
        }
        if (!MatchingValidator.nameMatches(firstName)) {
            model.addAttribute("fnWrong", true);
            setAttributes(model, firstName, lastName, role, email);
            return doGet(model);
        }
        if (!MatchingValidator.nameMatches(lastName)) {
            model.addAttribute("lnWrong", true);
            setAttributes(model, firstName, lastName, role, email);
            return doGet(model);
        }
        if (!MatchingValidator.emailMatches(email)) {
            model.addAttribute("emailWrong", true);
            setAttributes(model, firstName, lastName, role, email);
            return doGet(model);
        }
        User user = new User().builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .password(userDto.getPassword())
                .build();
        SecurityEncryptor.getEncryptor().encrypt(user);
        service.add(user);
        return "redirect:/authorisation";
    }

    private static void setAttributes(Model model, String firstName, String lastName, String role, String email) {
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("role", role);
        model.addAttribute("email", email);
    }

}

