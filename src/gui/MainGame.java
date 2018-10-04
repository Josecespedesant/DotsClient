package gui;

import people.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * GUI where the actual game interaction takes place
 *
 * @author David Azofeifa H.
 */
public class MainGame {
    class gameCanvas extends JPanel {
        final private int imageSize = 15;
        final private int rowSize = 9;
        final private int initialX = 150;
        final private int initialY = 140;
        final private int dotPadding = 75;

        Graphics graphics;

        Point[][] dots = new Point[rowSize][rowSize];
        int xPos = initialX;
        int yPos = initialY;

        public gameCanvas() {
            this.setLayout(null);
            this.setMinimumSize(new Dimension(640, 480));
            this.setMaximumSize(new Dimension(640, 480));
            this.setVisible(true);
            this.setBackground(Color.decode("#2F343F"));
        }

        /**
         * Paints all dots into the screen ignoring the parts in the matrix used for defining lines
         * @param g
         */
        @Override
        public void paint(Graphics g) {
            this.graphics = g;
            super.paint(g);
            // define the position


            boolean changeRowValues = true;
            for (int i = 0; i < this.rowSize; i++) {
                if (changeRowValues) {
                    boolean changeToOne = true;
                    for (int j = 0; j < this.rowSize; j++) {
                        if (changeToOne) {
                            dots[i][j] = new Point(xPos, yPos);
                            xPos += dotPadding;
                            changeToOne = false;
                        } else
                            changeToOne = true;
                    }
                        changeRowValues = false;
                    yPos += dotPadding;
                    xPos = initialX;
                }
                else {
                    changeRowValues = true;
                }

            }

            g.setColor(Color.white);

            // draw a line (there is no drawPoint..)
            try {
                BufferedImage image = ImageIO.read(new File("assets//dot.png"));
                changeRowValues = true;
                for (int i = 0; i < this.rowSize; i++) {
                    if (changeRowValues) {
                        boolean changeToOne = true;
                        for (int j = 0; j < this.rowSize; j++) {
                            if (changeToOne) {
                                g.drawImage(image, dots[i][j].x, dots[i][j].y, imageSize, imageSize, null);
                                changeToOne = false;
                            } else
                                changeToOne = true;
                        }
                        changeRowValues = false;
                    }
                    else {
                        changeRowValues = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void drawLine(int i, int j, int player) {

            if (i%2==0 && j%2!=0) {
                if (player == 1) {
                    this.graphics.setColor(Color.green);
                }
                else {
                    this.graphics.setColor(Color.orange);
                }
                this.graphics.drawLine(this.dots[i][j-1].x, this.dots[1][j-1].y,
                        this.dots[1][j+1].x, this.dots[i][j+1].y);
            }

        }


    }
    private JPanel game;
    private JFrame frame;

    public JPanel getGamePanel() {
        return this.game;
    }

    public MainGame() {
        this.game = new gameCanvas();
    }

    public void selfBuild() {
        this.frame = new JFrame("Dots");
        this.frame.setContentPane(new MainGame().game);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(640, 480));
        this.frame.setMaximumSize(new Dimension(640, 480));
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void selfDestroy() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }


    public static void main(String[] args) {
        MainGame mainGame = new MainGame();
        mainGame.selfBuild();
       ((gameCanvas)mainGame.getGamePanel()).drawLine(0,1, 1);
    }
}
