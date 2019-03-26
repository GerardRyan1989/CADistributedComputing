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
                ServerDatagramPacket request = mySocket.receiveMessageAndSender();

                ReadFile readFile = new ReadFile();
                int protocolNumber =  readFile.getProtocolNum(request.getData());

                switch (protocolNumber) {
                    case 101:
                        System.out.println("Login request made.");
                        login.loginRequest(request, mySocket);
                        break;
                    case 102:
                        System.out.println("File Upload request made.");
                        fileManager.saveFileUploaded(request, mySocket, login);
                        break;
                    case 103:
                        System.out.println("File Download request made.");
                        fileManager.downloadFileFromServer(request, mySocket, login);
                        break;
                    case 104:
                        System.out.println("Logout request made.");
                        //login.logoutRequest(request, mySocket);
                        break;
                    default:
                        System.out.println("Unrecognised request type made.");
                }
            } //end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace( );
        } // end catch
    } //end main
} // end class

