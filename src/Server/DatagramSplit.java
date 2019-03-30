package Server;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

public class DatagramSplit {

    String name;
    String password;
    InetAddress address;
    int portNo;
    int protocolNumber;
    byte [] data;
    String fileName;


    public DatagramSplit(DatagramPacket packet){
        setProtocolNumber(packet.getData());
        setAddress(packet.getAddress());
        setPortNo(packet.getPort());
        String data = new String(packet.getData());
        if(this.protocolNumber == 101 || this.protocolNumber == 104){
            setNameWithPassword(packet.getData());
            setPassword(packet.getData());
        }else{
            setName(packet.getData());
            setData(packet.getData());
            setFileName(packet.getData());
        }

    }

    public String getName(){
        return name;
    }

    public void setName(byte [] data){
        String uploadedFile = new String(data);
        List<String> list = Arrays.asList(uploadedFile.split(","));
        this.name = list.get(1);
    }

    public void setNameWithPassword(byte [] data){
        String uploadedFile = new String(data);
        List<String> list = Arrays.asList(uploadedFile.split(","));
        this.name = list.get(1);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(byte[] data) {
        String uploadedFile = new String(data);
        List<String> list = Arrays.asList(uploadedFile.split(","));
        this.password = list.get(2);
    }

    public int getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(byte[] data) {
        String uploadedFile = new String(data);
        List<String> list = Arrays.asList(uploadedFile.split(","));
        this.protocolNumber = Integer.parseInt(list.get(0));
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        String uploadedFile = new String(data);
        List<String> list = Arrays.asList(uploadedFile.split(","));
        this.data = list.get(3).getBytes();
    }


    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPortNo() {
        return portNo;
    }

    public void setPortNo(int portNo) {
        this.portNo = portNo;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(byte[] data) {
        String uploadedFile = new String(data);
        List<String> list = Arrays.asList(uploadedFile.split(","));
        this.fileName = list.get(2);

    }


}
