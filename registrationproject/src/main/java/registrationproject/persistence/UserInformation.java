package registrationproject.persistence;

public class UserInformation {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phone;
    private String education;
    private String englishLevel;
    private String age;
    private String newsLetter;

    public UserInformation() {
    }

    public UserInformation(String userName, String password,
                           String firstName, String lastName, String emailAddress,
                           String phone, String education, String englishLevel, String age, String newsLetter) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.education = education;
        this.englishLevel = englishLevel;
        this.age = age;
        this.newsLetter = newsLetter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(String newsLetter) {
        this.newsLetter = newsLetter;
    }
}
