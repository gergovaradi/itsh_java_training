package registrationproject.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationproject.persistence.*;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserInformation user){
        userRepository.saveUser(user);
    }

    public List<AdminInformation> informationList(){
        return userRepository.informationList();
    }

    public boolean loginCheck(LoginInformation loginInformation) throws SQLException {
        return userRepository.loginCheck(loginInformation);
    }

    public void saveEmail(Email email){
        userRepository.saveEmail(email);
    }
}
