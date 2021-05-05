package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MazePanel extends JPanel {
	private final String TITLE = "Map";
	
	public MazePanel() {
        setPreferredSize(new Dimension(200,200));
        JLabel label = new JLabel(TITLE, JLabel.LEFT);
        setLayout(new FlowLayout());
        add(label); 
    }
}
