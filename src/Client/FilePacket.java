package Client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static String [] splitDataIntoLIst(byte [] data) {
        String downLoadedFile = new String(data);
        ArrayList<String> listAsArray = new ArrayList(Arrays.asList(downLoadedFile.split(",")));
        listAsArray.remove(0);
        String [] list = listAsArray.toArray(new String[listAsArray.size()]);
        return list;
    }







}
