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
	private final static int WIDTH = 275;
	private final static int HEIGHT = 100;
	private JLabel myTitleLabel;
	private JLabel myBackgroundLabel;
	private GameIcon TITLE_ICON = new GameIcon("src/icons/title.png",250,100);
	private GameIcon BACKGROUND_ICON = new GameIcon("src/icons/stars.png",285,100);
	
	/**
	 * 
	 */
	public TitlePanel() {
		super();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.darkGray);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		setBorder(compound);

	}
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	BACKGROUND_ICON.paintIcon(this, g, 0, 0);
    	TITLE_ICON.paintIcon(this, g, 15, 0);
    }

}
