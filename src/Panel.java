import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class Panel extends JPanel {
	public Panel(int x, int y, int w, int h) {
		/*addComponentListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				rx= e.getX();
				ry= e.getY();
				redraw();
			}
		});*/
		MouseListener mouseListener = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				rx= (x+w+(h/2))-arg0.getX();
				ry= y+(h/2) - arg0.getY();
				evalTheta();
				if(theta<0){
					ry=0;
					rx=0;
				}
				if(theta>150){
					ry=43;
					rx=-74;
				}
				evalTheta();
				text.setText("angle: "+theta);
				System.out.println("y: "+ry+"   x:"+rx+"    theta:"+theta+"     hype:"+Math.sqrt(ry*ry+rx*rx));
				repaint();
				move();
				
			}

			private void move() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
			}
			
		};
		addMouseListener(mouseListener);
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		text= new  JLabel("angle: 0");
		text.setBounds(150, 10, 100, 30);
		add(text);
		
	}
	/*public Panel(){
		MouseListener mouseListener = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				rx= (x+w+(h/2))-arg0.getX();
				ry= y+(h/2) - arg0.getY();
				evalTheta();
				if(theta<0){
					ry=0;
					rx=0;
				}
				if(theta>150){
					ry=43;
					rx=-74;
				}
				repaint();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
			}
			
		};
		addMouseListener(mouseListener);
	}*/
   int x = 150;
   int y = 150;
   int w = 100;
   int h = 10;
   int rx=0;
   int ry=0;
   static double theta;
   static JLabel text;

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      // draw the rectangle here
      //g
      g.drawOval(x+w,y,h,h);
      g.drawRect(x+w+h, y, w, h);
      AffineTransform trans=new AffineTransform();
      trans.rotate(rx,ry,x+w+(h/2),y+(h/2));
      Graphics2D g2d= (Graphics2D)g;
      g2d.setTransform(trans);
      g2d.drawRect(x, y, w, h);
      //g2d.setTransform(null);
   }
   public void evalTheta(){
	   if((rx+ry)!=0){
		   theta= Math.toDegrees(Math.atan2(ry, rx));
	   }else{
		   theta=0;
	   }
   }

   @Override
   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(w + 2 * x, h + 2 * y);
   }

   // create the GUI explicitly on the Swing event thread
   private static void createAndShowGui() {
      Panel mainPanel = new Panel(150,150,100,10);

      JFrame frame = new JFrame("DrawRect");
      frame.setLayout(new BorderLayout());
      frame.setSize(600, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel,BorderLayout.CENTER);
      frame.getContentPane().add(text, BorderLayout.NORTH);
      //frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}
