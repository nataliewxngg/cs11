package culminating;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Main extends JPanel implements KeyListener, MouseListener, Runnable {

    // Global Variables
    public static int state = 0; // 0 - Menu
                                 // 1 - Credits
                                 // 2 - Rules
                                 // 3 - # of Players Selection
                                 // 4 - In-Game
                                 // 5 - Pause
                                 // 6 - Game Over

    public static int spriteNo = 0;
    public static int frameController = 0;

    public static int dogxPos = 320;
    public static int dogyPos = 240;

    public static int birdxPos = 0;
    public static int birdyPos = 180;

    public static BufferedImage[] stateImages = new BufferedImage[4];

    public static BufferedImage[] dogIdle = new BufferedImage[4];
    public static BufferedImage[] dogRun = new BufferedImage[6];

    public Main() {
        // JPanel default settings
        setPreferredSize(new Dimension(700, 360));

        // Add MouseListener and KeyListener
        this.setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);

        // Timer
        Thread t = new Thread(this);
        t.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen
        g.drawImage(stateImages[state], 0, 0, null);

        if (state == 0) {
            frameController++;
            g.drawImage(dogIdle[spriteNo], dogxPos, dogyPos, null);

            if (frameController == 4) {
                spriteNo = (spriteNo + 1) % 4;
                frameController = 0;
            }
        }

    }

    public void mousePressed(MouseEvent e) {
        System.out.printf("x: %d, y: %d%n", e.getX(), e.getY());

        if (state == 0) {
            if (e.getX() >= 284 && e.getX() <= 365 && e.getY() >= 79 && e.getY() <= 95) {
                state = 3; // Play
            } else if (e.getX() >= 284 && e.getX() <= 364 && e.getY() >= 105 && e.getY() <= 122) {
                state = 2; // Rules
            } else if (e.getX() >= 264 && e.getX() <= 382 && e.getY() >= 132 && e.getY() <= 146) {
                state = 1; // Credits
            }
        }

        else if (state == 1) {
            if (e.getX() >= 528 && e.getX() <= 545 && e.getY() >= 48 && e.getY() <= 61) {
                state = 0;
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (state == 1) {
            if (e.getKeyChar() == ' ') {
                state = 0;
            }
        }
    }

    public static void main(String[] args) {
        try {
            stateImages[0] = ImageIO.read(new File("culminating/assets/0menu.png"));
            stateImages[1] = ImageIO.read(new File("culminating/assets/1credits.png"));

            dogIdle[0] = ImageIO.read(new File("culminating/assets/sprites/dog/dogIdle0.png"));
            dogIdle[1] = ImageIO.read(new File("culminating/assets/sprites/dog/dogIdle1.png"));
            dogIdle[2] = ImageIO.read(new File("culminating/assets/sprites/dog/dogIdle2.png"));
            dogIdle[3] = ImageIO.read(new File("culminating/assets/sprites/dog/dogIdle3.png"));

            dogRun[0] = ImageIO.read(new File("culminating/assets/sprites/dog/dogRun0.png"));
            dogRun[1] = ImageIO.read(new File("culminating/assets/sprites/dog/dogRun1.png"));
            dogRun[2] = ImageIO.read(new File("culminating/assets/sprites/dog/dogRun2.png"));
            dogRun[3] = ImageIO.read(new File("culminating/assets/sprites/dog/dogRun3.png"));
            dogRun[4] = ImageIO.read(new File("culminating/assets/sprites/dog/dogRun4.png"));
            dogRun[5] = ImageIO.read(new File("culminating/assets/sprites/dog/dogRun5.png"));

        } catch (Exception e) {
            System.out.println("Something wrong with the image!");
        }

        JFrame frame = new JFrame("PLATFORMERS");
        Main panel = new Main();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }

    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
        }
    }

    // Useless Methods
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
