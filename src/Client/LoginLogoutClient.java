package Client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginLogoutClient {

    String username;
    String password;

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
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        this.username = JOptionPane.showInputDialog(("please enter name "));
        this.password = JOptionPane.showInputDialog(("please enter password "));

        try{
            System.out.println("username and password.");

            ClientHelper helper = new ClientHelper(hostName, portNum);
            String message = "101" + " " + username + " " + password;
            String messageReturned =  helper.getEcho(message);


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
