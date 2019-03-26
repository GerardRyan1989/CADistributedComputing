package Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class FileManager {


    public FileManager(){

    }

    public void saveFileUploaded(ServerDatagramPacket packet, ServerDatagramSocket socket, Login login) throws IOException {
        Login loggedIn = login;
        ReadFile read = new ReadFile();
        ServerDatagramPacket uploadFile = packet;
        byte [] data = uploadFile.getData();
        String uploadedFile = new String(data);
        List<String> list = Arrays.asList(uploadedFile.split(","));
        VerifyUser user = loggedIn.getCurrentUser(packet);
        String nameOfFile = list.get(2);
        byte [] file = list.get(3).getBytes();

        if(user.isValid() == true){
            new File(user.getUserName()).mkdir();
            Path path = Paths.get(user.getUserName().trim() + "/" + nameOfFile.trim());
            Files.write(path,file);
            socket.sendMessage(packet.getAddress(), packet.getPort(), "501");
        }else{
            socket.sendMessage(packet.getAddress(), packet.getPort(), "502");
        }
    }


    public void downloadFileFromServer(ServerDatagramPacket packet, ServerDatagramSocket socket, Login login) throws IOException {
        Login loggedIn = login;
        ReadFile readFile = new ReadFile();
        String username = readFile.getUserName(packet.getData());
        String nameOfFile =  readFile.getFileNameAString(packet.getData());

        Path path = Paths.get(  username.trim() + "/" + nameOfFile.trim());
        nameOfFile = "," + nameOfFile + ",";
        byte[] fileDownload =  Files.readAllBytes(path);
        byte[] fileName = nameOfFile.getBytes();
        byte[] file = new byte[fileName.length + fileDownload.length];
        System.arraycopy(fileName, 0, file, 0, fileName.length);
        System.arraycopy(fileDownload, 0, file, fileName.length, fileDownload.length);

        System.out.println("hello");
        socket.sendFile(packet.getAddress(), packet.getPort(), file);
    }



}
