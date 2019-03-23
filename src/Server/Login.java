package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("Duplicates")
public class Login {

    private ArrayList<UserLoginDetails> validUsers = new ArrayList<UserLoginDetails>();
    protected ArrayList<LoggedInUser> loggedInUsers = new ArrayList<LoggedInUser>();

    Login(){
        validUsers.add(new UserLoginDetails("Gerard","password"));
        validUsers.add(new UserLoginDetails("Darren","password1"));
        validUsers.add(new UserLoginDetails("Jonathan","password2"));
        validUsers.add(new UserLoginDetails("Jimmy","password3"));
    }

    protected void loginRequest(DatagramMessage message, ServerDatagramSocket socket) throws IOException {
            String username;
            String password;
            String thisMessage = message.getMessage();
            String[] words = thisMessage.split(" ");
            username = words[1];
            password = words[2];

            if(isUserValid(username, password)){
                System.out.print("Valid User\n");
                socket.sendMessage(message.getAddress(), message.getPort(), "110");
                loggedInUsers.add(new LoggedInUser(username,password, message.getAddress(), message.getPort()));

            }else{
                System.out.print("Invalid User\n");
                socket.sendMessage(message.getAddress(), message.getPort(), "111");
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

    public void logoutRequest(DatagramMessage message, ServerDatagramSocket socket) throws IOException {
        String username;
        String password;
        String thisMessage = message.getMessage();
        String[] words = thisMessage.split(" ");
        username = words[1];
        password = words[2];
        boolean removed = false;

        Iterator<LoggedInUser> it = loggedInUsers.iterator();
        while (it.hasNext()) {
            LoggedInUser user = it.next();
            if(user.getName().equals(username) && user.getPassword().equals(password)) {
                it.remove();
                System.out.println("Logged out");
                socket.sendMessage(message.getAddress(), message.getPort(), "112");
                removed = true;
            }
        }

        if(removed == true){
            System.out.println("Logout Successful");
        }else{
            System.out.println("Logout Unsuccessful");
        }
    }

}
