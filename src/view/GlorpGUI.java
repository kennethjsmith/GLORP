package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border; 
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.sound.sampled.*;

import model.Direction;
import model.Fixture;
import model.FixtureType;
import model.Item;
import model.Maze;
import model.Player;
import model.SkinType;
/**
 * The main frame for the GUI.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
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
    private Clip myBackgroundMusic;

    /**
     * 
     */
    public GlorpGUI() {
        super();
        setTitle(TITLE);
        setResizable(false);
        // music
        try {
			myBackgroundMusic = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			music(new File("src/sounds/harp_michael_levy.wav"));
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        //set layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
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

    /**
     * 
     */
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
        JMenu cheats = new JMenu("Cheats");
        //JMenuItem cheats = new JMenuItem("Cheats");
        help.add(cheats);
        
        JMenuItem unlockAllDoors = new JMenuItem("Unlock All Doors");
        cheats.add(unlockAllDoors);
        unlockAllDoors.addActionListener(e ->{
        	Maze.getInstance().unlockAllDoors();
        	this.repaint();
        });
        
        JMenu skins = new JMenu("Skins");
        
        JMenuItem glorp = new JMenuItem("Glorp");
        glorp.addActionListener(e ->{
        	Player tempPlayer = Maze.getInstance().getPlayer();
        	tempPlayer.setSkin(SkinType.ALIEN);
        	tempPlayer.updateRoomIcon();
        	Maze.getInstance().getMyStartRoom().setFixture(new Fixture(150, 150, FixtureType.SHIP));
        	//move player out of fixture base if needed
        	if(Maze.getInstance().getCurrRoom().getFixture() != null) {
        		while(tempPlayer.getBase().intersects(Maze.getInstance().getCurrRoom().getFixture().getBase())) {
        			tempPlayer.move(Direction.SOUTH);
        		}	
        	}
        	this.repaint();
        	try {
        		myBackgroundMusic.close();
				music(new File("src/sounds/harp_michael_levy.wav"));
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        JMenuItem ignignokt = new JMenuItem("Ignignokt");
        ignignokt.addActionListener(e ->{
        	Player tempPlayer = Maze.getInstance().getPlayer();
        	tempPlayer.setSkin(SkinType.MOONINITE);
        	tempPlayer.updateRoomIcon();
        	Maze.getInstance().getMyStartRoom().setFixture(new Fixture(150, 100, FixtureType.ALTSHIP));
        	//move player out of fixture base if needed
        	if(Maze.getInstance().getCurrRoom().getFixture() != null) {
        		while(tempPlayer.getBase().intersects(Maze.getInstance().getCurrRoom().getFixture().getBase())) {
        			tempPlayer.move(Direction.SOUTH);
        		}	
        	}
        	this.repaint();
        	
        	try {
        		myBackgroundMusic.close();
    			music(new File("src/sounds/athf.wav"));
    		} catch (UnsupportedAudioFileException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        help.add(skins);
        skins.add(glorp);
        skins.add(ignignokt);
        
        myMenubar.add(file);
        myMenubar.add(help);
        setJMenuBar(myMenubar);
    }
    	
    /**
     * 
     * @param thePlayer
     */
    public void updateItemPanel(Player thePlayer) {
		myItemPanel.update(thePlayer);
	}
    
    public void music(File theAudioFile) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	    if(theAudioFile == null) myBackgroundMusic.close();
	    else {
		    AudioInputStream ais = AudioSystem.getAudioInputStream(theAudioFile);
		    myBackgroundMusic.open(ais);
		    myBackgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
	    }
    }
}