package view;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The RoundJPanel is used for the RiddlePanel to give the questions
 * and answers a rounded edge.
 * @author Ken Smith, Katelynn Oleson, Heather Finch
 * @version 1.0.
 */
public class RoundJPanel extends JPanel {
	// A serialized ID for serialization.
	private static final long serialVersionUID = 3018490270013327762L;

	/**
	 * Paints the RoundJPanel.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
	}
}
