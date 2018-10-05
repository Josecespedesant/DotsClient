package gui;

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
    class gameCanvas extends JPanel {
        final private int imageSize = 15;
        final private int rowSize = 9;
        final private int initialX = 150;
        final private int initialY = 140;
        final private int dotPadding = 75;
        Graphics graphics;
        
        int[] A1, B1, C1, D1, A2, B2, C2, D2, A3, B3, C3, D3, A4, B4, C4, D4, A5, B5, C5, D5;
    	// Vertical Lines
    	int[] E1, F1, G1, H1, E2, F2, G2, H2, E3, F3, G3, H3, E4, F4, G4, H4, E5, F5, G5, H5;
    	// Diagonal Lines (Diagonal N del Cuadro M, si es D1 = / si es D2 = \)
    	int[] D1C1, D2C1, D1C2, D2C2,D1C3, D2C3,D1C4, D2C4,D1C5, D2C5,D1C6,
    	D2C6,D1C7, D2C7,D1C8, D2C8,D1C9, D2C9,D1C10, D2C10,D1C11, D2C11,D1C12,
    	D2C12,D1C13, D2C13,D1C14, D2C14,D1C15, D2C15,D1C16, D2C16;
    	
    	//Todos los posibles cuadrados
    	int[][] cuad1,cuad2,cuad3,cuad4,cuad5,cuad6,cuad7,cuad8,cuad9,cuad10,cuad11,cuad12,cuad13,cuad14,cuad15,cuad16;
    	// All possible triangles
    	int[][] tri1,tri2,tri3,tri4,tri5,tri6,tri7,tri8,tri9,tri10,tri11,tri12,tri13,tri14,tri15,tri16,tri17,tri18,tri19,tri20,tri21,tri22,tri23,tri24,tri25,tri26,tri27,tri28,tri29,tri30,tri31,tri32;
    	int[][] tri33,tri34,tri35,tri36,tri37,tri38,tri39,tri40,tri41,tri42,tri43,tri44,tri45,tri46,tri47,tri48,tri49,tri50,tri51,tri52,tri53,tri54,tri55,tri56,tri57,tri58,tri59,tri60,tri61,tri62,tri63,tri64;
    	// Where everything is saved
    	int[][][] todosLosTriang;
    	int[][][] todasLasCajas;
        

        Point[][] dots = new Point[rowSize][rowSize];
        int xPos = initialX;
        int yPos = initialY;

        public gameCanvas() {
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
            this.graphics = g;
            super.paint(g);
            
            setVisible(true);
        //    drawLines(g);
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
            drawLine(g);
        }
        
        public void drawLine(Graphics g) {
        	Graphics2D g2d = (Graphics2D) g;
        	createLines();
        	organizeSquares();
        	organizeTriangles();
        	for(int i = 0; i< todasLasCajas.length; i++) {
        		for(int j = 0; j<todasLasCajas[i].length; j++) {
        			if(todasLasCajas[i][j][0] == 0) {
        	        	g2d.setColor(getBackground());
        			}
        			else if(todasLasCajas[i][j][0] == 1){
        				g2d.setColor(Color.WHITE);
        			}
        			g2d.drawLine(todasLasCajas[i][j][1], todasLasCajas[i][j][3], todasLasCajas[i][j][2], todasLasCajas[i][j][4]);
        		}
        	}
        	
        	for(int i = 0; i< todosLosTriang.length; i++) {
        		for(int j = 0; j<todosLosTriang[i].length; j++) {
        			if(todosLosTriang[i][j][0] == 0) {
        	        	g2d.setColor(getBackground());
        			}
        			else if(todosLosTriang[i][j][0] == 1){
        				g2d.setColor(Color.WHITE);
        			}
        			g2d.drawLine(todosLosTriang[i][j][1], todosLosTriang[i][j][3], todosLosTriang[i][j][2], todosLosTriang[i][j][4]);
        		}
        	}
        	
        		
        		//g2d.drawLine(A1[1], A1[3], A1[2], A1[4]);
        	
        }
        
        public void organizeTriangles() {
    		//1st row
    		this.tri1 = new int [][] {this.A1, this.E1, this.D1C1};
    		this.tri2 = new int [][] {this.A1, this.E2, this.D2C1};
    		this.tri3 = new int [][] {this.A2, this.E1, this.D2C1};
    		this.tri4 = new int [][] {this.A2, this.E2, this.D1C1};

    		this.tri5 = new int [][] {this.B1, this.E2, this.D1C2};
    		this.tri6 = new int [][] {this.B1, this.E3, this.D2C2};
    		this.tri7 = new int [][] {this.B2, this.E2, this.D2C2};
    		this.tri8 = new int [][] {this.B2, this.E3, this.D1C2};

    		this.tri9 = new int [][] {this.C1, this.E3, this.D1C3};
    		this.tri10 = new int [][] {this.C1, this.E4, this.D2C3};
    		this.tri11 = new int [][] {this.C2, this.E3, this.D2C3};
    		this.tri12 = new int [][] {this.C2, this.E4, this.D1C3};

    		this.tri13 = new int [][] {this.D1, this.E4, this.D1C4};
    		this.tri14 = new int [][] {this.D1, this.E5, this.D2C4};
    		this.tri15 = new int [][] {this.D2, this.E4, this.D2C4};
    		this.tri16 = new int [][] {this.D2, this.E5, this.D1C4};

    		//2nd row
    		this.tri17 = new int [][] {this.A2, this.F1, this.D1C5};
    		this.tri18 = new int [][] {this.A2, this.F2, this.D2C5};
    		this.tri19 = new int [][] {this.A3, this.F1, this.D2C5};
    		this.tri20 = new int [][] {this.A3, this.F2, this.D1C5};

    		this.tri21 = new int [][] {this.B2, this.F2, this.D1C6};
    		this.tri22 = new int [][] {this.B2, this.F3, this.D2C6};
    		this.tri23 = new int [][] {this.B3, this.F2, this.D2C6};
    		this.tri24 = new int [][] {this.B3, this.F3, this.D1C6};

    		this.tri25 = new int [][] {this.C2, this.F3, this.D1C7};
    		this.tri26 = new int [][] {this.C2, this.F4, this.D2C7};
    		this.tri27 = new int [][] {this.C3, this.F3, this.D2C7};
    		this.tri28 = new int [][] {this.C3, this.F4, this.D1C7};

    		this.tri29 = new int [][] {this.D2, this.F4, this.D1C8};
    		this.tri30 = new int [][] {this.D2, this.F5, this.D2C8};
    		this.tri31 = new int [][] {this.D3, this.F4, this.D2C8};
    		this.tri32 = new int [][] {this.D3, this.F5, this.D1C8};

    		//3rd row
    		this.tri33 = new int [][] {this.A3, this.G1, this.D1C9};
    		this.tri34 = new int [][] {this.A3, this.G2, this.D2C9};
    		this.tri35 = new int [][] {this.A4, this.G1, this.D2C9};
    		this.tri36 = new int [][] {this.A4, this.G2, this.D1C9};

    		this.tri37 = new int [][] {this.B3, this.G2, this.D1C10};
    		this.tri38 = new int [][] {this.B3, this.G3, this.D2C10};
    		this.tri39 = new int [][] {this.B4, this.G2, this.D2C10};
    		this.tri40 = new int [][] {this.B4, this.G3, this.D1C10};

    		this.tri41 = new int [][] {this.C3, this.G3, this.D1C11};
    		this.tri42 = new int [][] {this.C3, this.G4, this.D2C11};
    		this.tri43 = new int [][] {this.C4, this.G3, this.D2C11};
    		this.tri44 = new int [][] {this.C4, this.G4, this.D1C11};

    		this.tri45 = new int [][] {this.D3, this.G4, this.D1C12};
    		this.tri46 = new int [][] {this.D3, this.G5, this.D2C12};
    		this.tri47 = new int [][] {this.D4, this.G4, this.D2C12};
    		this.tri48 = new int [][] {this.D4, this.G5, this.D1C12};

    		//4th row
    		this.tri49 = new int [][] {this.A4, this.H1, this.D1C13};
    		this.tri50 = new int [][] {this.A4, this.H2, this.D2C13};
    		this.tri51 = new int [][] {this.A5, this.H1, this.D2C13};
    		this.tri52 = new int [][] {this.A5, this.H2, this.D1C13};

    		this.tri53 = new int [][] {this.B4, this.H2, this.D1C14};
    		this.tri54 = new int [][] {this.B4, this.H3, this.D2C14};
    		this.tri55 = new int [][] {this.B5, this.H2, this.D2C14};
    		this.tri56 = new int [][] {this.B5, this.H3, this.D1C14};

    		this.tri57 = new int [][] {this.C4, this.H3, this.D1C15};
    		this.tri58 = new int [][] {this.C4, this.H4, this.D2C15};
    		this.tri59 = new int [][] {this.C5, this.H3, this.D2C15};
    		this.tri60 = new int [][] {this.C5, this.H4, this.D1C15};

    		this.tri61 = new int [][] {this.D4, this.H4, this.D1C16};
    		this.tri62 = new int [][] {this.D4, this.H5, this.D2C16};
    		this.tri63 = new int [][] {this.D5, this.H4, this.D2C16};
    		this.tri64 = new int [][] {this.D5, this.H5, this.D1C16};

    		//All the possible triangles
    		this.todosLosTriang = new int[][][] {this.tri1, this.tri2, this.tri3, this.tri4, this.tri5, 
    			this.tri6, this.tri7, this.tri8, this.tri9, this.tri10, this.tri11, this.tri12, this.tri13, 
    			this.tri14, this.tri15, this.tri16, this.tri17, this.tri18, this.tri19, this.tri20, this.tri21, 
    			this.tri22, this.tri23, this.tri24, this.tri25, this.tri26, this.tri27, this.tri28, this.tri29, 
    			this.tri30, this.tri31, this.tri32, this.tri33, this.tri34, this.tri35, this.tri36, this.tri37, 
    			this.tri38, this.tri39, this.tri40, this.tri41, this.tri42, this.tri43, this.tri44, this.tri45, 
    			this.tri46, this.tri47, this.tri48, this.tri49, this.tri50, this.tri51, this.tri52, this.tri53, 
    			this.tri54, this.tri55, this.tri56, this.tri57, this.tri58, this.tri59, this.tri60, this.tri61, 
    			this.tri62};
    	}

    	public void organizeSquares() {
    		this.cuad1 = new int[][] {this.A1, this.E1, this. A2, this. E2};
    		this.cuad2 = new int[][] {this.B1, this.E2, this. B2, this. E3};
    		this.cuad3 = new int[][] {this.C1, this.E3, this. C2, this. E4};
    		this.cuad4 = new int[][] {this.D1, this.E4, this. D2, this. E5};

    		this.cuad5 = new int[][] {this.A2, this.F1, this. A3, this. F2};
    		this.cuad6 = new int[][] {this.B2, this.F2, this. B3, this. F3};
    		this.cuad7 = new int[][] {this.C2, this.F3, this. C3, this. F4};
    		this.cuad8 = new int[][] {this.D2, this.F4, this. D3, this. F5};

    		this.cuad9 = new int[][] {this.A3, this.G1, this. A4, this. G2};
    		this.cuad10 = new int[][] {this.B3, this.G2, this. B4, this. G3};
    		this.cuad11 = new int[][] {this.C3, this.G3, this. C4, this. G4};
    		this.cuad12 = new int[][] {this.D3, this.G4, this. D4, this. G5};

    		this.cuad13 = new int[][] {this.A4, this.H1, this. A5, this. H2};
    		this.cuad14 = new int[][] {this.B4, this.H2, this. B5, this. H3};
    		this.cuad15 = new int[][] {this.C4, this.H3, this. C5, this. H4};
    		this.cuad16 = new int[][] {this.D4, this.H4, this. D5, this. H5};

    		//Todos los posibles cuadrados.
    		this.todasLasCajas = new int[][][] {this.cuad1, this.cuad2, this.cuad3, 
    			this.cuad4, this.cuad5, this.cuad6, 
    			this.cuad7, this.cuad8, this.cuad9,
    			this.cuad10, this.cuad11, this.cuad12, 
    			this.cuad13, this.cuad14, this.cuad15, this.cuad16};

    	}
       
       public void createLines() {
   		//Horizontal
   		A1 = new int[] {0, 150, 225, 140, 140};
   		B1 = new int[] {0, 225, 300, 140, 140}; 
   		C1 = new int[] {0, 300, 375, 140, 140};
   		D1 = new int[] {0, 375, 450, 140, 140};
   		
   		A2 = new int[] {0, 150, 225, 215, 215};
   		B2 = new int[] {0, 225, 300, 215, 215}; 
   		C2 = new int[] {0, 300, 375, 215, 215};
   		D2 = new int[] {0, 375, 450, 215, 215};
   		
   		A3 = new int[] {0, 150, 225, 290, 290};
   		B3 = new int[] {0, 225, 300, 290, 290}; 
   		C3 = new int[] {0, 300, 375, 290, 290};
   		D3 = new int[] {0, 375, 450, 290, 290};
   		
   		A4 = new int[] {0, 150, 225, 365, 365};
   		B4 = new int[] {0, 225, 300, 365, 365}; 
   		C4 = new int[] {0, 300, 375, 365, 365};
   		D4 = new int[] {0, 375, 450, 365, 365};
   		
   		A5 = new int[] {0, 150, 225, 440, 440};
   		B5 = new int[] {0, 225, 300, 440, 440}; 
   		C5 = new int[] {0, 300, 375, 440, 440};
   		D5 = new int[] {0, 375, 450, 440, 440};
   		
   		//Vertical
   		
   		E1 = new int[] {0, 150, 150, 140, 215};
   		F1 = new int[] {0, 150, 150, 215, 290};
   		G1 = new int[] {0, 150, 150, 290, 365};
   		H1 = new int[] {0, 150, 150, 365, 440};
   		
   		E2 = new int[] {0, 225, 225, 140, 215};
   		F2 = new int[] {0, 225, 225, 215, 290};
   		G2 = new int[] {0, 225, 225, 290, 365};
   		H2 = new int[] {0, 225, 225, 365, 440};
   		
   		E3 = new int[] {0, 300, 300, 140, 215};
   		F3 = new int[] {0, 300, 300, 215, 290};
   		G3 = new int[] {0, 300, 300, 290, 365};
   		H3 = new int[] {0, 300, 300, 365, 440};
   		
   		E4 = new int[] {0, 375, 375, 140, 215};
   		F4 = new int[] {0, 375, 375, 215, 290};
   		G4 = new int[] {0, 375, 375, 290, 365};
   		H4 = new int[] {0, 375, 375, 365, 440};
   		
   		E5 = new int[] {0, 440, 440, 140, 215};
   		F5 = new int[] {0, 440, 440, 215, 290};
   		G5 = new int[] {0, 440, 440, 290, 365};
   		H5 = new int[] {0, 440, 440, 365, 440};
   		
   		//Diagonals
   		
   		D1C1 = new int[] {0, 150, 225, 215, 140};
   		D2C1 = new int[] {0, 225, 150, 215, 140};
   		
   		D1C2 = new int[] {0, 225, 300, 215, 140};
   		D2C2 = new int[] {0, 300, 225, 215, 140};
   		
   		D1C3 = new int[] {0, 300, 375, 215, 140};
   		D2C3 = new int[] {0, 375, 300, 215, 140};
   		
   		D1C4 = new int[] {0, 375, 450, 215, 140};
   		D2C4 = new int[] {0, 450, 375, 215, 140};
   		
   		
   		D1C5 = new int[] {0, 150, 225, 290, 215};
   		D2C5 = new int[] {0, 225, 150, 290, 215};
   		
   		D1C6 = new int[] {0, 225, 300, 290, 215};
   		D2C6 = new int[] {0, 300, 225, 290, 215};
   		
   		D1C7 = new int[] {0, 300, 375, 290, 215};
   		D2C7 = new int[] {0, 375, 300, 290, 215};
   		
   		D1C8 = new int[] {0, 375, 450, 290, 215};
   		D2C8 = new int[] {0, 450, 375, 290, 215};
   		
   		
   		D1C9 = new int[] {0, 150, 225, 365, 290};
   		D2C9 = new int[] {0, 225, 150, 365, 290};
   		
   		D1C10 = new int[] {0, 225, 300, 365, 290};
   		D2C10 = new int[] {0, 300, 225, 365, 290};
   		
   		D1C11 = new int[] {0, 300, 375, 365, 290};
   		D2C11 = new int[] {0, 375, 300, 365, 290};
   		
   		D1C12 = new int[] {0, 375, 450, 365, 290};
   		D2C12 = new int[] {0, 450, 375, 365, 290};
   		
   		
   		D1C13 = new int[] {0, 150, 225, 440, 365};
   		D2C13 = new int[] {0, 225, 150, 440, 365};
   		
   		D1C14 = new int[] {0, 225, 300, 440, 365};
   		D2C14 = new int[] {0, 300, 225, 440, 365};
   		
   		D1C15 = new int[] {0, 300, 375, 440, 365};
   		D2C15 = new int[] {0, 375, 300, 440, 365};
   		
   		D1C16 = new int[] {0, 375, 450, 440, 365};
   		D2C16 = new int[] {0, 450, 375, 440, 365};
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
      // ((gameCanvas)mainGame.getGamePanel()).drawLine(0,1, 1);
    }
}
