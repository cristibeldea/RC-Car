import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyHandler implements KeyListener {

    SocketClient client;
    boolean wKeyPressed = false, aKeyPressed = false, sKeyPressed = false, dKeyPressed = false;
   // boolean alreadyPressed = false;
   // char previousKey='$';
    public KeyHandler(SocketClient client){
        this.client = client;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyChar()=='w'){
           wKeyPressed = true;
        }

        if(e.getKeyChar()=='a'){
            aKeyPressed = true;
        }

        if(e.getKeyChar()=='s'){
           sKeyPressed = true;
        }

        if(e.getKeyChar()=='d'){
            dKeyPressed = true;
        }

      /* if(aKeyPressed || wKeyPressed || dKeyPressed || sKeyPressed) {
            try {
                if (!alreadyPressed || e.getKeyChar() != previousKey) {
                    alreadyPressed = true;
                    if (previousKey != '$')
                        previousKey = e.getKeyChar();
                    else
                        previousKey = '@';
                    manageDataOut();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } */

    }

    @Override
    public void keyReleased(KeyEvent e) {

        char input = e.getKeyChar();
        if(input =='w' || input =='a' || input =='d' || input =='s') {

            if (e.getKeyChar() == 'w') {
                wKeyPressed = false;
            }

            if (e.getKeyChar() == 'a') {
                aKeyPressed = false;
            }

            if (e.getKeyChar() == 's') {
                sKeyPressed = false;
            }

            if (e.getKeyChar() == 'd') {
                dKeyPressed = false;
            }

         /*   alreadyPressed = false;
            previousKey = '@';
            try {
                manageDataOut();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } */
        }
    }

    public void manageDataOut() throws IOException {
        if(wKeyPressed && !aKeyPressed && !dKeyPressed && !sKeyPressed){
            client.setInput(1);
            client.sendData();
            System.out.println("Mergi in fata!");
        }

        else if(!wKeyPressed && !aKeyPressed && !dKeyPressed && sKeyPressed){
            client.setInput(2);
            client.sendData();
            System.out.println("Mergi in spate!");
        }

        else if(wKeyPressed && aKeyPressed && !dKeyPressed && !sKeyPressed){
            client.setInput(3);
            client.sendData();
            System.out.println("Mergi in fata-stanga!");
        }

        else if(wKeyPressed && !aKeyPressed && dKeyPressed && !sKeyPressed){
            client.setInput(4);
            client.sendData();
            System.out.println("Mergi in fata-dreapta!");
        }

        else if(!wKeyPressed && aKeyPressed && !dKeyPressed && sKeyPressed){
             client.setInput(5);
            client.sendData();
            System.out.println("Mergi in spate-stanga!");
        }

        else if(!wKeyPressed && !aKeyPressed && dKeyPressed && sKeyPressed){
            client.setInput(6);
            client.sendData();
            System.out.println("Mergi in spate-dreapta!");
        }

        else if(!wKeyPressed && !aKeyPressed && !dKeyPressed && !sKeyPressed ){
            client.setInput(0);
            client.sendData();
            System.out.println("Nu mergi!");
        }

        else {
             client.setInput(0);
             client.sendData();
            System.out.println("Nu mergi!");
        }
    }
}

