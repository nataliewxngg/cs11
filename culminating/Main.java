package culminating;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import java.util.Random;

public class Main extends JPanel implements KeyListener, Runnable {

    // Global Variables
    public static Random rand = new Random();

    public static int state = 0; // 0 - Menu
                                 // 1 - Credits
                                 // 2 - Rules
                                 // 3 - # of Players Selection
                                 // 4 - In-Game
                                 // 5 - Pause
                                 // 6 - Game Over
    public static int idleSpriteNo = 0;
    public static int runSpriteNo = 0;
    public static int frameController = 0;

    public static int bgyPos = -700;
    public static double bgMoveUpFactor = 0.00000000001;

    public static int numOfPlatforms = 0;
    public static int landx;
    public static int landy;

    public static int[] landxCoords = new int[8];
    public static int[] landyCoords = { 50, 100, 150, 170, 200, 250, 280, 300 };
    public static int platformIndex = 0;
    // public static int[] landxPos = { 50, 150, 260, 300, 430, 520, 570, 630, 660};

    // Character Positions
    public static int dogxPos = 320;
    public static int dogyPos = 240;

    // used for jumping (initial value doesn't matter)
    public static int dogOriginalyPos = 240;
    public static int catOriginalyPos = 240;

    public static int catxPos = 360;
    public static int catyPos = 240;

    // arrow positions
    public static int menuArrowxPos = 255;
    public static int menuArrowyPos = 80;

    public static int selectArrowxPos = 230;
    public static int selectArrowyPos = 80;

    public static int arrowState = 1;

    // twoPlayers
    public static boolean twoPlayers;

    // key listener
    public static boolean upPressed = false;
    public static boolean leftPressed = false;
    public static boolean rightPressed = false;

    public static boolean wPressed = false;
    public static boolean aPressed = false;
    public static boolean dPressed = false;

    public static boolean dogJumping = false;
    public static boolean catJumping = false;

    public static boolean dogLookLeft = false;
    public static boolean catLookLeft = false;

    public static int dogGravity = 3;
    public static int dogVelocity = -27;
    public static int catGravity = 3;
    public static int catVelocity = -27;

    // Buffered Images
    public static BufferedImage[] stateImages = new BufferedImage[7];

    public static BufferedImage land;

    public static BufferedImage[] dogIdle = new BufferedImage[4];
    public static BufferedImage[] dogIdlel = new BufferedImage[4];
    public static BufferedImage[] dogRun = new BufferedImage[6];
    public static BufferedImage[] dogRunl = new BufferedImage[6];

    public static BufferedImage[] catIdle = new BufferedImage[4];
    public static BufferedImage[] catIdlel = new BufferedImage[4];
    public static BufferedImage[] catRun = new BufferedImage[6];
    public static BufferedImage[] catRunl = new BufferedImage[6];

    public static BufferedImage arrow;

    // platform(s) generation
    public Main() {
        // JPanel default settings
        setPreferredSize(new Dimension(700, 360));

        // Add KeyListener
        this.setFocusable(true);
        addKeyListener(this);

        // Timer
        Thread t = new Thread(this);
        t.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen
        g.drawImage(stateImages[state], 0, 0, null);

        if (state == 0 || state == 3) {

            // VARIABLE RESET (for pause -> menu -> game)
            dogxPos = 320;
            dogyPos = catyPos = 240;
            catxPos = 360;
            bgyPos = -700;
            bgMoveUpFactor = 0.00000000001;

            rightPressed = leftPressed = dogJumping = wPressed = aPressed = dPressed = catJumping = twoPlayers = false;
            dogGravity = catVelocity = 3;
            dogVelocity = catVelocity = -27;

            for (int i = 0; i < 8; i++) {
                landx = rand.nextInt(600);
                landy = rand.nextInt(260);

                landxCoords[i] = landx;
                landyCoords[i] = landy;

                numOfPlatforms += 1;
            }

            frameController++;
            g.drawImage(dogIdle[idleSpriteNo], 320, 240, null);

            if (frameController == 4) {
                idleSpriteNo = (idleSpriteNo + 1) % 4;
                frameController = 0;
            }
            if (state == 0)
                g.drawImage(arrow, menuArrowxPos, menuArrowyPos, null);
            else
                g.drawImage(arrow, selectArrowxPos, selectArrowyPos, null);
        }

        if (state == 4) {

            g.drawImage(stateImages[state], 0, bgyPos, null); // sky

            if (bgyPos < 0) { // used to accumulate y pos of land and bg
                bgyPos += bgMoveUpFactor;
                bgMoveUpFactor += 0.00001;
                dogyPos += 1;
                catyPos += 1;

                dogOriginalyPos += 1; // for jumping back on platform
                catOriginalyPos += 1;

                for (int i = 0; i < 8; i++) {
                    landyCoords[i] += 1;
                }
            } else
                bgyPos = -245;

            frameController++;

            if (frameController == 4) { // for sprites
                idleSpriteNo = (idleSpriteNo + 1) % 4;
                runSpriteNo = (runSpriteNo + 1) % 6;
                frameController = 0;
            }

            for (int i = 0; i < 8; i++) {
                if (landyCoords[i] > 360) { // generate new blocks if old one disappears
                    landx = rand.nextInt(600);

                    landxCoords[i] = landx;
                    landyCoords[i] = -10;
                }

                g.drawImage(land, landxCoords[i], landyCoords[i], null); // print platforms
            }

            if (!twoPlayers) {
                // for movement
                if (dogJumping || rightPressed || leftPressed) {
                    if (rightPressed) {
                        dogLookLeft = false;
                        dogxPos += 8;
                    }
                    if (leftPressed) {
                        dogxPos -= 8;
                        dogLookLeft = true;
                    }

                    if (dogLookLeft == true) {
                        if (dogJumping) {
                            g.drawImage(dogIdlel[idleSpriteNo], dogxPos, dogyPos, null);
                            dogyPos += dogVelocity;
                            dogVelocity += dogGravity;

                            // do collision here
                            // if the dog is coming down (dogVelocity>0) and the dogyPos+70 is greater than
                            // the
                            // ypos of a platform, then you position the dog on the platform and set
                            // dogJumping to false
                            if (dogVelocity >= 0) { // if jumping down
                                for (int i = 0; i < landyCoords.length; i++) {
                                    if (dogxPos >= landxCoords[i] - 60 && dogxPos <= landxCoords[i] + 90) {
                                        if (dogyPos + 70 > landyCoords[i]) {
                                            dogOriginalyPos = landyCoords[i] - 70;

                                            platformIndex = i;
                                        }
                                    }
                                }
                            } // known bug: when the bottom platform is disappearing and another one is
                              // spawned on top, the dog gets transfered to the one above

                            if (dogyPos > dogOriginalyPos) {
                                dogJumping = false;
                                dogyPos = dogOriginalyPos; // depends on y value of platform dog stands on

                                dogVelocity = -27;
                                dogGravity = 3;
                            }

                        } else {
                            g.drawImage(dogRunl[runSpriteNo], dogxPos, dogyPos, null);
                        }
                    } else if (!dogLookLeft) {
                        if (dogJumping) {
                            g.drawImage(dogIdle[idleSpriteNo], dogxPos, dogyPos, null);
                            dogyPos += dogVelocity;
                            dogVelocity += dogGravity;

                            // COLLISION
                            if (dogVelocity >= 0) { // if jumping down
                                for (int i = 0; i < landyCoords.length; i++) {
                                    if (dogxPos >= landxCoords[i] - 60 && dogxPos <= landxCoords[i] + 90) {
                                        if (dogyPos + 70 > landyCoords[i]) {
                                            dogOriginalyPos = landyCoords[i] - 70;

                                            platformIndex = i;
                                        }
                                    }
                                }
                            }

                            if (dogyPos > dogOriginalyPos) {
                                dogJumping = false;
                                dogyPos = dogOriginalyPos; // depends on y value of platform dog stands on

                                dogVelocity = -27;
                                dogGravity = 3;

                            }
                        } else {
                            g.drawImage(dogRun[runSpriteNo], dogxPos, dogyPos, null);
                        }
                    }

                }

                // if nothing is pressed
                else {
                    if (dogLookLeft == true)
                        g.drawImage(dogIdlel[idleSpriteNo], dogxPos, dogyPos, null);
                    else if (dogLookLeft == false)
                        g.drawImage(dogIdle[idleSpriteNo], dogxPos, dogyPos, null);
                }

                if (dogyPos >= 360 - 38) {

                    // *********** VARIABLE RESET HERE IS NOT NEEDED ANYMORE
                    // VARIABLE RESET
                    dogxPos = 320;
                    dogyPos = catyPos = 240;
                    catxPos = 360;
                    bgyPos = -700;
                    bgMoveUpFactor = 0.00000000001;

                    rightPressed = leftPressed = dogJumping = wPressed = aPressed = dPressed = catJumping = false;
                    dogGravity = catVelocity = 3;
                    dogVelocity = catVelocity = -27;

                    state = 6;
                }
                // put other floating islands/blocks here
            }

            // two players
            else {
                // for movement
                if (dogJumping || rightPressed || leftPressed) {
                    if (rightPressed) {
                        dogLookLeft = false;
                        dogxPos += 8;
                    }
                    if (leftPressed) {
                        dogxPos -= 8;
                        dogLookLeft = true;
                    }

                    if (dogLookLeft == true) {
                        if (dogJumping) {
                            g.drawImage(dogIdlel[idleSpriteNo], dogxPos, dogyPos, null);
                            dogyPos += dogVelocity;
                            dogVelocity += dogGravity;
                            if (dogyPos > dogOriginalyPos) {
                                dogJumping = false;
                                dogyPos = dogOriginalyPos; // depends on y value of platform dog stands on

                                dogVelocity = -27;
                                dogGravity = 3;
                            }
                        } else {
                            g.drawImage(dogRunl[runSpriteNo], dogxPos, dogyPos, null);
                        }
                    } else if (!dogLookLeft) {
                        if (dogJumping) {
                            g.drawImage(dogIdle[idleSpriteNo], dogxPos, dogyPos, null);
                            dogyPos += dogVelocity;
                            dogVelocity += dogGravity;
                            if (dogyPos > dogOriginalyPos) {
                                dogJumping = false;
                                dogyPos = dogOriginalyPos; // depends on y value of platform dog stands on

                                dogVelocity = -27;
                                dogGravity = 3;

                            }
                        } else {
                            g.drawImage(dogRun[runSpriteNo], dogxPos, dogyPos, null);
                        }
                    }
                } else {
                    if (dogLookLeft)
                        g.drawImage(dogIdlel[idleSpriteNo], dogxPos, dogyPos, null);
                    else if (!dogLookLeft)
                        g.drawImage(dogIdle[idleSpriteNo], dogxPos, dogyPos, null);
                }

                if (catJumping || dPressed || aPressed) {
                    if (dPressed) {
                        catLookLeft = false;
                        catxPos += 8;
                    }
                    if (aPressed) {
                        catLookLeft = true;
                        catxPos -= 8;
                    }

                    if (catLookLeft) {
                        if (catJumping) {
                            g.drawImage(catIdlel[idleSpriteNo], catxPos, catyPos, null);
                            catyPos += catVelocity;
                            catVelocity += catGravity;
                            if (catyPos > catOriginalyPos) {
                                catJumping = false;
                                catyPos = catOriginalyPos; // depends on y value of platform cat stands on

                                catVelocity = -27;
                                catGravity = 3;
                            }
                        } else {
                            g.drawImage(catRunl[runSpriteNo], catxPos, catyPos, null);
                        }
                    } else if (!catLookLeft) {
                        if (catJumping) {
                            g.drawImage(catIdle[idleSpriteNo], catxPos, catyPos, null);
                            catyPos += catVelocity;
                            catVelocity += catGravity;
                            if (catyPos > catOriginalyPos) {
                                catJumping = false;
                                catyPos = catOriginalyPos; // depends on y value of platform cat stands on

                                catVelocity = -27;
                                catGravity = 3;
                            }
                        } else {
                            g.drawImage(catRun[runSpriteNo], catxPos, catyPos, null);
                        }
                    }
                } else {
                    if (catLookLeft)
                        g.drawImage(catIdlel[idleSpriteNo], catxPos, catyPos, null);
                    else if (!catLookLeft)
                        g.drawImage(catIdle[idleSpriteNo], catxPos, catyPos, null);
                }

                if (dogyPos >= 360 - 38 || catyPos >= 360 - 38) {

                    // *********** VARIABLE RESET HERE IS NOT NEEDED ANYMORE
                    // VARIABLE RESET
                    dogxPos = 320;
                    dogyPos = catyPos = 240;
                    catxPos = 360;
                    bgyPos = -700;
                    bgMoveUpFactor = 0.00000000001;

                    rightPressed = leftPressed = dogJumping = wPressed = aPressed = dPressed = catJumping = false;
                    dogGravity = catVelocity = 3;
                    dogVelocity = catVelocity = -27;

                    state = 6;
                }

                // put other floating islands/blocks here
            }
        }

        if (state == 6) {
            // VARIABLE RESET
            dogxPos = 320;
            dogyPos = catyPos = 240;
            catxPos = 360;
            bgyPos = -700;
            bgMoveUpFactor = 0.00000000001;

            rightPressed = leftPressed = dogJumping = wPressed = aPressed = dPressed = catJumping = false;
            dogGravity = catVelocity = 3;
            dogVelocity = catVelocity = -27;

            for (int i = 0; i < 8; i++) {
                landx = rand.nextInt(600);
                landy = rand.nextInt(260);

                landxCoords[i] = landx;
                landyCoords[i] = landy;

                numOfPlatforms += 1;
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
                    dogxPos = 280;
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

            // for movement
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (!dogJumping) {
                    dogOriginalyPos = dogyPos;
                    dogJumping = true;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                leftPressed = true;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                rightPressed = true;

            if (twoPlayers) {
                if (e.getKeyChar() == 'w') {
                    if (!catJumping) {
                        catOriginalyPos = catyPos;
                        catJumping = true;
                    }
                }
                if (e.getKeyChar() == 'a')
                    aPressed = true;
                if (e.getKeyChar() == 'd')
                    dPressed = true;
            }
        }

        else if (state == 5) { // pause
            if (e.getKeyChar() == 'r') {
                // VARIABLE RESET
                dogxPos = 320;
                dogyPos = catyPos = 240;
                catxPos = 360;
                bgyPos = -700;
                bgMoveUpFactor = 0.00000000001;

                rightPressed = leftPressed = dogJumping = wPressed = aPressed = dPressed = catJumping = false;
                dogGravity = catVelocity = 3;
                dogVelocity = catVelocity = -27;

                for (int i = 0; i < 8; i++) {
                    landx = rand.nextInt(600);
                    landy = rand.nextInt(260);

                    landxCoords[i] = landx;
                    landyCoords[i] = landy;

                    numOfPlatforms += 1;
                }

                state = 4;
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

    public void keyReleased(KeyEvent e) {
        if (state == 4) {
            if (e.getKeyCode() == KeyEvent.VK_UP)
                upPressed = false;
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                leftPressed = false;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                rightPressed = false;

            if (twoPlayers) {
                if (e.getKeyChar() == 'w')
                    wPressed = false;
                if (e.getKeyChar() == 'a')
                    aPressed = false;
                if (e.getKeyChar() == 'd')
                    dPressed = false;
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
            land = ImageIO.read(new File("culminating/assets/land.png"));

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

            dogIdlel[0] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogIdle0l.png"));
            dogIdlel[1] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogIdle1l.png"));
            dogIdlel[2] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogIdle2l.png"));
            dogIdlel[3] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogIdle3l.png"));

            dogRunl[0] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogRun0l.png"));
            dogRunl[1] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogRun1l.png"));
            dogRunl[2] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogRun2l.png"));
            dogRunl[3] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogRun3l.png"));
            dogRunl[4] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogRun4l.png"));
            dogRunl[5] = ImageIO.read(new File("culminating/assets/sprites/dog/flipped/dogRun5l.png"));

            // cat sprites
            catIdle[0] = ImageIO.read(new File("culminating/assets/sprites/cat/catIdle0.png"));
            catIdle[1] = ImageIO.read(new File("culminating/assets/sprites/cat/catIdle1.png"));
            catIdle[2] = ImageIO.read(new File("culminating/assets/sprites/cat/catIdle2.png"));
            catIdle[3] = ImageIO.read(new File("culminating/assets/sprites/cat/catIdle3.png"));

            catRun[0] = ImageIO.read(new File("culminating/assets/sprites/cat/catRun0.png"));
            catRun[1] = ImageIO.read(new File("culminating/assets/sprites/cat/catRun1.png"));
            catRun[2] = ImageIO.read(new File("culminating/assets/sprites/cat/catRun2.png"));
            catRun[3] = ImageIO.read(new File("culminating/assets/sprites/cat/catRun3.png"));
            catRun[4] = ImageIO.read(new File("culminating/assets/sprites/cat/catRun4.png"));
            catRun[5] = ImageIO.read(new File("culminating/assets/sprites/cat/catRun5.png"));

            catIdlel[0] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catIdle0l.png"));
            catIdlel[1] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catIdle1l.png"));
            catIdlel[2] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catIdle2l.png"));
            catIdlel[3] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catIdle3l.png"));

            catRunl[0] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catRun0l.png"));
            catRunl[1] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catRun1l.png"));
            catRunl[2] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catRun2l.png"));
            catRunl[3] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catRun3l.png"));
            catRunl[4] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catRun4l.png"));
            catRunl[5] = ImageIO.read(new File("culminating/assets/sprites/cat/flipped/catRun5l.png"));

        } catch (Exception e) {
            System.out.println("Something wrong with the image!");
        }

        // platform(s) generation (initial)
        for (int i = 0; i < 8; i++) {
            landx = rand.nextInt(600);
            landxCoords[i] = landx;

            numOfPlatforms += 1;
        }

        JFrame frame = new JFrame("Flood Escape");
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
    public void keyTyped(KeyEvent e) {
    }
}