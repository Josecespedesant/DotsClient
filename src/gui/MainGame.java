package gui;

import matrix.Matrix;
import people.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * GUI where the actual game interaction takes place
 *
 * @author David Azofeifa H.
 */
public class MainGame {
    private JPanel game;
    private JFrame frame;

    public MainGame(Matrix matrix) {
        this.game = new gameCanvas(matrix);

    }

    public void selfBuild() {
        this.frame = new JFrame("Dots");
        this.frame.setContentPane(this.game);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(640, 480));
        this.frame.setMaximumSize(new Dimension(640, 480));
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void selfDestroy() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }


    class gameCanvas extends JPanel {
        final private int imageSize = 15;
        final private int rowSize = 9;
        final private int initialX = 150;
        final private int initialY = 140;
        final private int dotPadding = 75;
        private Matrix matrix;

        Point[][] dots = new Point[rowSize][rowSize];
        int xPos = initialX;
        int yPos = initialY;

        public gameCanvas(Matrix matrix) {
            this.matrix = matrix;
            this.setLayout(null);
            this.setMinimumSize(new Dimension(640, 480));
            this.setMaximumSize(new Dimension(640, 480));
            this.setPreferredSize(this.getMaximumSize());
            this.setVisible(true);
            this.setBackground(Color.decode("#2F343F"));
            
        }

        /**
         * Paints all dots into the screen ignoring the parts in the matrix used for defining lines
         * @param g
         */
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            setVisible(true);
            boolean changeRowValues = true;
            BufferedImage image = null;
            g.setColor(Color.white);
            try {
                image = ImageIO.read(new File("assets//dot.png"));
                for (int i = 0; i < this.rowSize; i++) {
                    if (changeRowValues) {
                        boolean changeToOne = true;
                        for (int j = 0; j < this.rowSize; j++) {
                            if (changeToOne) {
                                dots[i][j] = new Point(xPos, yPos);
                                g.drawImage(image, dots[i][j].x, dots[i][j].y, imageSize, imageSize, null);
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
            } catch (IOException e) {
                e.printStackTrace();
            }


        /*
            public void showChanges(Graphics g) {
            for (int i = 0; i < this.matrix.getRows(); i++) {

            }
        }
        */
      
       /*
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
            }*/

        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(9,9,0);
        MainGame mainGame = new MainGame(matrix);
        mainGame.selfBuild();
        //((gameCanvas)mainGame.getGamePanel()).drawLine(0,1, 1);
    }
}
