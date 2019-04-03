package Client;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class UploadDownload extends Component {

    ClientHelper helper;
    public UploadDownload(){

    }


    public void uploadFileToServer(String hostname, String port, String userName) throws IOException {
        //JFileChooser to allow user to specify which file they want to upload
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        String filePathToUpload =  fileChooser.getSelectedFile().getAbsolutePath();


        String username = userName;

        if(filePathToUpload.getBytes().length > 64) {
            JOptionPane.showMessageDialog(null,"File size too big please choose a file 64Kb or smaller" ,"File size Error", JOptionPane.ERROR_MESSAGE);
        }else{
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

    }

    //public ArrayList<String> getAllFilesFromServer(String hostname , String port, String userName) throws IOException {
//
    //    this.helper = new ClientHelper(hostname,port);
//
    //    String username =  userName;
    //    String message = "105" + "," + userName;
    //    String messageReturned = helper.getEcho(message);
//
    //    ArrayList<String> files = FilePacket.splitDataIntoList(messageReturned.getBytes());
//
    //    helper.done();
//
    //    downloadFileFromServer(hostname, port,username, files);
    //    return files;
//
    //}
//

   public void downloadFileFromServer(String hostname , String port, String userName) throws IOException {
       String username = userName;
       //ArrayList<String> filesAvailableForDownload = new ArrayList<>();
       //String[] filenames = new String[files.size()];
       //filesAvailableForDownload.addAll(files);

      //for(int i = 0; i < filesAvailableForDownload.size(); i++){
      //    filenames[i] = filesAvailableForDownload.get(i);
      //}

      // if(filesAvailableForDownload.size() > 0){
      //     String filename  = (String) JOptionPane.showInputDialog(null, "Choose file for download",
      //             "FileDownload!!", JOptionPane.QUESTION_MESSAGE, null,
      //             filenames, // Array of choices
      //             filenames[0]);


           String filename = (String) JOptionPane.showInputDialog("please enter the name of the file you wish to download");
           this.helper = new ClientHelper(hostname,port);

           String message = "103" + "," + username + "," + filename + ",";
           String messageReturned = helper.getEcho(message);
           DatagramSplit file = new DatagramSplit(messageReturned);

           if(file.getProtocolNumber() == 504 ){
               String directory = "Client/" + username.trim() + "/";
               new File(directory).mkdir();
               Path path = Paths.get( directory  + file.getFileName());
               Path parentDir = Paths.get( directory);
               if (!Files.exists(parentDir))
                   Files.createDirectories(parentDir);
               Files.write(path, file.getData());

               JOptionPane.showMessageDialog(null,"File Download Successful","Successful Download", JOptionPane.INFORMATION_MESSAGE);
           }
           else if(file.getProtocolNumber() == 503){
               JOptionPane.showMessageDialog(null,"please log in before downloading a file","Unsuccessful Download", JOptionPane.ERROR_MESSAGE);
           }
           else if(file.getProtocolNumber() == 506){
               JOptionPane.showMessageDialog(null,"No such file exists","Unsuccessful Download", JOptionPane.ERROR_MESSAGE);
           }
           else {
               JOptionPane.showMessageDialog(null,"File download Unsuccessful something went wrong","Unsuccessful Download", JOptionPane.ERROR_MESSAGE);
           }


      //}
      //else{
      //    JOptionPane.showMessageDialog(null,"No Files Available for download","Successful Upload", JOptionPane.INFORMATION_MESSAGE);
      //}
   }
}



