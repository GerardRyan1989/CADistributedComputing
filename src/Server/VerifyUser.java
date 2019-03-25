package Server;

public class VerifyUser {

    protected boolean valid;

    public VerifyUser(){

    }

    public VerifyUser(String username, boolean isValid){
        this.userName = username;
        this.valid = isValid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    protected String userName;

}
