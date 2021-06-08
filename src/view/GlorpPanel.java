package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GlorpPanel extends JPanel{
	// fields
	private TitlePanel myTitlePanel;
	private MapPanel myMapPanel;
    private MapView myMapView;
    private ItemPanel myItemPanel;
    private ItemView myItemView;
	
    /**
     * 
     */
	public GlorpPanel() {
		this.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		setBorder(compound);
		
	    // TODO: make this titlepanel its own class
	    myTitlePanel = new TitlePanel();
	    c.gridx = 0;
	    c.gridy = 0;
	    add(myTitlePanel, c);
	    
	    
	    // add map view to map panel, add to GUI
	    myMapPanel = new MapPanel();
	    myMapView = new MapView();
	    myMapPanel.add(myMapView);
	    c.gridx = 0;
	    c.gridy = 1;
	    add(myMapPanel, c);
	    
	    // add item panel to GUI
	    myItemPanel = new ItemPanel();
	    myItemView = new ItemView();
	    myItemPanel.add(myItemView, BorderLayout.NORTH);
	    c.gridx = 0;
	    c.gridy = 2;
	    add(myItemPanel, c);
	}
	
	/**
	 * @return the myItemView
	 */
	public ItemView getItemView() {
		return myItemView;
	}
}
