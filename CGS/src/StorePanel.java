
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class StorePanel extends JPanel implements ActionListener, ItemListener{
	
	
	
	private final float DRAWING_WIDTH = 1000, DRAWING_HEIGHT = 500;
	private JButton quit, back;
	private JButton purple, blue, red;
	private Main w;
	private GamePanel g;
	
	private String color = " ";
	
	public StorePanel(Main w) {
		
		
	}
	
	
	public void printStore(Main w) {
	
		this.w = w;
		quit = new JButton("quit");
		back = new JButton("back");
		purple = new JButton("buy");
		blue = new JButton("buy");
		red = new JButton("buy");
		
		quit.addActionListener(this);
		back.addActionListener(this);
		purple.addActionListener(this);
		blue.addActionListener(this);
		red.addActionListener(this);
		
		JLabel coinCount = new JLabel("coins: "); //add + g.getCoins when game panel is set up
		coinCount.setFont(new Font("Serif", Font.PLAIN, 10));
		
		this.add(coinCount);
		this.add(back);
		this.add(quit);
		this.add(purple);
		this.add(red);
		this.add(blue);
		
		
		
		System.out.println("store panel");
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(quit)) 
		{
			System.exit(0);;
		} else if (e.getSource().equals(back)) {
			w.getMenu();
		} else if (e.getSource().equals(purple)) {
			color = "purple";
			//replace buy button with choose button
		} else if (e.getSource().equals(red)) {
			color = "red";
			//replace buy button with choose button
		} else if (e.getSource().equals(blue)) {
			color = "blue";
			//replace buy button with choose button
		}
	}
	


	
	public String getColor() {
		return color;
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		
	}

	
	
	
	
	
	
	/*
	public void draw()
	{
		background(255);
		pushMatrix();
		pushStyle();
		
		
		float scaleW = width / DRAWING_WIDTH;
		float scaleH = height / DRAWING_HEIGHT;
		scale(scaleW,scaleH);
		//flightSim.draw(this);
		popStyle();
		popMatrix();
	}

*/

	
	
}