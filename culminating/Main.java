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

    // Character Positions
    public static int dogxPos = 320;
    public static int dogyPos = 240;

    public static int catxPos = 400;
    public static int catyPos = 240;

    // arrow positions
    public static int menuArrowxPos = 255;
    public static int menuArrowyPos = 80;

    public static int selectArrowxPos = 230;
    public static int selectArrowyPos = 80;

    public static int arrowState = 1;

    // twoPlayers
    public static boolean twoPlayers;

    public static boolean upPressed = false;
    public static boolean leftPressed = false;
    public static boolean rightPressed = false;

    public static boolean wPressed = false;
    public static boolean aPressed = false;
    public static boolean dPressed = false;

    // Buffered Images
    public static BufferedImage[] stateImages = new BufferedImage[7];
    public static BufferedImage[] dogIdle = new BufferedImage[4];
    public static BufferedImage[] dogRun = new BufferedImage[6];
    public static BufferedImage[] catIdle = new BufferedImage[4];
    public static BufferedImage[] catRun = new BufferedImage[6];
    public static BufferedImage arrow;

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

        if (state == 0 || state == 3) {
            frameController++;
            g.drawImage(dogIdle[spriteNo], dogxPos, dogyPos, null);

            if (frameController == 4) {
                spriteNo = (spriteNo + 1) % 4;
                frameController = 0;
            }
            if (state == 0)
                g.drawImage(arrow, menuArrowxPos, menuArrowyPos, null);
            else
                g.drawImage(arrow, selectArrowxPos, selectArrowyPos, null);
        }

        if (state == 4) {
            g.drawImage(stateImages[state], 0, -339, null); // move y coord down until it turns 0;
            frameController++;

            if (!twoPlayers) {
                if (frameController == 4) {
                    spriteNo = (spriteNo + 1) % 4;
                    frameController = 0;
                }

                // if nothing is pressed
                g.drawImage(dogIdle[spriteNo], dogxPos, dogyPos, null); // doy y pos must be going down with the same
                                                                        // increment
                                                                        // as the y coord of the screen

                // put other floating islands/blocks here
            } else {
                if (frameController == 4) {
                    spriteNo = (spriteNo + 1) % 4;
                    frameController = 0;
                }

                // if nothing is pressed
                g.drawImage(dogIdle[spriteNo], dogxPos, dogyPos, null);
                g.drawImage(catIdle[spriteNo], catxPos, catyPos, null);

                // put other floating islands/blocks here
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        System.out.printf("x: %d, y: %d%n", e.getX(), e.getY());

        if (state == 0) { // menu
            if (e.getX() >= 284 && e.getX() <= 365 && e.getY() >= 79 && e.getY() <= 95) {
                state = 3; // Select num of players

                selectArrowxPos = 230;
                selectArrowyPos = 80;
                arrowState = 1;
            } else if (e.getX() >= 284 && e.getX() <= 364 && e.getY() >= 105 && e.getY() <= 122) {
                state = 2; // Rules
            } else if (e.getX() >= 264 && e.getX() <= 382 && e.getY() >= 132 && e.getY() <= 146) {
                state = 1; // Credits
            }
        }

        else if (state == 1) { // credits
            if (e.getX() >= 528 && e.getX() <= 545 && e.getY() >= 48 && e.getY() <= 61) {
                state = 0; // return to menu

                menuArrowxPos = 255;
                menuArrowyPos = 80;
                arrowState = 1;
            }
        }

        else if (state == 2) { // rules
            if (e.getX() >= 528 && e.getX() <= 545 && e.getY() >= 48 && e.getY() <= 61) {
                state = 0; // return to menu
            }
        }

        else if (state == 3) { // # of players selection
            if (e.getX() >= 259 && e.getY() >= 81 && e.getX() <= 387 && e.getY() <= 96) {
                state = 4; // play (SOLO)
                twoPlayers = false;
            } else if (e.getX() >= 250 && e.getY() >= 108 && e.getX() <= 397 && e.getY() <= 123) {
                state = 4; // play (TWO PLAYERS)
                twoPlayers = true;
            } else if (e.getX() >= 276 && e.getY() >= 137 && e.getX() <= 372 && e.getY() <= 147) {
                state = 0; // return to menu

                menuArrowxPos = 255;
                menuArrowyPos = 80;
                arrowState = 1;
            }
        }
    }

    public void keyPressed(KeyEvent e) {

        if (state == 0) {
            // down arrow
            if (e.getKeyCode() == KeyEvent.VK_DOWN && arrowState == 1) {
                menuArrowxPos = 260;
                menuArrowyPos = 105;
                arrowState = 2;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && arrowState == 2) {
                menuArrowxPos = 240;
                menuArrowyPos = 130;
                arrowState = 3;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && arrowState == 3) {
                menuArrowxPos = 255;
                menuArrowyPos = 80;
                arrowState = 1;
            }

            // up arrow
            else if (e.getKeyCode() == KeyEvent.VK_UP && arrowState == 3) {
                menuArrowxPos = 260;
                menuArrowyPos = 105;
                arrowState = 2;
            } else if (e.getKeyCode() == KeyEvent.VK_UP && arrowState == 2) {
                menuArrowxPos = 255;
                menuArrowyPos = 80;
                arrowState = 1;
            } else if (e.getKeyCode() == KeyEvent.VK_UP && arrowState == 1) {
                menuArrowxPos = 240;
                menuArrowyPos = 130;
                arrowState = 3;
            }

            else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (arrowState == 1) {
                    state = 3; // Select num of players

                    selectArrowxPos = 230;
                    selectArrowyPos = 80;
                    arrowState = 1;
                } else if (arrowState == 2)
                    state = 2;
                else if (arrowState == 3) {
                    state = 1;
                }
            }
        }

        else if (state == 1) { // credits
            if (e.getKeyChar() == ' ') {
                state = 0; // return to menu
            }
        }

        else if (state == 2) { // rules
            if (e.getKeyChar() == ' ') {
                state = 0; // return to menu
            }
        }

        else if (state == 3) { // select # of players

            // down arrow
            if (e.getKeyCode() == KeyEvent.VK_DOWN && arrowState == 1) {
                selectArrowxPos = 220;
                selectArrowyPos = 105;
                arrowState = 2;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && arrowState == 2) {
                selectArrowxPos = 240;
                selectArrowyPos = 130;
                arrowState = 3;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && arrowState == 3) {
                selectArrowxPos = 230;
                selectArrowyPos = 80;
                arrowState = 1;
            }

            // up arrow
            else if (e.getKeyCode() == KeyEvent.VK_UP && arrowState == 3) {
                selectArrowxPos = 220;
                selectArrowyPos = 105;
                arrowState = 2;
            } else if (e.getKeyCode() == KeyEvent.VK_UP && arrowState == 2) {
                selectArrowxPos = 230;
                selectArrowyPos = 80;
                arrowState = 1;
            } else if (e.getKeyCode() == KeyEvent.VK_UP && arrowState == 1) {
                selectArrowxPos = 240;
                selectArrowyPos = 130;
                arrowState = 3;
            }

            else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (arrowState == 1) {
                    state = 4; // 1 PLAYER
                } else if (arrowState == 2) {
                    state = 4; // 2 PLAYERS
                    twoPlayers = true;
                } else if (arrowState == 3) {
                    state = 0;

                    menuArrowxPos = 255;
                    menuArrowyPos = 80;
                    arrowState = 1;
                }
            }
        }

        else if (state == 4) { // in-game
            if (e.getKeyChar() == 'p') {
                state = 5; // pause
            } else if (e.getKeyChar() == 'g') {
                state = 6; // game over
                // only for transitions
            }
        }

        else if (state == 5) { // pause
            if (e.getKeyChar() == 'r') {
                state = 4;
                // reset all vars in here to restart the game
            }

            else if (e.getKeyChar() == ' ') {
                state = 4; // resume game
            }

            else if (e.getKeyChar() == 'm') {
                state = 0; // return to menu

                menuArrowxPos = 255;
                menuArrowyPos = 80;
                arrowState = 1;
            }
        }

        else if (state == 6) { // game over
            // reset all game variables here
            if (e.getKeyChar() == ' ') {
                state = 4; // restart
            }

            else if (e.getKeyChar() == 'm') {
                state = 0; // return to menu

                menuArrowxPos = 255;
                menuArrowyPos = 80;
                arrowState = 1;
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

            // dog sprites
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
            // ** need a dog running left

            // cat sprites
            catIdle[0] = ImageIO.read(new File("culminating/assets/sprites/cat/catIdle0.png"));
            catIdle[1] = ImageIO.read(new File("culminating/assets/sprites/cat/catIdle1.png"));
            catIdle[2] = ImageIO.read(new File("culminating/assets/sprites/cat/catIdle2.png"));
            catIdle[3] = ImageIO.read(new File("culminating/assets/sprites/cat/catIdle3.png"));

        } catch (Exception e) {
            System.out.println("Something wrong with the image!");
        }

        JFrame frame = new JFrame("FLOOD ESCAPE");
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

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
