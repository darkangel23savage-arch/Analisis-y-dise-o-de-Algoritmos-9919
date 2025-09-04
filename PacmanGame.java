import javax.swing.*;
        import java.awt.*;
        import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacmanGame extends JPanel implements KeyListener, Runnable {
    private int pacmanX = 100, pacmanY = 100;
    private int pacmanSpeed = 5;
    private int pacmanSize = 40;
    private int direction = KeyEvent.VK_RIGHT;
    private boolean running = true;
    public PacmanGame() {
        setFocusable(true);
        addKeyListener(this);
        new Thread(this).start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.YELLOW);
        int startAngle = 30;
        int arcAngle = 300;
        if (direction == KeyEvent.VK_LEFT) {
            startAngle = 210;
        } else if (direction == KeyEvent.VK_UP) {
            startAngle = 120;
        } else if (direction == KeyEvent.VK_DOWN) {
            startAngle = 300;
        }

        g.fillArc(pacmanX, pacmanY, pacmanSize, pacmanSize, startAngle, arcAngle);
    }
    @Override
    public void run() {
        while (running) {
            if (direction == KeyEvent.VK_LEFT) {
                pacmanX -= pacmanSpeed;
            } else if (direction == KeyEvent.VK_RIGHT) {
                pacmanX += pacmanSpeed;
            } else if (direction == KeyEvent.VK_UP) {
                pacmanY -= pacmanSpeed;
            } else if (direction == KeyEvent.VK_DOWN) {
                pacmanY += pacmanSpeed;
            }
            if (pacmanX < 0) pacmanX = 0;
            if (pacmanY < 0) pacmanY = 0;
            if (pacmanX > getWidth() - pacmanSize) pacmanX = getWidth() - pacmanSize;
            if (pacmanY > getHeight() - pacmanSize) pacmanY = getHeight() - pacmanSize;
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT ||
                key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            direction = key;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mini Pacman");
        PacmanGame game = new PacmanGame();
        frame.add(game);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

