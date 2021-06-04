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
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import model.Riddle;
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
    private FloatControl myGainControl;
	

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
			music(new File("src/sounds/wlae.wav"));
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
        myGainControl = (FloatControl) myBackgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
        myGainControl.setValue(-30f);
        
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

    // TODO: move this into builder pattern
    /**
     * 
     */
    public void addMenuBar() {
    	JMenuBar myMenubar = new JMenuBar();
        
        // create file menu
    	JMenu file = new JMenu("File");
    	JMenuItem restart = new JMenuItem("Restart");
        JMenuItem save = new JMenuItem("Save Game");
        JMenuItem load = new JMenuItem("Load Game");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e ->{
        	System.exit(ABORT);
        });
        
        //create settings menu
        JMenu settings = new JMenu("Settings");
        JMenu sound = new JMenu("Sound");
        JSlider volume = new JSlider(0,100,60);
        volume.setPaintTicks(true);
        volume.setPaintLabels(true);
        volume.setMajorTickSpacing(20);
        volume.setMinorTickSpacing(5);
        // TODO: fix the scale, is this logarithmic?
        volume.addChangeListener(e ->{
			if(myBackgroundMusic.isOpen()) {
				float range = myGainControl.getMaximum() - myGainControl.getMinimum();
				float gain =(range * (float)volume.getValue()/volume.getMaximum()) + myGainControl.getMinimum();
				myGainControl.setValue(gain);
			}
        });
        
        //create help menu
        JMenu help = new JMenu("Help");
        // TODO make this window singleton
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e ->{
        	JFrame aboutWindow = new JFrame();
        	aboutWindow.setLayout(new FlowLayout());
        	aboutWindow.setPreferredSize(new Dimension(200,200));
        	JTextArea text = new JTextArea();
            text.setText("Glorp: Revenge of the Sphinx \n"
            		+ "Version 1.0 \n \n"
            		+ "Glorp is a simple java game developed by \n");
            aboutWindow.add(text);
            aboutWindow.pack();
            aboutWindow.setLocationRelativeTo(this);
            aboutWindow.setVisible(true);
            aboutWindow.setAlwaysOnTop(true);
        });
        
        JMenuItem instructions = new JMenuItem("Game Play Instructions");
        JMenu cheats = new JMenu("Cheats");
        
        JMenuItem unlockAllDoors = new JMenuItem("Unlock All Doors");
        
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
				music(new File("src/sounds/wlae.wav"));
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
        
        file.add(restart);
        file.add(save);
        file.add(load);
        file.add(exit);
        
        settings.add(sound);
        sound.add(volume);
        
        help.add(about);
        help.add(instructions);
        help.add(cheats);
        cheats.add(unlockAllDoors);
        help.add(skins);
        skins.add(glorp);
        skins.add(ignignokt);
        
        myMenubar.add(file);
        myMenubar.add(settings);
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
    
    /**
     * activates the riddle panel and returns the runnable object to open a new thread
     * @return
     */
    public RiddlePanel getRunnableRiddlePanel(Riddle theRiddle) {
        myRiddlePanel.update(true, theRiddle);
        return myRiddlePanel;
    }
    
//    public String updateRiddlePanel(boolean theRiddlePromptStatus, Riddle theRiddle) {
//        myRiddlePanel.update(theRiddlePromptStatus, theRiddle); //update state of view
//        System.out.println("Riddle Prompt updated");
//        
//        repaint(); //repaint the riddlePrompt ... not repainting???
//        
//        if(theRiddlePromptStatus) {
//            System.out.println("Riddle Prompt displayed");
//        }
//        
//        String inAnswer = null;
//        
//        while(!(myRiddlePanel.hasAnswer())) { //while answer not ready
//            System.out.println("Waiting for answer");
//            inAnswer = myRiddlePanel.getAnswer();
//        }
//        
//        System.out.println("Answer retreived");
//        
//        return inAnswer;
//    }
    
    public void music(File theAudioFile) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	    if(theAudioFile == null) myBackgroundMusic.close();
	    else {
		    AudioInputStream ais = AudioSystem.getAudioInputStream(theAudioFile);
		    myBackgroundMusic.open(ais);
		    myBackgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
	    }
    }
}