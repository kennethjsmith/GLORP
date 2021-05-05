package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RiddlePanel extends JPanel {
	private final String TITLE = "Riddle";
	
	public RiddlePanel() {
        setPreferredSize(new Dimension(400,200));
        JLabel label = new JLabel(TITLE, JLabel.LEFT);
        setLayout(new FlowLayout());
        add(label); 
    }
}