package culminating;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class test extends JPanel implements KeyListener, MouseListener, Runnable {

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
    public static int arrowxPos = 255;
    public static int arrowyPos = 80;

    // arrow positions
    public static int menuArrow = 1;
    public static int selectArrow = 1;

    public static boolean twoPlayers;

    public static BufferedImage[] stateImages = new BufferedImage[7];

    public static BufferedImage[] dogIdle = new BufferedImage[4];
    public static BufferedImage[] dogRun = new BufferedImage[6];
    public static BufferedImage arrow;

    public test() {
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

        if (state == 0 || state == 3) {
            frameController++;
            g.drawImage(dogIdle[spriteNo], dogxPos, dogyPos, null);

            if (frameController == 4) {
                spriteNo = (spriteNo + 1) % 4;
                frameController = 0;
            }
        }

        if (state == 0)
            g.drawImage(arrow, arrowxPos, arrowyPos, null);
        if (state == 1)
            g.drawImage(arrow, arrowxPos, arrowyPos, null);

    }

    public void mousePressed(MouseEvent e) {
        System.out.printf("x: %d, y: %d%n", e.getX(), e.getY());

        if (state == 0) {
            if (e.getX() >= 284 && e.getX() <= 365 && e.getY() >= 79 && e.getY() <= 95) {
                state = 3; // Select num of players
            } else if (e.getX() >= 284 && e.getX() <= 364 && e.getY() >= 105 && e.getY() <= 122) {
                state = 2; // Rules
            } else if (e.getX() >= 264 && e.getX() <= 382 && e.getY() >= 132 && e.getY() <= 146) {
                state = 1; // Credits
            }
        }

        else if (state == 3) {
            if (e.getX() >= 259 && e.getY() >= 81 && e.getX() <= 387 && e.getY() <= 96) {
                state = 4; // play
                twoPlayers = false;
            } else if (e.getX() >= 250 && e.getY() >= 108 && e.getX() <= 397 && e.getY() <= 123) {
                state = 4; // play
                twoPlayers = true;
            } else if (e.getX() >= 276 && e.getY() >= 137 && e.getX() <= 372 && e.getY() <= 147) {
                state = 0;
            }

        }

        else if (state == 1) {
            if (e.getX() >= 528 && e.getX() <= 545 && e.getY() >= 48 && e.getY() <= 61) {
                state = 0;
            }
        }

        else if (state == 2) {
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

        if (state == 2) {
            if (e.getKeyChar() == ' ') {
                state = 0;
            }
        }

        if (state == 4) {
            if (e.getKeyChar() == 'p') {
                state = 5;
            } else if (e.getKeyChar() == 'g') {
                state = 6;
            }
        }

        if (state == 5) {
            if (e.getKeyChar() == 'r') {
                state = 4;
            }

            else if (e.getKeyChar() == ' ') {
                state = 4;
            }

            else if (e.getKeyChar() == 'm') {
                state = 0;
            }
        }

        if (state == 6) {
            if (e.getKeyChar() == ' ') {
                state = 4;
            }

            else if (e.getKeyChar() == 'm') {
                state = 0;
            }
        }

        if (state == 0) {
            if (e.getKeyCode() == 40 && menuArrow == 1) {
                arrowxPos = 260;
                arrowyPos = 105;
                menuArrow = 2;
            } else if (e.getKeyCode() == 40 && menuArrow == 2) {
                arrowxPos = 240;
                arrowyPos = 130;
                menuArrow = 3;
            } else if (e.getKeyCode() == 38 && menuArrow == 3) {
                arrowxPos = 260;
                arrowyPos = 105;
                menuArrow = 2;
            } else if (e.getKeyCode() == 38 && menuArrow == 2) {
                arrowxPos = 255;
                arrowyPos = 80;
                menuArrow = 1;
            }
        }

        if (state == 1) {
            if (e.getKeyCode() == 40 && selectArrow == 1) {
                arrowxPos = 260;
                arrowyPos = 105;
                menuArrow = 2;
            } else if (e.getKeyCode() == 40 && selectArrow == 2) {
                arrowxPos = 240;
                arrowyPos = 130;
                menuArrow = 3;
            } else if (e.getKeyCode() == 38 && selectArrow == 3) {
                arrowxPos = 260;
                arrowyPos = 105;
                menuArrow = 2;
            } else if (e.getKeyCode() == 38 && selectArrow == 2) {
                arrowxPos = 255;
                arrowyPos = 80;
                menuArrow = 1;
            }
        }

    }

    public static void main(String[] args) {
        try {
            stateImages[0] = ImageIO.read(new File("culminating/assets/0menu.png"));
            stateImages[1] = ImageIO.read(new File("culminating/assets/1credits.png"));
            stateImages[2] = ImageIO.read(new File("culminating/assets/2rules.png"));
            stateImages[3] = ImageIO.read(new File("culminating/assets/3selectNumOfPlayers.png"));
            stateImages[4] = ImageIO.read(new File("culminating/assets/4inGame.png"));
            stateImages[5] = ImageIO.read(new File("culminating/assets/5pause.png"));
            stateImages[6] = ImageIO.read(new File("culminating/assets/6gameOver.png"));

            arrow = ImageIO.read(new File("culminating/assets/arrow.png"));

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

        JFrame frame = new JFrame("FLOOD ESCAPE");
        test panel = new test();
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
