
import javax.swing.*;
import javax.swing.JOptionPane;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import java.awt.*;


public class Main {
	
	private JFrame window;
	private JPanel cardPanel;
	private Menu menu; 
	private StorePanel store;
	private GamePanel gameView;
	private StorePanel storeView;
	private ScoreboardPanel scoreboardView;
	private PSurfaceAWT.SmoothCanvas processingCanvas;
	

	public Main() 
	{
		try
		{
			final int WIDTH = 500;
			final int HEIGHT = 500;

			gameView = new GamePanel();
			PApplet.runSketch(new String[]{""}, gameView);
			storeView = new StorePanel(this);
	
			//scoreboardView = new ScoreboardPanel();
			
			
			
			//window.getContentPane().add(new JPanel());
			

			PSurfaceAWT surf = (PSurfaceAWT) gameView.getSurface();
			processingCanvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
			processingCanvas.getFrame().setTitle("CGS");
			window = (JFrame)processingCanvas.getFrame();

			window.setBounds(0,0, WIDTH, HEIGHT);
			window.setMinimumSize(new Dimension(WIDTH,HEIGHT));
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(true);

			cardPanel = new JPanel();
			CardLayout cl = new CardLayout();
			cardPanel.setLayout(cl);



			window.getContentPane().removeAll();

			menu = new Menu(this); 

			System.out.println();

			cardPanel.add(menu,"1");
			cardPanel.add(processingCanvas,"2");
			

			window.setLayout(new BorderLayout());
			window.pack();
			window.add(cardPanel);
			window.revalidate();
		}
		catch (IllegalStateException e)
		{
			System.out.println("processing library error -- rerun");
		}

	}
	
	public static void main(String[] args)
	{
		Main m = new Main();
	}

	
	public void startGame() 
	{
		gameView.printGame();

		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		processingCanvas.requestFocus();	

		
		//String message = "cgs";
		//JOptionPane.showMessageDialog(null, message, "cgs", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void store() 
	{
		
		
		storeView.printStore(this);

		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		processingCanvas.requestFocus();	

		
		//String message = "cgs";
		//JOptionPane.showMessageDialog(null, message, "cgs", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void scoreboard() 
	{
		
		scoreboardView.printScoreboard();

		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		processingCanvas.requestFocus();
		
		//String message = "cgs";
		//JOptionPane.showMessageDialog(null, message, "cgs", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public Menu getMenu() {
		return menu;
	}
	

}