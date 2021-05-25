package view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GameIcon extends ImageIcon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameIcon() {
		super();
	}
	
	public GameIcon(String theString) {
		super(theString);
	}
	
	public GameIcon(String theString, int theSize) {
		super(theString);
		resize(theSize);
	}
	
	public GameIcon(String theString, int theWidth, int theHeight) {
		super(theString);
		resize(theWidth, theHeight);
	}
	
	public void resize(int theSize) {
		Image inTempImage;
    	//resize left facing standing icon
    	inTempImage = this.getImage(); // transform it 
		inTempImage = inTempImage.getScaledInstance(theSize, theSize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		this.setImage(inTempImage); // transform it back
    
	}
	
	public void resize(int theWidth, int theHeight) {
		Image inTempImage;
    	//resize left facing standing icon
    	inTempImage = this.getImage(); // transform it 
		inTempImage = inTempImage.getScaledInstance(theWidth, theHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		this.setImage(inTempImage); // transform it back
    
	}
}
