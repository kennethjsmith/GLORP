package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RiddlePanel extends JPanel {
	private final String TITLE = "Riddle";
	private final static int WIDTH = 800;
	private final static int HEIGHT = 300;
	
	public RiddlePanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        JLabel label = new JLabel(TITLE, JLabel.LEFT);
        setLayout(new FlowLayout());
        add(label); 
    }
}