package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGame {
    class gameCanvas extends JPanel {
        final private int imageSize = 15;
        final private int rowSize = 5;
        final private int initialX = 150;
        final private int initialY = 140;
        final private int dotPadding = 75;

        public gameCanvas() {
            this.setLayout(null);
            this.setMinimumSize(new Dimension(640, 480));
            this.setMaximumSize(new Dimension(640, 480));
            this.setVisible(true);
            this.setBackground(Color.decode("#2F343F"));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            // define the position
            Point[][] dots = new Point[rowSize][rowSize];
            int xPos = initialX;
            int yPos = initialY;
            for (int i = 0; i < this.rowSize; i++) {
                for (int j = 0; j < this.rowSize; j++) {
                    dots[i][j] = new Point(xPos, yPos);
                    xPos += dotPadding;
                }
                yPos += dotPadding;
                xPos = initialX;
            }

            g.setColor(Color.white);

            // draw a line (there is no drawPoint..)
            try {
                BufferedImage image = ImageIO.read(new File("assets//dot.png"));
                for (int i = 0; i < this.rowSize; i++) {
                    for (int j = 0; j < this.rowSize; j++) {
                        g.drawImage(image, dots[i][j].x, dots[i][j].y, imageSize,imageSize, null);
                        System.out.println("Dot " +i+ "," + j + "=  " +dots[i][j].x + ", " + dots[i][j].y);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    private JPanel game;
    private JFrame frame;


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
    }
}
