package registrationproject.persistence;

public class AdminInformation {

    private String lastName;
    private String firstName;
    private String emailAddress;
    private String phone;
    private String education;
    private String englishLevel;
    private String age;
    private String newsLetter;

    public AdminInformation() {
    }

    public AdminInformation(String lastName, String firstName,
                            String emailAddress, String phone,
                            String education, String englishLevel,
                            String age, String newsLetter) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.education = education;
        this.englishLevel = englishLevel;
        this.age = age;
        this.newsLetter = newsLetter;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
