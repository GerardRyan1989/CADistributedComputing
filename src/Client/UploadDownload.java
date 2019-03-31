package Client;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class UploadDownload extends Component {

    ClientHelper helper;
    public UploadDownload(){

    }

    public void uploadFileToServer(String hostname, String port, String userName) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        String filePathToUpload =  fileChooser.getSelectedFile().getAbsolutePath();

        String username = userName;

        try {
            this.helper = new ClientHelper(hostname,port);
            String response = helper.sendFileToServer(username, filePathToUpload);

            if(response.equals("501")){
                JOptionPane.showMessageDialog(null,"File upload Successful","Successful Upload", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(response.equals("502")){
                JOptionPane.showMessageDialog(null,"File upload Unsuccessful","Unsuccessful Upload", JOptionPane.ERROR_MESSAGE);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }




    public void downloadFileFromServer(String hostname , String port, String userName) throws IOException {
        this.helper = new ClientHelper(hostname,port);
        String filename = JOptionPane.showInputDialog(null, "please enter the name of ile you want to download");
        String username =  userName;

        String message = "103" + "," + username + "," + filename + ",";
        String messageReturned = helper.getEcho(message);
        System.out.println(messageReturned);

        JFileChooser FC= new JFileChooser("C:/");
        int retrival = FC.showSaveDialog(null);



    }

}



