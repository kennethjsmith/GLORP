package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Holds the Title image icon for the game.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class TitlePanel extends JPanel{
	// A serialized ID for serialization.
	private static final long serialVersionUID = 9044993728288393059L;
	private final static int WIDTH = 285;
	private final static int HEIGHT = 81;
	private GameIcon TITLE_ICON = new GameIcon("src/icons/title.png",250, HEIGHT+10);
	private GameIcon BACKGROUND_ICON = new GameIcon("src/icons/stars.png",WIDTH,HEIGHT);
	
	/**
	 * Constructs the TitlePanel.
	 */
	public TitlePanel() {
		super();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.darkGray);
	}
	
	/**
	 * Paints the TitlePanel.
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	BACKGROUND_ICON.paintIcon(this, g, 0, 0);
    	TITLE_ICON.paintIcon(this, g, 10, -5);
    }
}
