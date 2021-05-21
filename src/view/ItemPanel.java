package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ItemPanel extends JPanel{
	private final String TITLE = "Items";
	private final static int WIDTH = 350;
	private final static int HEIGHT = 200;

	// Constructor 
	public ItemPanel() {
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        
        // Gives the JLabel a name "Items"
        JLabel label = new JLabel(TITLE, JLabel.LEFT);
        setLayout(new FlowLayout());
        add(label);
        
    }
}
