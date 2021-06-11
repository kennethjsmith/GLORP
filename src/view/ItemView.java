package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ItemType;
import model.Player;

/**
* Displays the current items in the players inventory.
* Goes on top of ItemPanel.
* @author Ken Smith, Heather Finch, Katelynn Oleson 
* @version 1.0.
*/
public class ItemView extends JPanel {
	// A serialized ID for serialization.
	private static final long serialVersionUID = 2788214106090858619L;

	private JPanel item1;
    private JPanel item2;
    private JPanel item3;
    private JLabel itemLabel1;
    private JLabel itemLabel2;
    private JLabel itemLabel3;
    private final static int WIDTH = 275;
	private final static int HEIGHT = 90;
	private final static int ICON_SIZE = 75;
	private final static GameIcon BLANK = new GameIcon("src/icons/blank_item_icon.png", ICON_SIZE);
	private static final Color TRANSPARENT = new Color(0, 0, 0, 64);
    
	/**
	 * Constructor creates the ItemView.
	 */
	public ItemView() {
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setLayout(new FlowLayout());
		item1 = new JPanel();
	    item2 = new JPanel();
	    item3 = new JPanel();
	    
	    item1.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
	    item1.setBackground(TRANSPARENT);
	    item2.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
	    item2.setBackground(TRANSPARENT);
	    item3.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
	    item3.setBackground(TRANSPARENT);
	    
	    itemLabel1 = new JLabel(BLANK);
	    itemLabel1.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
	    itemLabel1.setBackground(TRANSPARENT);
	    itemLabel2 = new JLabel(BLANK);
	    itemLabel2.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
	    itemLabel2.setBackground(TRANSPARENT);
	    itemLabel3 = new JLabel(BLANK);
	    itemLabel3.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
	    itemLabel3.setBackground(TRANSPARENT);
	    
	    item1.add(itemLabel1);
	    item2.add(itemLabel2);
	    item3.add(itemLabel3);
	    
	    add(item1);
	    add(item2);
	    add(item3);
	    
	    setBackground(TRANSPARENT);
	}
	
	/**
	 * Updates the ItemView based on the players inventory.
	 * @param Player The Players whose inventory we are using to update this
	 */
	public void update(Player thePlayer) {
		itemLabel1.setIcon(BLANK);
		itemLabel2.setIcon(BLANK);
		itemLabel3.setIcon(BLANK);
		
		ItemType tempItem;
		
		if(thePlayer.getInventory().size() >= 1) {
			tempItem = thePlayer.getInventory().get(0);
			if (tempItem != null) itemLabel1.setIcon(ItemType.getItemPanelIcon(tempItem));
		}
		
		if(thePlayer.getInventory().size() >= 2) {
			tempItem = thePlayer.getInventory().get(1);
			if (tempItem != null) itemLabel2.setIcon(ItemType.getItemPanelIcon(tempItem));
		}
		
		if(thePlayer.getInventory().size() >= 3) {
			tempItem = thePlayer.getInventory().get(2);
			if (tempItem != null) itemLabel3.setIcon(ItemType.getItemPanelIcon(tempItem));
		}
	}
	
	/**
	 * Paints the ItemView.
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
}
