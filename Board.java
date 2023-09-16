package snake_Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Board extends JPanel implements ActionListener{
	int dots,dots_Size=10,random_cnt=11,apple_x=21,apple_y=21;
	int[] x=new int[900];
	int[] y=new int[900];
	Image apple,head,dot;
	boolean leftDirection=false;
	boolean rightDirection=true;
	boolean upDirection=false;
	boolean downDirection=false;
	Timer timer;
	boolean inGame=true;
	Board(){
		addKeyListener(new TAdapter());
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(300,300));
		setFocusable(true);
		load_images();
		initGame();
	}
	public void load_images() {
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("snake_Game/icons/apple.png"));
		apple=i1.getImage();
		ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("snake_Game/icons/dot.png"));
		dot=i2.getImage();
		ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("snake_Game/icons/head.png"));
		head=i3.getImage();
	}
	public void initGame() {
		dots=3;
		for(int i=0;i<dots;i++)
		{
			y[i]=50;
			x[i]=50-i*dots_Size;
		}
		locateApple();
		timer=new Timer(140,this); //140ms speed
		timer.start();
	}
	public void locateApple()
	{
		int r=(int)(Math.random() * random_cnt); //to keep the apple in the range
		apple_x=r*dots_Size;
		r=(int)(Math.random() * random_cnt); //to keep the apple in the range
		apple_y=r*dots_Size;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g)
	{
		if(inGame)
		{
		g.drawImage(apple,apple_x,apple_y,this);
		for(int i=0;i<dots;i++)
		{
			if(i==0)
				g.drawImage(head,x[i],y[i],this);
			else
				g.drawImage(dot,x[i],y[i],this);
		}
		Toolkit.getDefaultToolkit().sync();
		}else
		{
			gameOver(g);
		}
	}
	public void gameOver(Graphics g) {
		String msg="Game Over!!";
		Font font=new Font("SAN_SERIF",Font.BOLD,14);
		FontMetrics metrices=getFontMetrics(font);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(msg,90,300/2);
	}
	public void move() {
		for(int i=dots;i>0;i--)
		{
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		if(leftDirection)
		{
			x[0]=x[0]-dots_Size;
		}
		if(rightDirection)
		{
			x[0]=x[0]+dots_Size;
		}
		if(upDirection)
		{
			y[0]=y[0]-dots_Size;
		}
		if(downDirection)
		{
			y[0]=y[0]+dots_Size;	
		}
	}
	
	public void apple_collision() {
		 if ((x[0] == apple_x) && (y[0] == apple_y)) {
			 dots++;
	         locateApple();
	        }
	}
	public void checkCollision() {
		for(int i=0;i<dots;i++)
		{
			if(i>4 && x[0]==x[i] && y[0]==y[i])
				inGame=false;
		}
		if(x[0]>=300)
			inGame=false;
		if(y[0]>=300)
			inGame=false;
		if(x[0]<0)
			inGame=false;
		if(y[0]<0)
			inGame=false;
		if(!inGame)
			timer.stop();
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(inGame)
		{
			apple_collision();
			checkCollision();
			move();
		}
		repaint();//For refreshment
	}
	public class TAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e)
		{
			int key=e.getKeyCode();
			if(key==KeyEvent.VK_LEFT && (!rightDirection)) {
				leftDirection=true;
				upDirection=false;
				downDirection=false;
			}
			if(key==KeyEvent.VK_RIGHT && (!leftDirection)) {
				rightDirection=true;
				upDirection=false;
				downDirection=false;
			}
			if(key==KeyEvent.VK_DOWN && (!upDirection)) {
				downDirection=true;
				leftDirection=false;
				rightDirection=false;
			}
			if(key==KeyEvent.VK_UP && (!downDirection)) {
				upDirection=true;
				leftDirection=false;
				rightDirection=false;
			}
		}
	}
}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub	
//		
//	}
