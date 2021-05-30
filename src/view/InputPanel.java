package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
/**
 * The panel for player input.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class InputPanel extends JPanel{
	private final static int WIDTH = 285;
	private final static int HEIGHT = 125;
	
	/**
	 * 
	 */
	public InputPanel() {
		super();
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		// Create a border
	    Border whiteline = BorderFactory.createLineBorder(Color.WHITE);
	    setBorder(whiteline);
		
		JTextField textField = new JTextField(5);
		// TODO: this breaks focus
        add(new JLabel("Answer here"));
	}
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
}
