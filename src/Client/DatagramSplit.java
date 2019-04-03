package Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatagramSplit {

    private int protocolNumber;
    private byte [] data;
    private String fileName;

    public DatagramSplit(String download){
        List<String> list = new ArrayList<String>(Arrays.asList(download.split(",")));
        this.protocolNumber = Integer.parseInt(list.get(0));

        if(this.protocolNumber == 506){
            //do nothing
        }
        else{
            this.fileName = list.get(1);
            this.data = list.get(2).getBytes();
        }

    }

    public int getProtocolNumber() {
        return protocolNumber;
    }


    public byte[] getData() {
        return data;
    }



    public String getFileName() {
        return fileName;
    }

}

