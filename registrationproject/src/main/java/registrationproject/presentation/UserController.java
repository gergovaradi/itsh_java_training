package registrationproject.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import registrationproject.businesslogic.UserService;
import registrationproject.persistence.AdminInformation;
import registrationproject.persistence.Email;
import registrationproject.persistence.LoginInformation;
import registrationproject.persistence.UserInformation;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/registration", method = RequestMethod.POST)
    public void saveUser(@RequestBody UserInformation user){
        userService.saveUser(user);
    }

    @RequestMapping("/api/admin")
    public List<AdminInformation> informationList(){
        return userService.informationList();
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public boolean loginCheck(@RequestBody LoginInformation loginInformation) throws SQLException {
        return userService.loginCheck(loginInformation);
    }

    @RequestMapping(value = "/api/email", method = RequestMethod.POST)
    public void saveEmail(@RequestBody Email email){
        userService.saveEmail(email);
    }
}
