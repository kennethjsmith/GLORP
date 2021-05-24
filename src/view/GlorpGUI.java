package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
    private JPanel myMapPanel;
    private MapView myMapView;
    private RiddlePanel myRiddlePanel;
    private ItemPanel myItemPanel;
    //TODO: add panels for maze map and sphinx/questions

    public GlorpGUI() {
        super();
        setTitle(TITLE);
        setResizable(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        myMapPanel = new JPanel(new FlowLayout());
        myMapPanel.setBackground(Color.darkGray);
        myMapPanel.setPreferredSize(new Dimension(285,285));
        
        TitledBorder mapPanelTitleBorder = BorderFactory.createTitledBorder("Map");
        mapPanelTitleBorder.setTitleColor(Color.WHITE);
        myMapPanel.setBorder(mapPanelTitleBorder);
        
        c.gridx = 0;
        c.gridy = 0;
        
        myMapView = new MapView();
        myMapPanel.add(myMapView); //, BorderLayout.CENTER);
        add(myMapPanel, c);
        
        myItemPanel = new ItemPanel();
        myItemPanel.setBackground(Color.lightGray); //SAND COLOR
        c.gridx = 0;
        c.gridy = 1;
        add(myItemPanel, c);
        
        myRoomPanel = new RoomPanel();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        add(myRoomPanel, c);
        
        myRiddlePanel = new RiddlePanel();
        myRiddlePanel.setBackground(new Color(194,178,128)); //SAND COLOR
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 2;
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
    	
}