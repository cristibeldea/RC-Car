import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {

    final int screenWidth = 300;
    final int screenHeight =300;
    KeyHandler keyH;
    public Display(SocketClient client) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        keyH = new KeyHandler(client);
        this.addKeyListener(keyH);
    }
}

