package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MapPanel extends JPanel{
	
	private final static int SIZE = 285;
	private final static TitledBorder MAP_TITLE_BORDER = BorderFactory.createTitledBorder("Map");
	
	public MapPanel() {
		super();
		setBackground(Color.darkGray);
        setPreferredSize(new Dimension(SIZE,SIZE));
        setLayout(new FlowLayout());
        
        MAP_TITLE_BORDER.setTitleColor(Color.WHITE);
        setBorder(MAP_TITLE_BORDER);
	}

}
