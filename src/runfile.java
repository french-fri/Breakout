import java.awt.Color;
import java.awt.*;
import Elements.*;
import javax.swing.*;
import java.awt.event.*;

public class runfile{

	JComboBox levelbox;
	JFrame welcome;
	static boolean reset = true;
	static boolean on = false; 
	static sound bg;
	static JFrame window;
	static JButton start;
	static int lev_no;
	
	public static void main (String[] args) 
	{	
		while (true)
		{
			if(reset) {
			runfile run = new runfile();
			reset = false;
			bg = new sound("sounds//bg.wav");
			}
			if(on)
			{				
				start();
				window.dispose();
				on = false;
				reset = true;
			}
			System.out.println(); // I DONT EVEN KNOW
		}
	}
	
	public runfile()
	{
	
		String lev_arr[] = {"Demo","Level 1","Level 2","Level 3","Level 4","Level 5", "Level 6"};
		JButton exit = new JButton(new ImageIcon("sprites\\exit2.jpg"));
		JButton start = new JButton(new ImageIcon("sprites\\start_button.jpg"));
		JLabel lev_label = new JLabel(new ImageIcon("sprites\\level.jpg"));	
		JLabel atari = new JLabel(new ImageIcon("sprites\\welcome.png"));
		
		levelbox = new JComboBox(lev_arr);
		
		//Hide drop down button of Combo box
		levelbox.setUI(new javax.swing.plaf.metal.MetalComboBoxUI()
			{public void layoutComboBox(Container parent, MetalComboBoxLayoutManager manager) 
				{super.layoutComboBox(parent, manager);
				 arrowButton.setBounds(0,0,0,0);}});
		
		welcome = new JFrame();
		
		welcome.setExtendedState(JFrame.MAXIMIZED_BOTH);
		welcome.setUndecorated(true);
		welcome.getContentPane().setBackground(new Color(50,50,50));
		welcome.setLayout(null);
		welcome.setVisible(true);
		
		atari.setBounds(250, 200, 1030, 300);
		lev_label.setBounds(650,720,220,70);
		lev_label.setVisible(true);
		start.setBounds(400,720,220,70);
		levelbox.setBounds(650,720,220,70);
		exit.setBounds(900,720,220,70);	

		welcome.add(atari);
		welcome.add(lev_label);
		welcome.add(start);
		welcome.add(levelbox);
		welcome.add(exit);
		
		start.addActionListener(new start());
		exit.addActionListener(new exit());
		
	}

	public static void start()
	{		
		bg.volume();
		window = new JFrame();
		
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		window.setUndecorated(true);
		window.setTitle("ATRIA BREAKOUT");
		window.setVisible(true);

		int w = window.getSize().width; 
		int h = window.getSize().height;
		
		Board game = new Board(w,h,lev_no, bg);
		
		window.addKeyListener(game.new AL());
		window.add(game);
		game.startgame();
		game.gameloop();
		
	}

	public class start implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			lev_no = levelbox.getSelectedIndex();
			on = true;
			welcome.dispose();
		}
	}
	
	public class exit implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
}
