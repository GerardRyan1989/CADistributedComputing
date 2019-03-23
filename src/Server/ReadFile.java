package Server;

public class ReadFile {

    public int getProtocolNumber(String message){
        String ProtocolString = message.substring(0,3);
        int protocolNumber = Integer.parseInt(ProtocolString);
        return protocolNumber;
    }
}
