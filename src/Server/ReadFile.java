package Server;

import java.util.Arrays;

public class ReadFile {

    public int getProtocolNum(byte[] data){
        String ProtocolString = new String(Arrays.copyOfRange(data,  0,  3));
        int protocolNumber = Integer.parseInt(ProtocolString);
        return protocolNumber;
    }

    public static int getFileSize(byte[] data) {
        byte[] size = new byte[4];
        System.arraycopy(data, 3, size, 0, size.length);
        System.out.println("size of file is: " + java.nio.ByteBuffer.wrap(size).getInt());
        return java.nio.ByteBuffer.wrap(size).getInt();
    }
    public String getFileName(byte[] data) {
        byte[] fileName = new byte[9];
        System.arraycopy(data, 7, fileName, 0, fileName.length);
        System.out.println("name of file is: " + new String(fileName));
        return new String(fileName).trim();
    }
    public static byte[] getFileContent(byte[] data) {
        int fileSize = getFileSize(data);
        byte[] fileContent = new byte[fileSize];
        System.arraycopy(data, 17, fileContent, 0, fileContent.length);
        System.out.println("File content as string: " + new String(fileContent));
        return fileContent;
    }

    public String getUserName(byte[] data){
        String array = new String(data);
        String userName = array.substring(array.lastIndexOf('!') + 1, array.indexOf('@'));
        return userName;

    }

    public String getFileNameAString(byte[] data){
        String array = new String(data);
        String filename = array.substring(array.lastIndexOf('$') + 1, array.indexOf('^'));
        return filename;
    }

    public String getPassword(byte[] data){
        String array = new String(data);
        String password = array.substring(array.lastIndexOf('%') + 1, array.indexOf('&'));
        return password;

    }
}
