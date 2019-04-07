package Client;

import java.util.ArrayList;
import java.util.Arrays;


public class FilePacket{

    public static byte[] wrappedPacket(String protocol, String userName, String fileName, byte[] file) {
        byte[] protocolBytes = protocol.getBytes();
        byte[] userNameBytes = userName.getBytes();
        byte[] fileNameBytes = fileName.getBytes();
        byte[] fileContentBytes = file;


        byte[] protocolAndUsername = concatByteArrays(protocolBytes, userNameBytes);
        byte[] fileNameAndContent = concatByteArrays(fileNameBytes, fileContentBytes);

        return concatByteArrays(protocolAndUsername, fileNameAndContent);
    }

    public static byte[] concatByteArrays(byte[] byteArr1, byte[] byteArr2) {
        byte[] concatenatedByteArrays = new byte[byteArr1.length + byteArr2.length];
        System.arraycopy(byteArr1, 0, concatenatedByteArrays, 0, byteArr1.length);
        System.arraycopy(byteArr2, 0, concatenatedByteArrays, byteArr1.length, byteArr2.length);
        return concatenatedByteArrays;
    }
}
