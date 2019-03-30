package Client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePacket{

    public static byte[] wrappedPacket(String protocol, String userName, String fileName, byte[] file) {
        byte[] protocolBytes = getBytesFromString(protocol);
        byte[] userNameBytes = getBytesFromUSername(userName);
        byte[] fileNameBytes = getBytesFromString(fileName);
        byte[] fileContentBytes = file;


        byte[] protocolAndUsername = concatByteArrays(protocolBytes, userNameBytes);
        byte[] fileNameAndContent = concatByteArrays(fileNameBytes, fileContentBytes);

        return concatByteArrays(protocolAndUsername, fileNameAndContent);
    }

    public static byte[] getBytesFromString(String string) {
        return string.getBytes();
    }

    public static byte[] getBytesFromInt(int integer) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(integer);
        return buffer.array();
    }

    public static byte[] concatByteArrays(byte[] byteArr1, byte[] byteArr2) {
        byte[] concatenatedByteArrays = new byte[byteArr1.length + byteArr2.length];
        System.arraycopy(byteArr1, 0, concatenatedByteArrays, 0, byteArr1.length);
        System.arraycopy(byteArr2, 0, concatenatedByteArrays, byteArr1.length, byteArr2.length);
        return concatenatedByteArrays;
    }

    public static byte[] getBytesFromPath(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
    public static String getFileNameFromPath(String filePath) {
        Path path = Paths.get(filePath);
        String name = path.getFileName().toString();
        return name;
    }

    public static byte[] getBytesFromUSername(String username) {
        byte[] nameInBytes = username.getBytes();
        return nameInBytes;
    }

}
