package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class RiddlePanel extends JPanel {
	
	private InputPanel myInputPanel;
	
	private final String TITLE = "Riddle";
	private final GameIcon SPHINX = new GameIcon("src/icons/sphinx.png");
	private final GameIcon SPEECH_BUBBLE = new GameIcon("src/icons/speech_bubble.png");
	private final static int WIDTH = 300;
	private final static int HEIGHT = 500;
	
	public RiddlePanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        SPHINX.resize(200);
        SPEECH_BUBBLE.resize(300);
        // add border
        TitledBorder border = BorderFactory.createTitledBorder(TITLE);
        border.setTitleColor(Color.YELLOW);
        setBorder(border);
        
        setLayout(new GridBagLayout()); 
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        
        setBackground(new Color(194,178,128));
        
        JPanel myRiddle = new JPanel();
        myRiddle.setBackground(Color.WHITE);
        JLabel test = new JLabel("Question here");
        test.setBackground(Color.WHITE);
        test.setOpaque(true);
        myRiddle.add(test);
        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        add(myRiddle, c);
        
        myInputPanel = new InputPanel();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(300,0,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        add(myInputPanel, c);
        
    }
	
	@Override
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);
    	//TODO: this is borrowed art from a game called PixelPeople. Its open source, ok to use with credit? (atleast for project)
    	SPEECH_BUBBLE.paintIcon(this, g, 0, 10);
    	SPHINX.paintIcon(this, g, 95, 200);
    }
}