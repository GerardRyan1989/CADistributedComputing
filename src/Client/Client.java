package Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class Client extends JFrame implements ActionListener {
    private LoginLogoutClient loginLogout = new LoginLogoutClient();
    private UploadDownload uploadDownload = new UploadDownload();

    private JButton btnLogin;
    private JButton btnLogout;
    private JButton btnDownload;
    private JButton btnUpload;

    private Client(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        setLayout(new GridLayout(2,2));

        btnUpload = new JButton("Upload File");
        btnDownload = new JButton("Download File");
        btnLogin = new JButton("Login");
        btnLogout  = new JButton("Logout");

        btnLogin.addActionListener(this);
        btnLogout.addActionListener(this);
        btnUpload.addActionListener(this);
        btnDownload.addActionListener(this);

        this.add(btnLogin);
        this.add(btnLogout);
        this.add(btnUpload);
        this.add(btnDownload);
        this.setVisible(true);
    }

    public static void main(String[] args){
        new Client();
    } //end main

    @Override
    public void actionPerformed(ActionEvent e) {
        String hostName = "localhost";
        String portNum = "7";

        if(e.getSource() == btnLogin){
            loginLogout.login(hostName, portNum);
        }

        if(e.getSource() == btnUpload){

           try {
              uploadDownload.uploadFileToServer(hostName, portNum,loginLogout.getUsername());
           } catch (IOException e1) {
                e1.printStackTrace();
           }
        }

        if(e.getSource() == btnDownload){
            try {
                uploadDownload.downloadFileFromServer(hostName, portNum, loginLogout.getUsername());

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == btnLogout){
            try {
                loginLogout.logout(hostName, portNum);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
} // end class
