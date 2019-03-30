package Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UploadDownload extends Component {

    ClientHelper helper;
    public UploadDownload(){

    }

    public void uploadFileToServer(String hostname, String port) throws IOException {
        //JFileChooser fileChooser = new JFileChooser();
        //fileChooser.showOpenDialog(this);
        String username = "Gerard";
        String filePathToUpload = "/Users/gerardryan/Downloads/user.txt";
        System.out.println(filePathToUpload);


        this.helper = new ClientHelper(hostname,port);
        String response = helper.sendFileToServer(username, filePathToUpload);
        try {
            if(response.equals("111")){
                System.out.println("result was 111");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void downloadFileFromServer(String username, String filename, String hostname , String port ) throws IOException {
        this.helper = new ClientHelper(hostname,port);
        String message = "103," + username + "," + filename + ",";
        String messageReturned = helper.getEcho(message);
        System.out.println(messageReturned);
    }

}



