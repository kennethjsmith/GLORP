package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import model.Item;
import model.Maze;
import model.Player;

public class GlorpGUI extends JFrame {
    
    // static final fields (class constants)

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 0;

    /**
     * The window title.
     */
    private static final String TITLE = "GLORP";
    
    private RoomPanel myRoomPanel;
    private MapPanel myMapPanel;
    private MapView myMapView;
    private RiddlePanel myRiddlePanel;
    private ItemPanel myItemPanel;
    private TitlePanel myTitlePanel;
    //TODO: add panels for maze map and sphinx/questions

    public GlorpGUI() {
        super();
        setTitle(TITLE);
        setResizable(false);
        
        //set layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        //TODO: make this titlepanel its own class
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
        c.gridx = 0;
        c.gridy = 2;
        add(myItemPanel, c);
        
        // add room panel to GUI
        myRoomPanel = new RoomPanel();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        add(myRoomPanel, c);
        
        // add riddle panel to GUI
        myRiddlePanel = new RiddlePanel();
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 3;
        add(myRiddlePanel, c);
        
        
        addMenuBar();
              
        pack();
        repaint();
        
    }

    public void addMenuBar() {
    	JMenuBar myMenubar = new JMenuBar();
        JMenu file = new JMenu("File");
        
        
        JMenuItem save = new JMenuItem("Save Game");
        file.add(save);
        JMenuItem load = new JMenuItem("Load Game");
        file.add(load);
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        help.add(about);
        JMenuItem instructions = new JMenuItem("Game Play Instructions");
        help.add(instructions);
        JMenuItem cheats = new JMenuItem("Cheats");
        help.add(cheats);
        
        
        myMenubar.add(file);
        myMenubar.add(help);
        setJMenuBar(myMenubar);
    }
    	
    public void updateItemPanel(Player thePlayer) {
		myItemPanel.update(thePlayer);
	}
}