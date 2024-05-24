import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        log.info("start");
        int currentDirection = 0;

        SocketSrv socketServer = new SocketSrv();
        socketServer.init();

        GPIOControl gpioControl = new GPIOControl();
        gpioControl.setupPins();


        while (true) {
            log.info("Waiting for connection...");
            socketServer.establishConnection();
            log.info("Established connection, reading data...");

            while (!socketServer.isClosed()) {
                socketServer.receive();

                if (currentDirection != socketServer.direction) {
                    currentDirection = socketServer.direction;
                    gpioControl.setInput(socketServer.direction);
                    gpioControl.managePinOutput();
                }

            }

            log.info("connection closed...");
        }

    }

}