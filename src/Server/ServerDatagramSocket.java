package Server;

import java.net.*;
import java.io.*;

/**
 * A subclass of DatagramSocket which contains
 * methods for sending and receiving messages
 * @author M. L. Liu
 */

public class ServerDatagramSocket extends DatagramSocket {
    static final int MAX_LEN = 65536;
    ServerDatagramSocket(int portNo) throws SocketException{
        super(portNo);
    }
    public void sendMessage(InetAddress receiverHost, int receiverPort, String message) throws IOException {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket datagram = new DatagramPacket(sendBuffer, sendBuffer.length, receiverHost, receiverPort);
        this.send(datagram);
    } // end sendMessage

    public String receiveMessage( ) throws IOException {
        byte[] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        String message = new String(receiveBuffer);
        return message;
    } //end receiveMessage

    public DatagramSplit receiveMessageAsDatagramSplit( ) throws IOException {
        byte[ ] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        // create a DatagramMessage object, to contain message
        // received and sender's address
        DatagramSplit packet = new DatagramSplit(datagram) ;
        return packet;
    } //end receiveMessage


    public void sendFile(InetAddress receiverHost, int receiverPort, byte[] file) throws IOException {
        DatagramPacket datagram = new DatagramPacket(file, file.length, receiverHost, receiverPort);
        this.send(datagram);
        //String receiveMessage = receiveMessage();
        //return receiveMessage;
    }
} //end class


