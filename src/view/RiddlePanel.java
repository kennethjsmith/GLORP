package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class RiddlePanel extends JPanel {
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
        setLayout(new FlowLayout()); 
    }
	
	@Override
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);
    	//TODO: this is borrowed art from a game called PixelPeople. Its open source, ok to use with credit? (atleast for project)
    	SPHINX.paintIcon(this, g, 100, 300);
    	SPEECH_BUBBLE.paintIcon(this, g, 0, 50);
    }
}