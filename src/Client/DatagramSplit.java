package Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatagramSplit {

    int protocolNumber;
        byte [] data;
        String fileName;

        public DatagramSplit(String download){
            List<String> list = new ArrayList<String>(Arrays.asList(download.split(",")));
            this.protocolNumber = Integer.parseInt(list.get(0).toString());
            this.fileName = list.get(1);
            this.data = list.get(2).getBytes();
        }

    public int getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(int protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

