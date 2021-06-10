package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * The panel for displaying a Player's inventory.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class ItemPanel extends JPanel{
	// Serialized ID for serialization.
	private static final long serialVersionUID = -4527116705909636684L;

	private final String TITLE = "Items";
	private final static int WIDTH = 285;
	private final static int HEIGHT = 122;
	private final static GameIcon BACKGROUND = new GameIcon("src/icons/stars.png", WIDTH, HEIGHT);
	
	/**
	 * Constructs the item panel.
	 */
	public ItemPanel(){
		super();
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        TitledBorder border = BorderFactory.createTitledBorder(TITLE);
        border.setTitleColor(Color.WHITE);
        border.setTitleJustification(TitledBorder.CENTER);
        setLayout(new BorderLayout()); 
        setBorder(border);
    }
	
	/**
	 * Paints the item panel.
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	BACKGROUND.paintIcon(this, g, 0, 0);
    }
}
