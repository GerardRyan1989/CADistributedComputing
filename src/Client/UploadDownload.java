package Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UploadDownload extends Component {

    public UploadDownload(){

    }

    public void uploadFileToServer(String hostname, String port) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);

        String filePathToUpload = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println(filePathToUpload);

        ClientHelper helper = new ClientHelper(hostname, port);
        String response = helper.sendFileToServer(filePathToUpload);
        try {
            if(response.equals("111")){
                System.out.println("result was 111");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}



