package Server;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {


    public FileManager(){

    }


    public void saveFileUploaded(ServerDatagramPacket packet, ServerDatagramSocket socket, Login login) throws IOException {
        Login loggedIn = login;
        ReadFile read = new ReadFile();
        ServerDatagramPacket uploadFile = packet;
        ServerDatagramSocket serverSocket = socket;
        byte [] data = uploadFile.getData();

        VerifyUser user = loggedIn.getCurrentUser(packet);
        String nameOfFile = read.getFileName(uploadFile.getData());
        System.out.println(data);

        if(user.isValid() == true){
            new File(user.getUserName()).mkdir();
            Path path = Paths.get(user.getUserName().trim() + "/" + nameOfFile.trim());
            Files.write(path,data);

        }else{

        }


    }


    public void downloadFileFromServer(){

    }



}
