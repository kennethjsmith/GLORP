package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Holds the Title image icon for the game.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class TitlePanel extends JPanel{
	// fields
	private final static int WIDTH = 285;
	private final static int HEIGHT = 81;
	private final static int TITLE_WIDTH = 260;
	private final static int TITLE_HEIGHT = 90;
	private GameIcon TITLE_ICON = new GameIcon("src/icons/title.png",250, HEIGHT+10);
	private GameIcon BACKGROUND_ICON = new GameIcon("src/icons/stars.png",WIDTH,HEIGHT);
	
	/**
	 * 
	 */
	public TitlePanel() {
		super();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.darkGray);
	}
	
	
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	BACKGROUND_ICON.paintIcon(this, g, 0, 0);
    	TITLE_ICON.paintIcon(this, g, 10, -5);
    }

}
