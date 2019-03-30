package Server;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("Duplicates")
public class Login {

    private ArrayList<UserLoginDetails> validUsers = new ArrayList<UserLoginDetails>();
    protected ArrayList<LoggedInUser> loggedInUsers = new ArrayList<LoggedInUser>();;

    Login(){

        validUsers.add(new UserLoginDetails("Gerard","password"));
        validUsers.add(new UserLoginDetails("Darren","password1"));
        validUsers.add(new UserLoginDetails("Jonathan","password2"));
        validUsers.add(new UserLoginDetails("Jimmy","password3"));
    }

    protected void loginRequest(DatagramSplit data, ServerDatagramSocket socket) throws IOException {
            String username;
            String password;

            username = data.getName();
            password = data.getPassword();

            if(isUserValid(username, password)){
                System.out.print("Valid User\n");
                socket.sendMessage(data.getAddress(), data.getPortNo(), "110");
                loggedInUsers.add(new LoggedInUser(username,password, data.getAddress(), data.getPortNo()));

            }else{
                System.out.print("Invalid User\n");
                socket.sendMessage(data.getAddress(), data.getPortNo(), "111");
            }
    }

    private boolean isUserValid(String username, String password){
        for (UserLoginDetails userDetails : validUsers){
            if(userDetails.getUserName().equals(username)&& userDetails.getPassWord().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void logoutRequest(DatagramSplit data, ServerDatagramSocket socket) throws IOException {
        String username = data.getName();
        String password = data.getPassword();

        boolean removed = false;

        Iterator<LoggedInUser> it = loggedInUsers.iterator();
        while (it.hasNext()) {
            LoggedInUser user = it.next();
            if(user.getName().equals(username) && user.getPassword().equals(password)) {
                it.remove();
                System.out.println("Logged out");
                removed = true;
                break;
            }
        }

        if(removed == true){
            System.out.println("Logout Successful");
            socket.sendMessage(data.getAddress(), data.getPortNo(), "113");
        }else{
            System.out.println("Logout Unsuccessful");
            socket.sendMessage(data.getAddress(), data.getPortNo(), "114");
        }
    }


    public VerifyUser getCurrentUser(DatagramSplit data){

        VerifyUser user = new VerifyUser("",false);
        Iterator<LoggedInUser> it = loggedInUsers.iterator();
        while (it.hasNext()) {
            LoggedInUser loggedInuser = it.next();
            if((loggedInuser.getAddress().equals(data.getAddress()) && loggedInuser.getName().equals(data.getName()))){
                user.setUserName(loggedInuser.getName());
                user.setValid(true);
                break;
            }
        }
        return user;
    }
}
