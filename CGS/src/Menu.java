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
	
	private JButton start, quit, store, scoreboard;
	
	public Menu(Main w)
	{
		this.w = w;
		
		JPanel buttonPanel = new JPanel();
		
		start = new JButton("start");
		quit = new JButton("quit");
		store = new JButton("store");
		scoreboard = new JButton("scoreboard");
		
		JLabel gameName = new JLabel("CGS");
		gameName.setFont(new Font("Serif", Font.PLAIN, 40));
		
		//this.add(buttonPanel);
		
		start.addActionListener(this);
		quit.addActionListener(this);
		store.addActionListener(this);
		scoreboard.addActionListener(this);
		
		this.add(gameName);
		this.add(start);
		this.add(store);
		this.add(scoreboard);
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
		}
		
		
		/*
		else if (e.getSource().equals(startButton))
		{
			source = sourceBox.getItemAt(sourceBox.getSelectedIndex()) + "";
			destination = destinationBox.getItemAt(destinationBox.getSelectedIndex()) + "";
			int index = source.indexOf("(");
			source = source.substring(index+1, source.length()-1);
			int index2 = destination.indexOf("(");
			destination = destination.substring(index2+1, destination.length()-1);
			
			if (!source.equals(destination))
			{
				w.changePanel(source, destination);
			}
			else
			{
				displaySameBoxCheckedMessage();
			}
			*/
		}


	
	public void itemStateChanged(ItemEvent e) {
		
		
	}
	
}