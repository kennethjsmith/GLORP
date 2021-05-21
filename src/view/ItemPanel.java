package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Room;


public class ItemPanel extends JPanel{
	private final String TITLE = "Items";
	private final static int WIDTH = 315;
	private final static int HEIGHT = 185;

	// Constructor 
	public ItemPanel() {
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        TitledBorder border = BorderFactory.createTitledBorder(TITLE);
        border.setTitleColor(Color.cyan);
        setBorder(border);
        setLayout(new FlowLayout());    
    }
	
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	//test
    	GameIcon key = new GameIcon("src/icons/anhk_key.png");
		key.paintIcon(this, g, 10, 40);	
    }
}
