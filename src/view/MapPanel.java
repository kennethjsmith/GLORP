package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 * Displays the MapView.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class MapPanel extends JPanel{
	// fields
	private final static int HEIGHT = 290;
	private final static int WIDTH = 285;
	private final static TitledBorder MAP_TITLE_BORDER = BorderFactory.createTitledBorder("Map");
	private static final Color OFF_BLACK = new Color(15,15,15);
	/**
	 * 
	 */
	public MapPanel() {
		super();
		setBackground(OFF_BLACK);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLayout(new FlowLayout());
        
        MAP_TITLE_BORDER.setTitleColor(Color.WHITE);
        MAP_TITLE_BORDER.setTitlePosition(TitledBorder.BELOW_TOP);
        MAP_TITLE_BORDER.setTitleJustification(TitledBorder.CENTER);
        setBorder(MAP_TITLE_BORDER);
	}

}
