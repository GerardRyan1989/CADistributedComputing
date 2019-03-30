package Client;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class Client {
    static final String endMessage = ".";

    public static void main(String[] args) {
        try {
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Welcome to the Echo client.\nWhat is the name of the server host?");
            String hostName = br.readLine();


            LoginLogoutClient loginLogout = new LoginLogoutClient();
            UploadDownload uploadDownload = new UploadDownload();

            if (hostName.length() == 0) // if user did not enter a name
                hostName = "localhost";  //   use the default host name

            System.out.println("What is the port number of the server host?");
            String portNum = br.readLine();

            if (portNum.length() == 0)
                portNum = "7";// default port number

            ClientHelper helper = new ClientHelper(hostName, portNum);
            boolean done = false;
            String message, echo;

            while (!done) {
                System.out.println("Enter a line to receive an echo back from the server, or a single peroid to quit.");
                message = br.readLine( );

                if ((message.trim()).equals (endMessage)){
                    done = true;
                    helper.done( );
                }
                else {
                    echo = helper.getEcho(message);
                    System.out.println(echo);
                }
            }

                    loginLogout.login(hostName,portNum);

                    //uploadDownload.uploadFileToServer(hostName,portNum);

                    //uploadDownload.downloadFileFromServer("Gerard", "user.txt", hostName, portNum);

                    loginLogout.logout(hostName,portNum);




            // end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace( );
        } // end catch





        // end catch
    } //end main




    public void download(){

    }
    public void upload() {

    }


} // end class
