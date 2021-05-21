package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
    private RiddlePanel myRiddlePanel;
    private ItemPanel myItemPanel;
    //TODO: add panels for maze map and sphinx/questions

    public GlorpGUI() {
        super();
        setTitle(TITLE);
        //sets frame background
        //getContentPane().setBackground(Color.black);
        //setPreferredSize(new Dimension(840, 860));
        //setResizable(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        myMapPanel = new MapPanel();
        myMapPanel.setBackground(Color.darkGray);
        c.gridx = 0;
        c.gridy = 0;
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