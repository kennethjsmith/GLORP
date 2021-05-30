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
	private final static int HEIGHT = 125;
	
	private JPanel item1;
    private JPanel item2;
    private JPanel item3;
    JLabel item1Label;
    JLabel item2Label;
    JLabel item3Label;
	

	/**
	 * A default constructor. 
	 */
	public ItemPanel() {
		super();
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        TitledBorder border = BorderFactory.createTitledBorder(TITLE);
        border.setTitleColor(Color.WHITE);
        setBorder(border);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        setBackground(Color.darkGray);
        
        item1 = new JPanel();
        item2 = new JPanel();
        item3 = new JPanel();
        
        item1.setBackground(Color.darkGray);
        item2.setBackground(Color.darkGray);
        item3.setBackground(Color.darkGray);
        
        item1.setPreferredSize(new Dimension(80,80));
        item2.setPreferredSize(new Dimension(80,80));
        item3.setPreferredSize(new Dimension(80,80));
        
        GameIcon blank = new GameIcon("src/icons/blank_item_icon.png", 75);
        
        item1Label = new JLabel(blank);
        item2Label = new JLabel(blank);
        item3Label = new JLabel(blank);
        
        item1.add(item1Label);
        item2.add(item2Label);
        item3.add(item3Label);
        
        add(item1);
        add(item2);
        add(item3);
    }
	
	/**
	 * 
	 * @param thePlayer
	 */
	public void update(Player thePlayer) {
		for(Item i : thePlayer.getInventory()) {
			item1Label.setIcon(i.getItemPanelIcon());
		}
	}
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
}
