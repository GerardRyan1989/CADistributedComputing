package Client;
import java.io.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is a module which provides the application logic
 * for an Echo client using connectionless datagram socket.
 * @author M. L. Liu
 */


public class ClientHelper {
    private ClientDatagramSocket mySocket;
    private InetAddress serverHost;
    private int serverPort;


    //instantiates a new clienthelper
    ClientHelper(String hostName, String portNum) throws SocketException, UnknownHostException {
        this.serverHost = InetAddress.getByName(hostName);
        this.serverPort = Integer.parseInt(portNum);
        // instantiates a datagram socket for both sending and receiving data
        this.mySocket = new ClientDatagramSocket();
    }

    //sends message to server and recieves response from server
    public String getEcho(String message) throws IOException {
        String echo = "";
        mySocket.sendMessage(serverHost, serverPort, message);
        // now receive the echo
        echo = mySocket.receiveMessage();
        return echo;
    } //end getEcho

    public void done() throws SocketException {
        mySocket.close();
    }  //end done


    //sends a file to server and recives a response from server
    public String sendFileToServer(String username, String pathToFile) throws IOException {
        Path path = Paths.get(pathToFile);
        byte[] data = Files.readAllBytes(path);
        String name = path.getFileName().toString();
        byte[] bytesForPacket = FilePacket.wrappedPacket("102,", username + ",",   name + "," , data);
        return mySocket.sendFile(serverHost, serverPort, bytesForPacket);
    }



} //end class
