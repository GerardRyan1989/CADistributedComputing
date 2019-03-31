package Client;

import java.net.*;
import java.io.*;

/**
 * A subclass of DatagramSocket which contains
 * methods for sending and receiving messages
 * @author M. L. Liu
 */
public class ClientDatagramSocket extends DatagramSocket {
    static final int MAX_LEN = 100;
    ClientDatagramSocket( ) throws SocketException{
        super( );
    }

    public void sendMessage(InetAddress receiverHost, int receiverPort, String message) throws IOException {
        byte[ ] sendBuffer = message.getBytes( );
        DatagramPacket datagram = new DatagramPacket(sendBuffer, sendBuffer.length, receiverHost, receiverPort);
        this.send(datagram);
    } // end sendMessage

    public String receiveMessage() throws IOException {
        byte[ ] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        String message = new String(receiveBuffer);
        return message.trim();
    }

    public String sendFile(InetAddress receiverHost, int receiverPort, byte[] file) throws IOException {
        DatagramPacket datagram = new DatagramPacket(file, file.length, receiverHost, receiverPort);
        this.send(datagram);
        String receiveMessage = receiveMessage();
        return receiveMessage;
    }
} //end class

