package Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileManager {

    public FileManager(){

    }

    public void saveFileUploaded(DatagramSplit datagramSplit, ServerDatagramSocket socket, Login login) throws IOException {
        Login loggedIn = login;
        VerifyUser user = loggedIn.getCurrentUser(datagramSplit);
        String nameOfFile = datagramSplit.getFileName();
        byte [] file = datagramSplit.getData();

        if(user.isValid()){
            new File(user.getUserName()).mkdir();
            String directory = "Server/" + user.getUserName().trim();
            Path path = Paths.get(directory + "/" + nameOfFile.trim());
            Path parentDir = Paths.get(directory);;

            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);

            Files.write(path,file);
            socket.sendMessage(datagramSplit.getAddress(), datagramSplit.getPortNo(), "501");
        }else{
            socket.sendMessage(datagramSplit.getAddress(), datagramSplit.getPortNo(), "502");
        }
    }


    public void downloadFileFromServer(DatagramSplit data, ServerDatagramSocket socket, Login login) throws IOException{
        Login loggedIn = login;
        String username = data.getName();
        String nameOfFile =  data.getFileName();
        VerifyUser user = loggedIn.getCurrentUser(data);
        String protocolNum = "504";
        Boolean fileExsists = false;

        if(user.isValid()){

            File folder = new File("Server/" + data.getName().trim());
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                   if(listOfFiles[i].getName().equals(nameOfFile)) {
                       fileExsists = true;
                       break;
                   }
                }
            }

            if(fileExsists == true ){
                Path path = Paths.get(  "Server/" + username.trim() + "/" + nameOfFile.trim());
                nameOfFile = "," + nameOfFile + ",";
                byte[] fileDownload =  Files.readAllBytes(path);
                byte[] fileName = nameOfFile.getBytes();
                byte[] protocol = protocolNum.getBytes();
                byte[] file = new byte[fileName.length + fileDownload.length + 3];

                System.arraycopy(protocol, 0, file, 0, protocol.length);
                System.arraycopy(fileName, 0, file, protocol.length, fileName.length);
                System.arraycopy(fileDownload, 0, file, protocol.length + fileName.length, fileDownload.length);

                socket.sendFile(data.getAddress(), data.getPortNo(), file);
            }
           else{
                socket.sendMessage(data.getAddress(), data.getPortNo(), "506");
            }
        }else{
            socket.sendMessage(data.getAddress(), data.getPortNo(), "503");
        }
    }
}
