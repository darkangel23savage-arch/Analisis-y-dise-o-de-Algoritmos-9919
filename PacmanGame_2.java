import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacmanGame_2 extends JPanel implements KeyListener, Runnable {

    private int pacmanX = 60, pacmanY = 60;
    private int pacmanSpeed = 5;
    private int pacmanSize = 30;
    private int direction = KeyEvent.VK_RIGHT;
    private boolean running = true;
    private final int[][] maze = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,1,1,1,0,0,1,0,1,1,0,1,0,1,1,1,1,0,1},
            {1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1},
            {1,1,1,0,1,1,1,1,0,1,0,1,1,1,1,0,1,1,0,1},
            {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1},
            {1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    private final int cellSize = 30;

    public PacmanGame_2() {
        setFocusable(true);
        addKeyListener(this);
        new Thread(this).start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[row][col] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }
            }
        }
        g.setColor(Color.YELLOW);
        int startAngle = switch (direction) {
            case KeyEvent.VK_LEFT -> 210;
            case KeyEvent.VK_UP -> 120;
            case KeyEvent.VK_DOWN -> 300;
            default -> 30;
        };
        g.fillArc(pacmanX, pacmanY, pacmanSize, pacmanSize, startAngle, 300);
    }
    @Override
    public void run() {
        while (running) {
            int oldX = pacmanX;
            int oldY = pacmanY;
            if (direction == KeyEvent.VK_LEFT) pacmanX -= pacmanSpeed;
            if (direction == KeyEvent.VK_RIGHT) pacmanX += pacmanSpeed;
            if (direction == KeyEvent.VK_UP) pacmanY -= pacmanSpeed;
            if (direction == KeyEvent.VK_DOWN) pacmanY += pacmanSpeed;
            if (isWallCollision()) {
                pacmanX = oldX;
                pacmanY = oldY;
            }
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isWallCollision() {
        int row = pacmanY / cellSize;
        int col = pacmanX / cellSize;
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length) return true;
        return maze[row][col] == 1;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        direction = e.getKeyCode();
    }
    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pacman con Laberinto");
        PacmanGame_2 game = new PacmanGame_2();
        frame.add(game);
        frame.setSize(620, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
