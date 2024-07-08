import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class autoclick extends JFrame implements KeyListener {
    private volatile boolean running = true; // Volatile para asegurar la visibilidad entre threads
    private volatile boolean paused = false; // Volatile para asegurar la visibilidad entre threads

    public autoclick() {
        // Configuración de la ventana
        setTitle("AutoClick by Driscoll");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ESCAPE) { // Si se presiona la tecla ESCAPE
            paused = !paused; // Alternar el estado de pausa
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void startClicking() {
        try {
            Robot r = new Robot();
            int button = InputEvent.BUTTON1_DOWN_MASK;

            while (running) { // Bucle principal
                if (!paused) {
                    r.mousePress(button);
                    Thread.sleep(1);
                    r.mouseRelease(button);
                    Thread.sleep(1);
                } else {
                    // Si está en pausa, duerme un poco para no consumir CPU innecesariamente
                    Thread.sleep(100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        autoclick autoClicker = new autoclick();
        autoClicker.startClicking(); // Iniciar el autoclicker
    }
}