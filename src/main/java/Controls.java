import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {

    public boolean wPressed, sPressed, dPressed, aPressed, upPressed, downPressed, rightPressed, leftPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) wPressed = true;
        if (keyCode == KeyEvent.VK_S) sPressed = true;
        if (keyCode == KeyEvent.VK_D) dPressed = true;
        if (keyCode == KeyEvent.VK_A) aPressed = true;
        if (keyCode == KeyEvent.VK_UP) upPressed = true;
        if (keyCode == KeyEvent.VK_DOWN) downPressed = true;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = true;
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) wPressed = false;
        if (keyCode == KeyEvent.VK_S) sPressed = false;
        if (keyCode == KeyEvent.VK_D) dPressed = false;
        if (keyCode == KeyEvent.VK_A) aPressed = false;
        if (keyCode == KeyEvent.VK_UP) upPressed = false;
        if (keyCode == KeyEvent.VK_DOWN) downPressed = false;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = false;
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = false;
    }
}
