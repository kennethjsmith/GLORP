package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class InstructionsWindow extends JFrame{
	// fields
	private JPanel myPanel;
	
	private static final String TITLE = "Instructions";
	private static final GameIcon UNLOCKED_DOOR_ICON = new GameIcon("src/icons/door_green.png", 30, 6);
	private static final GameIcon LOCKED_DOOR_ICON = new GameIcon("src/icons/door_yellow.png", 30, 6);
	private static final GameIcon BLOCKED_DOOR_ICON = new GameIcon("src/icons/door_red.png", 30, 6);
	private static final GameIcon ARROW_KEY_ICON = new GameIcon("src/icons/arrow_keys.png", 120, 75);
	private static final Color OFF_BLACK = new Color(15,15,15);
	private final Color OFF_WHITE = new Color(248,248,255);
	
	public InstructionsWindow() {}
	
	public InstructionsWindow(ImageIcon theIcon) {
		super();
        setTitle(TITLE);
        setIconImage(theIcon.getImage());
        setResizable(false);
        
        myPanel = new JPanel();
        myPanel.setBackground(OFF_BLACK);
        myPanel.setOpaque(true);
        //set layout
        myPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel greenDoor = new JLabel(UNLOCKED_DOOR_ICON);
        JLabel yellowDoor = new JLabel(LOCKED_DOOR_ICON);
        JLabel redDoor = new JLabel(BLOCKED_DOOR_ICON);
        JLabel arrowLabel = new JLabel(ARROW_KEY_ICON);
		
        JTextPane intro = new JTextPane();
        intro.setOpaque(false);
        intro.setForeground(OFF_WHITE);
        intro.setEditable(false);
	    intro.setText("Find a new dilithium crystal within the maze. But beware, the "
	    		+ "Sphinx! Who seeks to entomb Glorp in the chambers he explores!");
	    StyledDocument doc = intro.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
	    
        JLabel greenDoorText = new JLabel();
        greenDoorText.setForeground(OFF_WHITE);
        greenDoorText.setText("Green doors are unlocked");
	    JLabel yellowDoorText = new JLabel();
	    yellowDoorText.setForeground(OFF_WHITE);
	    yellowDoorText.setText("Yellow doors are locked");
	    JLabel redDoorText = new JLabel();
	    redDoorText.setForeground(OFF_WHITE);
	    redDoorText.setText("Red doors are blocked");
        
	    JLabel arrowText = new JLabel();
	    arrowText.setForeground(OFF_WHITE);
	    arrowText.setText("Arrow keys to move");
	    
	    
	    c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10,0,20,0);
	    myPanel.add(intro, c);
	    
	    c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,20,0);
        myPanel.add(greenDoor, c);
	    
	    c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,20,20,0);
        myPanel.add(greenDoorText, c);
	    
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,20,0);
        myPanel.add(yellowDoor, c);
        
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(0,20,20,0);
        myPanel.add(yellowDoorText, c);
	    
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,20,0);
        myPanel.add(redDoor, c);
        
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(0,20,20,0);
        myPanel.add(redDoorText, c);
        
	    c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,0);
        myPanel.add(arrowLabel, c);
	    
	    c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(0,20,0,0);
        myPanel.add(arrowText, c);
	    
        this.add(myPanel);
	    pack();
	    setVisible(true);
	    setAlwaysOnTop(true);
	}
	
}
