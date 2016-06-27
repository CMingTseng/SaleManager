package tvcompany.salemanager.model;
import java.util.Date;


public class User {
    private String userName;
    private String password;
    private String createDate;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String note;
    private String parent;
    private String image;

    public User() {
    }

    public User(String userName, String passWord, String createDate, String email, String phoneNumber, String fullName, String note, String parent, String image) {
        this.userName = userName;
        this.password = passWord;
        this.createDate = createDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.note = note;
        this.parent = parent;
        this.image = image;

    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}