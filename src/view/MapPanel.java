package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapPanel extends JPanel {
	private final String TITLE = "Map";
	private final static int WIDTH = 300;
	private final static int HEIGHT = 800;
	
	public MapPanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        JLabel label = new JLabel(TITLE, JLabel.LEFT);
        setLayout(new FlowLayout());
        add(label); 
    }
}
