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
	private final static int WIDTH =285;
	private final static int HEIGHT = 215;

	// Constructor 
	public ItemPanel() {
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        TitledBorder border = BorderFactory.createTitledBorder(TITLE);
        border.setTitleColor(Color.cyan);
        setBorder(border);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JPanel item1 = new JPanel();
        JPanel item2 = new JPanel();
        JPanel item3 = new JPanel();
        
        item1.setBackground(Color.lightGray);
        item2.setBackground(Color.lightGray);
        item3.setBackground(Color.lightGray);
        
        item1.setPreferredSize(new Dimension(80,80));
        item2.setPreferredSize(new Dimension(80,80));
        item3.setPreferredSize(new Dimension(80,80));
        
        GameIcon key = new GameIcon("src/icons/key_item_icon.png");
        GameIcon blank = new GameIcon("src/icons/blank_item_icon.png");
        key.resize(75);
        blank.resize(75);
        
        JLabel item1Label = new JLabel(key);
        JLabel item2Label = new JLabel(blank);
        JLabel item3Label = new JLabel(blank);
        
        item1.add(item1Label);
        item2.add(item2Label);
        item3.add(item3Label);
        
        add(item1);
        add(item2);
        add(item3);
    }
	
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
}
