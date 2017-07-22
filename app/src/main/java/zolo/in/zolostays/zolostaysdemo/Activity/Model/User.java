package zolo.in.zolostays.zolostaysdemo.Activity.Model;

/**
 * Created by anujith47 on 21/07/17.
 */

public class User {
    private int userId;
    private String username;
    private String userPhoneNumber;
    private String password;
    private String userEmailId;

    public User(String phoneNumber, String emailId, String userName, String password) {
        this.userPhoneNumber = phoneNumber;
        this.userEmailId = emailId;
        this.username = userName;
        this.password = password;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }
}
