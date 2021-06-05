package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class AboutWindow extends JFrame{
	// fields
	private static final String TITLE = "About";
	private final static int ICON_WIDTH = 225;
	private final static int ICON_HEIGHT = 75;
	private GameIcon TITLE_ICON = new GameIcon("src/icons/title.png", ICON_WIDTH, ICON_HEIGHT);
	
	public AboutWindow() {}
	
	public AboutWindow(ImageIcon theIcon) {
		super();
        setTitle(TITLE);
        setIconImage(theIcon.getImage());
        setResizable(false);
		setLayout(new FlowLayout());
		
		//set layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
		JLabel icon = new JLabel(TITLE_ICON);	
		JLabel text1 = new JLabel();
	    text1.setText("<html>Glorp: Revenge of the Sphinx<br/>"
	    		+ "Version 1.0 (June 8, 2021)<br/>"
	    		+ "Authors: Heather Finch, Katelynn Oleson, Ken Smith</html>");
	    JLabel text2 = new JLabel();
	    text2.setText("<html>Glorp is a Java based game developed for TCSS 360B<br/>"
	    		+ "Software Development And Quality Assurance Techniques<br/>"
	    		+ "Prof. Tom Capaul - University of Washington Tacoma</html>");
	    
	    c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(10,0,10,0);
	    add(icon, c);
	    
	    JSeparator sep = new JSeparator();
	    c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weighty = .5;
        c.insets = new Insets(0,0,0,0);
        add(sep, c);
	    
	    c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(0,0,5,0);
	    add(text1, c);
	    c.gridx = 0;
        c.gridy = 3;
	    add(text2, c);
	    
	    
	    pack();
	    setVisible(true);
	    setAlwaysOnTop(true);
	}
	
}
