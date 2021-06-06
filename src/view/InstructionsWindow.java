package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class InstructionsWindow extends JFrame{
	// fields
	private static final String TITLE = "Instructions";
	
	public InstructionsWindow() {}
	
	public InstructionsWindow(ImageIcon theIcon) {
		super();
        setTitle(TITLE);
        setIconImage(theIcon.getImage());
        setResizable(false);
		
		//set layout
        this.setLayout(new FlowLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        	
		JLabel text1 = new JLabel();
		text1.setPreferredSize(new Dimension(325,100));
	    text1.setText("<html>\"Another dilithium crystal blown...\", grumbled Glorp. <br/>"
	    		+ " Luckily, scanners detected a viable sample beneath a nearby Earth pyramid.</html>");
	    JLabel text2 = new JLabel();
	    text2.setPreferredSize(new Dimension(325,100));
	    text2.setText("<html>Navigate the maze to find the Ankh Key and Scarab<br/>"
	    		+ " Arc; secure the Dilithium Gem and return to your saucer."
	    		+ " But beware, the Sphinx! Who seeks to entomb Glorp in the chambers he explores!</html>");
	    
	    c.gridx = 0;
        c.gridy = 0;
        //c.anchor = GridBagConstraints.FIRST_LINE_START;
        //c.insets = new Insets(10,0,10,0);
	    add(text1, c);
	    
	    JSeparator sep = new JSeparator();
	    c.gridx = 0;
        c.gridy = 1;
        //c.gridwidth = GridBagConstraints.REMAINDER;
        //c.weighty = .5;
        //c.insets = new Insets(0,0,0,0);
        add(sep, c);
	    
	    c.gridx = 0;
        c.gridy = 2;
        //c.weighty = 1;
       // c.anchor = GridBagConstraints.FIRST_LINE_START;
        //c.insets = new Insets(0,0,5,0);
	    add(text2, c);
	    
	    pack();
	    setVisible(true);
	    setAlwaysOnTop(true);
	}
	
}
