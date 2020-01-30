import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Menu extends JPanel implements ActionListener, ItemListener
{
	
	Main w;
	
	private JButton start, quit, store, scoreboard, help;
	
	public Menu(Main w)
	{
		this.w = w;
		
		JPanel buttonPanel = new JPanel();
		
		start = new JButton("start");
		quit = new JButton("quit");
		store = new JButton("store");
		scoreboard = new JButton("scoreboard");
		help = new JButton("help");
		
		JLabel gameName = new JLabel("CGS");
		gameName.setFont(new Font("Serif", Font.PLAIN, 40));
		
		//this.add(buttonPanel);
		
		start.addActionListener(this);
		quit.addActionListener(this);
		store.addActionListener(this);
		scoreboard.addActionListener(this);
		help.addActionListener(this);
		
		this.add(gameName);
		this.add(start);
		this.add(store);
		this.add(scoreboard);
		this.add(help);
		this.add(quit);
		
		
	}

	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(start)) 
		{
			w.startGame();
		} else if (e.getSource().equals(quit)) {
			System.exit(0);
		} else if (e.getSource().equals(store)) {
			w.store();
		} else if (e.getSource().equals(scoreboard)) {
			w.scoreboard();
		} else if (e.getSource().equals(help)) {
			displayInstructions();
		}
		
	
		}


	
	private void displayInstructions() {
		String message = "add instructions";
		JOptionPane.showMessageDialog(null, message, "instructions", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		
	}
	
}