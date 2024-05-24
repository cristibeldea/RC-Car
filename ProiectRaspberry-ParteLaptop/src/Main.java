import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketClient client = new SocketClient();
        client.establishConnection();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Controller");

        Display display = new Display(client);
        window.add(display);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        display.keyH.manageDataOut();

        while(true){
            display.keyH.manageDataOut();
            Thread.sleep(50);
        }

    }
}