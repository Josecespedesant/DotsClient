package gui;

import linkedlist.LinkedList;
import linkedlist.Node;
import matrix.Matrix;
import people.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * GUI where the actual gamePanel interaction takes place
 *
 * @author David Azofeifa H.
 */
public class MainGame {
    private JPanel gamePanel;
    private JFrame frame;
    private Matrix matrix;

    private Player player1;
    private Player player2;
    private int playerNum;

    public MainGame(Matrix matrix, Player player1, Player player2, int playerNum) throws IOException {
        this.player1 = player1;
        this.player2 = player2;
        this.playerNum = playerNum;
        this.gamePanel = new gameCanvas(matrix, player1.getName(), player2.getName());
        this.matrix = matrix;
        this.frame = new JFrame("Dots");
        this.frame.setContentPane(new MainMenuForm().getPanel());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);

        this.gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
            }
        });
    }

    public void selfBuild() {
        this.frame = new JFrame("Dots");
        this.frame.setContentPane(this.gamePanel);
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

    public Matrix getMatrix() {
        return this.matrix;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    class gameCanvas extends JPanel {
        final private int imageSize = 15;
        final private int rowSize = 9;
        final private int initialX = 150;
        final private int initialY = 140;
        final private int dotPadding = 75;

        private String player1Name;
        private String player2Name;
        private JLabel player1NameLabel;
        private JLabel player2NameLabel;
        private BufferedImage image;
        private Matrix matrix;

        private Point[][] dots = new Point[rowSize][rowSize];
        int xPos = initialX;
        int yPos = initialY;

        private LinkedList lines;

        public gameCanvas(Matrix matrix, String player1Name, String player2Name) throws IOException {
            this.lines = new LinkedList();

            this.player1NameLabel = new JLabel(this.player1Name);
            this.player1NameLabel.setSize(0, 0);
            this.player1NameLabel.setLocation(30, 30);
            this.player1NameLabel.setForeground(Color.white);
            this.player1NameLabel.setVisible(true);
            this.add(player1NameLabel);
            this.player2NameLabel = new JLabel(this.player2Name);
            this.player2NameLabel.setSize(50, 50);
            this.player2NameLabel.setLocation(400, 30);
            this.add(player2NameLabel);

            this.matrix = matrix;
            this.setBackground(Color.decode("#2F343F"));
            this.setLayout(null);
            this.setMinimumSize(new Dimension(640, 480));
            this.setMaximumSize(new Dimension(640, 480));
            this.setPreferredSize(this.getMaximumSize());
            this.setVisible(true);
            this.image = ImageIO.read(new File("assets//dot.png"));
            this.fillPoints();


        }

        public void fillPoints() {
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
                } else {
                    changeRowValues = true;
                }
            }
        }

        /**
         * Paints all dots to the screen ignoring the parts of the matrix used for defining lines
         * @param g
         */
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            setVisible(true);
            g.setColor(Color.white);

            try {
                this.image = ImageIO.read(new File("assets//dot.png"));
                boolean changeRowValues = true;
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
                    } else {
                        changeRowValues = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            addLines();
            Node temp = lines.getHead();
            while (temp != null) {
                LinkedList range = (LinkedList) temp.getData();
                Point from = (Point) range.getHead().getData();
                Point to = (Point) range.getHead().getNext().getData();
                int value = (int) range.getHead().getNext().getNext().getData();
                if (value == 1) {
                    g.setColor(Color.white);
                }
                else if (value == 2) {
                    g.setColor(Color.green);
                }
                else if (value == 3) {
                    g.setColor(Color.MAGENTA);
                }
                g.drawLine(from.x, from.y, to.x, to.y);
                temp = temp.getNext();
            }
            g.setColor(Color.white);
            repaint();
        }

        @Override
        public void update(Graphics g) {
            paint(g);
        }

        public void addLines() {
            boolean changeRowValues = true;
            for (int i = 0; i < this.matrix.getRows(); i++) {
                boolean changeToOne = false;
                for (int j = 0; j < this.matrix.getColumns(); j++) {
                    if (changeToOne || !changeRowValues) {
                        if (this.matrix.viewValue(j, i) != 0) {
                            if (i%2==0 && j%2!=0) {
                                Point from = new Point(dots[i][j-1].x+imageSize,
                                        dots[i][j-1].y+imageSize/2);
                                Point to = new Point(dots[i][j+1].x+imageSize,
                                        dots[i][j+1].y+imageSize/2);
                                LinkedList temp = new LinkedList();
                                temp.append(from);
                                temp.append(to);
                                temp.append(this.matrix.viewValue(j,i));
                                lines.append(temp);
                            }
                            else if (i%2!=0 && j%2!=0) {
                                Point from = new Point(dots[i-1][j-1].x+imageSize/2,
                                        dots[i-1][j-1].y+imageSize/2);
                                Point to = new Point(dots[i+1][j+1].x+imageSize/2,
                                        dots[i+1][j+1].y+imageSize/2);
                                LinkedList temp = new LinkedList();
                                temp.append(from);
                                temp.append(to);
                                temp.append(this.matrix.viewValue(j,i));
                                lines.append(temp);
                            }
                            else if(i%2!=0 && j%2==0) {
                                Point from = new Point(dots[i-1][j].x+imageSize/2,
                                        dots[i-1][j].y+imageSize/2);
                                Point to = new Point(dots[i+1][j].x+imageSize/2,
                                        dots[i+1][j].y+imageSize/2);
                                LinkedList temp = new LinkedList();
                                temp.append(from);
                                temp.append(to);
                                temp.append(this.matrix.viewValue(j,i));
                                lines.append(temp);
                            }
                        }
                        changeToOne = false;
                    } else
                        changeToOne = true;
                }
                changeRowValues = false;
            }
        }
    }
}
