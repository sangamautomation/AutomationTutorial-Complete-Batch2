/**
 * 
 */
package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Sangam
 * Description: Graphic Utils
 */
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicUtils extends JPanel
{

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.setBackground(new Color( 255,255,255));

		g.setColor(Color.yellow);
		g.fillOval(100,100,300,300);

		g.setColor(Color.white);
		g.fillArc(110,120,250,250,90,180);

		g.setColor(new Color (218,165,32));
		g.drawArc(130,110,250,280,90,-180);

		g.setColor(Color.yellow);
		g.fillOval(125,105,250,290);

		// draw Arc2D.Double
		//g2.setColor(Color.black);
		//g2.fill(new Arc2D.Double(130, 200, 200,150,170, 200, Arc2D.OPEN));


	}

	public void paintComponent2(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.setBackground(new Color( 255,255,255));

		g.setColor(Color.yellow);
		g.fillOval(100,100,300,300);

		g.setColor(Color.white);
		g.fillArc(110,120,250,250,90,180);

		g.setColor(new Color (218,165,32));
		g.drawArc(130,110,250,280,90,-180);

		g.setColor(Color.yellow);
		g.fillOval(125,105,250,290);

		// draw Arc2D.Double
		//g2.setColor(Color.black);
		//g2.fill(new Arc2D.Double(130, 200, 200,150,170, 200, Arc2D.OPEN));

		BasicStroke stroke = new BasicStroke(
				12,
				BasicStroke.CAP_BUTT,
				0,
				BasicStroke.JOIN_BEVEL
				);
		g2.setStroke(stroke);

		GeneralPath leftLaughLine = new GeneralPath();
		int x = 150;
		int y = 230;
		leftLaughLine.moveTo(x, y);
		leftLaughLine.curveTo(x - 20, y + 5, x - 25, y + 25, x - 25, y + 25);
		g2.draw(leftLaughLine);

		GeneralPath rigthLaughLine = new GeneralPath();
		x = 350;
		y = 230;
		rigthLaughLine.moveTo(x, y);
		rigthLaughLine.curveTo(x + 20, y + 5, x + 25, y + 25, x + 25, y + 25);
		g2.draw(rigthLaughLine);
	}

	//Draws a rectangle with given row/col chars for displaying indicative image in console logs for clarity
	public static void drawBox(int x, int y) {
		for (int i = 0; i < y; i++) {
			System.out.print("-");
		}
		System.out.println();
		for (int i = 0; i < x - 2; i++) {
			System.out.print("|");
			for (int j = 0; j < y - 2; j++) {
				System.out.print(" ");
			}
			System.out.println("|");
		}
		for (int i = 0; i < y; i++) {
			System.out.print("-");
		}
		System.out.println();
		System.out.println();
	}
	
	//Draws a rectangle with given row/col chars for displaying indicative image & write some text in console logs for clarity
	public static void drawBoxNWrite(int x, int y) {
		for (int i = 0; i < y; i++) {
			System.out.print("-");
		}
		System.out.println();
		for (int i = 0; i < x - 2; i++) {
			System.out.print("|");
			for (int j = 0; j < y - 2; j++) {
				System.out.print(" ");
			}
			System.out.println("|");
			//		StringUtils.writeDiagonal("Image","       ");
		}
		for (int i = 0; i < y; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	//Draws a square with given row/col chars for displaying indicative image in console logs for clarity
	public static void printSquare(int width,int length){
		for(int i=0;i<width;i++){
			StringBuilder stringBuilder=new StringBuilder();
			stringBuilder.append("* ");
			for (int j=2;j<length;j++){
				if(i==0){
					stringBuilder.append("* ");
				}else if(i==width-1){
					stringBuilder.append("* ");
				}else {
					stringBuilder.append("  ");
				}
			}
			stringBuilder.append("* ");
			System.out.println(stringBuilder);
		}
	}
	
	//Draws a square with given row/col chars  & display some text inside for displaying indicative image in console logs for clarity
	public static void printSquareNWrite(int width,int length){
		for(int i=0;i<width;i++){
			StringBuilder stringBuilder=new StringBuilder();
			stringBuilder.append("* ");
			for (int j=2;j<length;j++){
				if(i==0){
					stringBuilder.append("*");
				}else if(i==width-1){
					stringBuilder.append("*");
				}else {
					stringBuilder.append(" ");
				}
			}
			StringUtils.writeDiagonal("Image","       ");

			stringBuilder.append("*");
			System.out.println(stringBuilder);

		}
	}
	
	
	
	//-------------Test here---------
	
	

	public static void main (String[] args)
	{



		JFrame f = new JFrame("Task 2");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicUtils d = new GraphicUtils();
		f.add(d);
		f.setSize(600,600);
		f.setVisible(true);
		/*  
        JFrame f = new JFrame("Task 2");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicUtils d = new GraphicUtils();
        f.add(d);
        f.setSize(600,600);
        f.setVisible(true);*/

		for (int codePoint = 0x1F600; codePoint <= 0x1F64F;) {
			System.out.print(Character.toChars(codePoint));
			codePoint++;
			if (codePoint % 16 == 0) {
				System.out.println();
			}
		}

		System.out.println("\uD83D\uDE40");
	}

}