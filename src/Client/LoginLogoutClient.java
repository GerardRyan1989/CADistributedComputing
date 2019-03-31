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

        while(!validUsername){
            this.username = JOptionPane.showInputDialog(("please enter name "));
            if(username.length() < 15 || !username.contains(",")){
                validUsername = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid username please ensure username is lees than 15 characters long\n " +
                        "and dosent conation the \", \" character ","Invalid username", JOptionPane.ERROR_MESSAGE);
            }
        }

        while(!validPassword){
            this.password = JOptionPane.showInputDialog(("please enter password"));
            if(password.length() < 10 || !password.contains(",")){
                validPassword = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid password please ensure password is lees than 10 characters long\n " +
                        "and dosent conation the \", \" character ","Invalid username", JOptionPane.ERROR_MESSAGE);
            }
        }

        try{

            this.helper = new ClientHelper(hostName,portNum);
            String message = "101" + "," + this.username + "," + this.password + ",";
            String messageReturned = helper.getEcho(message);
            System.out.println(messageReturned);

            if(messageReturned.equals("110")){
                JOptionPane.showMessageDialog(null,"You have logged in Successfully","Successful Login", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(messageReturned.equals("111")){
                JOptionPane.showMessageDialog(null,"Login Unsuccessful","Unsuccessful Login", JOptionPane.ERROR_MESSAGE);
            }


        } catch (Exception ex) {
            ex.printStackTrace( );
        } // end catch
    }

    public void logout(String hostName, String portNum) throws IOException {
        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to log out ?", "",JOptionPane.YES_NO_OPTION);



        if(result == JOptionPane.YES_OPTION){
            ClientHelper helper = new ClientHelper(hostName, portNum);
            String message = "104" + "," + this.username + "," + this.password + ",";
            String messageReturned =  helper.getEcho(message);

            if(messageReturned.equals("113")){
                JOptionPane.showMessageDialog(null,"You have logged out Successfully","Successful Logout", JOptionPane.INFORMATION_MESSAGE);
                this.username = "";
                this.password = "";
            }
            else if(messageReturned.equals("114")){
                JOptionPane.showMessageDialog(null,"Logout Unsuccessful","Unsuccessful Logout", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



}
