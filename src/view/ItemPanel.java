package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Item;
import model.Player;
import model.Room;

/**
 * The panel for displaying a Player's inventory.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class ItemPanel extends JPanel{
	// fields
	private final String TITLE = "Items";
	private final static int WIDTH = 285;
	private final static int HEIGHT = 122;
	private final GameIcon BACKGROUND = new GameIcon("src/icons/stars.png", WIDTH, HEIGHT);
	
	/**
	 * A default constructor. 
	 */
	public ItemPanel(){
		super();
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        TitledBorder border = BorderFactory.createTitledBorder(TITLE);
        border.setTitleColor(Color.WHITE);
        border.setTitleJustification(TitledBorder.CENTER);
        setLayout(new BorderLayout()); 
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.NONE;
        setBorder(border);
        //setBackground(Color.darkGray);
    }
	
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	BACKGROUND.paintIcon(this, g, 0, 0);
    }
}
