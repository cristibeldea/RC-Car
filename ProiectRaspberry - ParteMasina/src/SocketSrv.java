import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketSrv {

    private static final Logger log = LoggerFactory.getLogger(SocketSrv.class);

    ServerSocket socketServer;
    Socket s;
    DataInputStream dataIn;
    int direction;

    public void init() {
        try {
            socketServer = new ServerSocket(6666);
        } catch (Exception e) {
            log.error("Error in init", e);
        }
    }
    public void establishConnection() {

        try {
            s = socketServer.accept();//establishes connection
            log.info("Established new connection with {} port {}", s.getInetAddress(), s.getLocalPort());
            dataIn = new DataInputStream(s.getInputStream());
            //socketServer.close();
        } catch (Exception e) {
            log.error("Error in establishConnection()", e);
        }
    }

    public void receive() {
        try {
            direction = dataIn.read();
        } catch (Exception e) {
            log.error("Error in receive", e);
        }
    }

    public boolean isClosed() {
        return s.isClosed();
    }


}