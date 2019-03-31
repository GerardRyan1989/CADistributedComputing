package Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileManager {


    public FileManager(){

    }

    public void saveFileUploaded(DatagramSplit datagramSplit, ServerDatagramSocket socket, Login login) throws IOException {
        Login loggedIn = login;
        VerifyUser user = loggedIn.getCurrentUser(datagramSplit);
        String nameOfFile = datagramSplit.getFileName();
        byte [] file = datagramSplit.getData();

        if(user.isValid() == true){
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

    public void getAllFilesFromFolder(DatagramSplit data, ServerDatagramSocket socket) throws IOException {
        File folder = new File(data.getName().trim());
        File[] listOfFiles = folder.listFiles();
        String protocolNum = "505";
        String filesAsString =  protocolNum + ",";

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filesAsString += listOfFiles[i].getName() + ',';
            }
        }

        System.out.println(filesAsString);
        byte [] filesBytes = filesAsString.getBytes();
        System.out.println(filesBytes.length);
        socket.sendFile(data.getAddress(), data.getPortNo(), filesBytes);
    }

    public void downloadFileFromServer(DatagramSplit data, ServerDatagramSocket socket, Login login) throws IOException {
        Login loggedIn = login;
        String username = data.getName();
        String nameOfFile =  data.getFileName();
        VerifyUser user = loggedIn.getCurrentUser(data);
        String protocolNum = "504";

        if(user.isValid() == true){
            Path path = Paths.get(  username.trim() + "/" + nameOfFile.trim());
            nameOfFile = "," + nameOfFile + ",";
            byte[] fileDownload =  Files.readAllBytes(path);
            byte[] fileName = nameOfFile.getBytes();
            byte[] protocol = protocolNum.getBytes();
            byte[] file = new byte[fileName.length + fileDownload.length + 3];

            System.arraycopy(protocol, 0, file, 0, protocol.length);
            System.arraycopy(fileName, 0, file, protocol.length, fileName.length);
            System.arraycopy(fileDownload, 0, file, protocol.length + fileName.length, fileDownload.length);

             socket.sendFile(data.getAddress(), data.getPortNo(), file);
        }else{
            socket.sendMessage(data.getAddress(), data.getPortNo(), "503");
        }
    }
}
