package registrationproject.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(UserInformation user) {
        jdbcTemplate.update("INSERT INTO users(userName, password) VALUES(?, MD5(?));",
                user.getUserName(),
                user.getPassword() + "kutykuruty");

        jdbcTemplate.update("INSERT INTO usersInformation(userId, firstName, lastName, regDate, emailAddress) " +
                        "VALUES((SELECT MAX(id) FROM users), ?, ?, CURDATE(), ?)",
                user.getFirstName(),
                user.getLastName(),
                user.getEmailAddress());

        jdbcTemplate.update("INSERT INTO usersSkill(userId, phone, education, englishLevel, age, newsLetter) " +
                        "VALUES ((SELECT MAX(id) FROM users), ?, ?, ?, ?, ?)",
                user.getPhone(),
                user.getEducation(),
                user.getEnglishLevel(),
                user.getAge(),
                user.getNewsLetter());
    }

    public List<AdminInformation> informationList(){
        return jdbcTemplate.query("SELECT lastName, firstName, emailAddress," +
                        " phone, education, englishLevel, age, newsLetter " +
                        "FROM usersinformation, usersskill " +
                        "WHERE usersinformation.userId=usersskill.userId",
                new RowMapper<AdminInformation>() {
                    @Override
                    public AdminInformation mapRow(ResultSet resultSet, int i) throws SQLException {
                        String lastName = resultSet.getString("lastName");
                        String firstName = resultSet.getString("firstName");
                        String emailAddress = resultSet.getString("emailAddress");
                        String phone = resultSet.getString("phone");
                        String education = resultSet.getString("education");
                        String englishLevel = resultSet.getString("englishLevel");
                        String age = resultSet.getString("age");
                        String newsLetter = resultSet.getString("newsLetter");
                        AdminInformation ai = new AdminInformation(lastName, firstName, emailAddress,
                                phone, education, englishLevel, age, newsLetter);
                        return ai;
                    }
                });
    }

    public boolean loginCheck(LoginInformation loginInformation) throws SQLException {
        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement("SELECT userName," +
                " password FROM users WHERE userName=? AND password=MD5(?);");
        pstmt.setString(1, loginInformation.getUserName());
        pstmt.setString(2, loginInformation.getPassword() + "kutykuruty");
        ResultSet resultSet = pstmt.executeQuery();
        if(resultSet.next()) {
            return true;
        }
        return false;
    }

    public void saveEmail(Email email) {
        jdbcTemplate.update("INSERT INTO emailaddresses(emailAddress) VALUES(?);",
                email.getEmail());
    }
}
