package Client;
import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * This class is a module which provides the application logic
 * for an Echo client using connectionless datagram socket.
 * @author M. L. Liu
 */
public class ClientHelper {
    private ClientDatagramSocket mySocket;
    private InetAddress serverHost;
    private int serverPort;

    ClientHelper(String hostName, String portNum)
            throws SocketException, UnknownHostException {
        this.serverHost = InetAddress.getByName(hostName);
        this.serverPort = Integer.parseInt(portNum);
        // instantiates a datagram socket for both sending
        // and receiving data
        this.mySocket = new ClientDatagramSocket();
    }

    public String getEcho(String message) throws SocketException, IOException {
        String echo = "";
        mySocket.sendMessage(serverHost, serverPort, message);
        // now receive the echo
        echo = mySocket.receiveMessage();
        return echo;
    } //end getEcho

    public void done( ) throws SocketException {
        mySocket.close( );
    }  //end done


    public String sendFileToServer(String username, String pathToFile) throws IOException {

        byte[] data = FilePacket.getBytesFromPath(pathToFile);
        String name = FilePacket.getFileNameFromPath(pathToFile);
        byte[] bytesForPacket = FilePacket.wrappedPacket("102,", username + ",",   name + "," , data);
        return mySocket.sendFile(serverHost, serverPort, bytesForPacket);
    }



} //end class
