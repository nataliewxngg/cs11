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
                                 // 2 - In-Game
                                 // 3 - Pause
                                 // 4 - Game Over

    public static int spriteNo = 0;
    public static int frameController = 0;

    public static int xPos = 320;
    public static int yPos = 240;

    public static BufferedImage[] stateImages = new BufferedImage[4];
    public static BufferedImage[] dogIdle = new BufferedImage[4];

    public Main() {
        // JPanel default settings
        setPreferredSize(new Dimension(700, 361));

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
            g.drawImage(dogIdle[spriteNo], xPos, yPos, null);

            if (frameController == 4) {
                spriteNo = (spriteNo + 1) % 4;
                frameController = 0;
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public static void main(String[] args) {
        try {
            stateImages[0] = ImageIO.read(new File("culminating/assets/menu.png"));

            dogIdle[0] = ImageIO.read(new File("culminating/assets/sprites/dogIdle0.png"));
            dogIdle[1] = ImageIO.read(new File("culminating/assets/sprites/dogIdle1.png"));
            dogIdle[2] = ImageIO.read(new File("culminating/assets/sprites/dogIdle2.png"));
            dogIdle[3] = ImageIO.read(new File("culminating/assets/sprites/dogIdle3.png"));

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
