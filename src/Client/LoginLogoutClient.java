package Client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginLogoutClient {

    String username;
    String password;
    ClientHelper helper;

    public LoginLogoutClient() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void login(String hostName, String portNum){

        boolean validUsername = false;
        boolean validPassword = false;


       // while(!validUsername){
       //     this.username = JOptionPane.showInputDialog(("please enter name "));
       //     if(username.startsWith("!") && username.endsWith("@") && username.length() > 5){
       //         validUsername = true;
       //     }
       // }
       //
       // while(!validPassword){
       //     this.password = JOptionPane.showInputDialog(("please enter password "));

       //     this.password = JOptionPane.showInputDialog(("please enter password"));
       //     if(password.startsWith("%") && password.endsWith("&") && password.length() > 5){
       //         validPassword = true;
       //     }
       // }

        this.username = "!Gerard@";
        this.password = "%password&";

        try{

            this.helper = new ClientHelper(hostName,portNum);
            String message = "101" + " " + username + " " + password;
            String messageReturned = helper.getEcho(message);
            System.out.println(messageReturned);

        } catch (Exception ex) {
            ex.printStackTrace( );
        } // end catch
    }

    public void logout(String hostName, String portNum) throws IOException {
        int result = JOptionPane.showConfirmDialog(null,"Do you wang to log out ?", "",JOptionPane.YES_NO_OPTION);
        this.username = JOptionPane.showInputDialog(("please enter name "));
        this.password = JOptionPane.showInputDialog(("please enter password "));

        if(result == JOptionPane.YES_OPTION){
            ClientHelper helper = new ClientHelper(hostName, portNum);
            String message = "104" + " " + username + " " + password;
            String messageReturned =  helper.getEcho(message);
            System.out.println((messageReturned));
        }
    }



}
