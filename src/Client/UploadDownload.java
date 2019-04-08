package Client;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UploadDownload extends Component {

    ClientHelper helper;
    public UploadDownload(){

    }


    public void uploadFileToServer(String hostname, String port, String userName) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        String filePathToUpload =  fileChooser.getSelectedFile().getAbsolutePath();

        String username = userName;

        if(new File(filePathToUpload).length() >  65536) {
            JOptionPane.showMessageDialog(null,"File size too big please choose a file 64Kb or smaller" ,
                    "File size Error", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                this.helper = new ClientHelper(hostname,port);
                String response = helper.sendFileToServer(username, filePathToUpload);

                if(response.equals("501")){
                    JOptionPane.showMessageDialog(null,"File upload Successful","Successful Upload",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else if(response.equals("502")){
                    JOptionPane.showMessageDialog(null,"File upload Unsuccessful","Unsuccessful Upload",
                            JOptionPane.ERROR_MESSAGE);
                }

            }catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

   public void downloadFileFromServer(String hostname , String port, String userName) throws IOException {

        String username = userName;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") +
                "ideaProjects/CADistributedComputing/Server/" + username));
        fileChooser.showOpenDialog(this);
        String filePathDownload =  fileChooser.getSelectedFile().getAbsolutePath();
        File file = new File(filePathDownload);
        String filename = file.getName();

        this.helper = new ClientHelper(hostname,port);
        String message = "103" + "," + username + "," + filename + ",";
        String messageReturned = helper.getEcho(message);
        DatagramSplit fileData = new DatagramSplit(messageReturned);

        if(fileData.getProtocolNumber() == 504 ){
            String directory = "Client/" + username.trim() + "/";
            new File(directory).mkdir();
            Path path = Paths.get( directory  + fileData.getFileName());
            Path parentDir = Paths.get( directory);
            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);

            Files.write(path, fileData.getData());

            JOptionPane.showMessageDialog(null,"File Download Successful","Successful Download",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else if(fileData.getProtocolNumber() == 503){
            JOptionPane.showMessageDialog(null,"please log in before downloading a file","Unsuccessful Download",
                    JOptionPane.ERROR_MESSAGE);
        }
        else if(fileData.getProtocolNumber() == 506){
            JOptionPane.showMessageDialog(null,"No such file exists","Unsuccessful Download",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null,"File download Unsuccessful something went wrong",
                    "Unsuccessful Download", JOptionPane.ERROR_MESSAGE);
        }

        this.helper.done();
   }
}



