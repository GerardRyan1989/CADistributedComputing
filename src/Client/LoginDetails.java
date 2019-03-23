package Client;

public class LoginDetails{

    private String userName;
    private String passWord;

    LoginDetails(String username, String password){
        this.userName = username;
        this.passWord = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }






}
