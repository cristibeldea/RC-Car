import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {

    String hostname="192.168.4.1";
    int port = 6666;
    int controlInput=0;
    DataOutputStream out;
    Socket socket;

    public void establishConnection(){
        try {
            socket = new Socket(hostname, port);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public void sendData() throws IOException {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            out.write(controlInput);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void setInput(int controlInput){
        this.controlInput = controlInput;
    }
}
