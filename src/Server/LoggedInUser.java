package Server;

import java.net.InetAddress;

public class LoggedInUser {

    private String name;
    private String password;
    private InetAddress address;
    private int port;

    public LoggedInUser(String name, String password, InetAddress address, int port) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
