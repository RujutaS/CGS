package menus;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import utilities.GameScreen;
import utilities.ImageLoader;

public class Store extends Menu {
	
	private final int cost = 10;
	
	private Button buy, select;
	private int currentImage, selected;
	private boolean[] bought;
	
	private int coins;

	public Store() {
		super();
		this.addButton(new Button(540, 640, 200, 75, "Main Menu", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
		
		buy = new Button(540, 550, 200, 75, "Buy", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK);
		select = new Button(540, 550, 200, 75, "Select", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK);
		this.addButton(buy);
		
		this.addButton(new Button(100, 300, 200, 75, "Previous", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
		this.addButton(new Button(980, 300, 200, 75, "Next", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
				
		currentImage = 0;
		selected = 0;
		coins = 0;
	}
	
	/**
	 * Gives the character that the user has selected
	 * @return the image of the character that the user has selected
	 */
	public PImage getSelectedImage() {
		return ImageLoader.characters[currentImage];
	}
	
	/**
	 * Reads the stored data and updates the store with those values
	 * @param papplet the PApplet which helps read the data
	 */
	public void setup(PApplet papplet) {
		JSONObject json = papplet.loadJSONObject("data/store.json");
		coins = json.getInt("coins");
		selected = json.getInt("selected");
		JSONArray jsonBought = json.getJSONArray("bought");
		bought = new boolean[jsonBought.size()];
		for(int i = 0;i<jsonBought.size();i++) {
			bought[i] = jsonBought.getBoolean(i);
		}
		updateButtons();
	}
	
	private void saveData(PApplet papplet) {
		JSONObject json = new JSONObject();
		json.setInt("coins", coins);
		json.setInt("selected", selected);
		JSONArray jsonBought = new JSONArray();
		for(int i = 0;i<bought.length;i++) {
			jsonBought.append(bought[i]);
		}
		json.setJSONArray("bought", jsonBought);
		papplet.saveJSONObject(json, "data/store.json");
	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if (buttonText.equals("Main Menu")) {
			saveData(gameScreen);
			gameScreen.changeMenuMode("main");
		} else if(buttonText.equals("Buy")) {
			if(!bought[currentImage] && coins>cost) {
				coins -=cost;
				bought[currentImage] = true;
				updateButtons();
			}
		} else if(buttonText.equals("Select")) {
			if(selected!=currentImage) {
				selected = currentImage;
				updateButtons();
			}
		} else if(buttonText.equals("Previous") ) {
			 currentImage--;
			 if(currentImage<0) {
				 currentImage = ImageLoader.characters.length-1;
			 } 
			 updateButtons();
		} else if(buttonText.equals("Next")) {
			currentImage++;
			 if(currentImage>ImageLoader.characters.length-1) {
				 currentImage = 0;
			 }
			 updateButtons();
		}
	}
	
	private void updateButtons() {
		if(bought[currentImage]) {
			this.addButton(select);
			this.removeButton(buy);
		} else {
			this.addButton(buy);
			this.removeButton(select);
		}
		
		if(selected == currentImage) {
			select.setFill(Color.LIGHT_GRAY);
		} else {
			select.setFill(Color.WHITE);
		}
		
		if(coins<10) {
			buy.setFill(Color.RED);
			buy.setHighlightFillColor(Color.RED);
		} else {
			buy.setFill(Color.WHITE);
			buy.setHighlightFillColor(Color.LIGHT_GRAY);
		}
	}

	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Roboto", 20));
		drawer.textSize(40);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("Store", 640, 50);
		
		drawer.textSize(20);
		drawer.text("You have "+coins+" coins. Each character costs "+cost+" coins.", 640, 100);
		
//		drawer.rect(300, 150, 680, 400);
		drawer.image(ImageLoader.characters[currentImage], 300, 150, 680, 400);
	}

}
