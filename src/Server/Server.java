package Server;
/**
 * This module contains the application logic of an echo server
 * which uses a connectionless datagram socket for interprocess
 * communication.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 */

public class Server {
    public static void main(String[] args) {
        int serverPort = 7;    // default port
        if (args.length == 1 )
            serverPort = Integer.parseInt(args[0]);

        try {
            ServerDatagramSocket mySocket = new ServerDatagramSocket(serverPort);
            System.out.println("Echo server ready.");
            Login login = new Login();
            FileManager fileManager = new FileManager();

            while (true) {
                DatagramSplit clientData = mySocket.receiveMessageAsDatagramSplit();

                switch (clientData.protocolNumber) {
                    case 101:
                        System.out.println("Login request.");
                        login.loginRequest(clientData, mySocket);
                        break;
                    case 102:
                        System.out.println("File Upload request.");
                        fileManager.saveFileUploaded(clientData, mySocket, login);
                        break;
                    case 103:
                        System.out.println("File Download request.");
                        fileManager.downloadFileFromServer(clientData, mySocket, login);
                        break;
                    case 104:
                        System.out.println("Logout Request");
                        login.logoutRequest(clientData, mySocket);
                        break;
                    default:
                        System.out.println("Unknown Request.");
                }
            } //end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace( );
        } // end catch
    } //end main
} // end class

