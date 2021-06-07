package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.sound.sampled.*;

import model.Direction;
import model.Fixture;
import model.FixtureType;
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

    // fields
    private GlorpPanel myGlorpPanel;
    private RoomPanel myRoomPanel;
    private RiddlePanel myRiddlePanel;
    private Clip myBackgroundMusic;
    private FloatControl myGainControl;
	
    private static final String TITLE = "Glorp";
    private ImageIcon ICON = new ImageIcon("src/icons/space_invader.png");
    private final int POP_UP_WIDTH = 400;
	private final int POP_UP_HEIGHT = 300;
  
    /**
     * 
     */
    public GlorpGUI() {
        super();
        setTitle(TITLE);
        setResizable(false);
        setIconImage(ICON.getImage());
        
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
        myGainControl.setValue(-80f);
        
        //set layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
       
        myGlorpPanel = new GlorpPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        add(myGlorpPanel, c);
        
        // add room panel to GUI
        myRoomPanel = new RoomPanel();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        add(myRoomPanel, c);
        
        // add riddle panel to GUI
        myRiddlePanel = new RiddlePanel();
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
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
        save.addActionListener(e ->{
            System.out.println("Save game");
        });
        
        load.addActionListener(e ->{
            System.out.println("Load game");
        });
        
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
        sound.add(volume);
        
        //create help menu
        JMenu help = new JMenu("Help");
        // TODO: make these window singleton
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e ->{
        	AboutWindow inAbout = new AboutWindow(ICON);
        	inAbout.setSize(new Dimension(POP_UP_WIDTH, POP_UP_HEIGHT));
        	inAbout.setLocationRelativeTo(this);
        });
        
        JMenuItem instructions = new JMenuItem("Game Play Instructions");
        instructions.addActionListener(e ->{
        	InstructionsWindow inInstructions = new InstructionsWindow(ICON);
        	inInstructions.setSize(new Dimension(POP_UP_WIDTH, POP_UP_HEIGHT+30));
        	inInstructions.setLocationRelativeTo(this);
        });
        
        JMenu cheats = new JMenu("Cheats");
        JMenuItem unlockAllDoors = new JMenuItem("Unlock All Doors");
        unlockAllDoors.addActionListener(e ->{
        	Maze.getInstance().unlockAllDoors();
        	this.repaint();
        });
        cheats.add(unlockAllDoors);
        
        JMenu skins = new JMenu("Skins");
        
        JMenuItem glorp = new JMenuItem("Glorp");
        glorp.addActionListener(e ->{
        	Player tempPlayer = Maze.getInstance().getPlayer();
        	if(!tempPlayer.getFixed()) {
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
        	}
        });
        
        JMenuItem ignignokt = new JMenuItem("Ignignokt");
        ignignokt.addActionListener(e ->{
        	Player tempPlayer = Maze.getInstance().getPlayer();
        	if(!tempPlayer.getFixed()) {
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
        	}
        });
        
        file.add(restart);
        file.add(save);
        file.add(load);
        file.add(exit);
        
        settings.add(sound);
        
        help.add(about);
        help.add(instructions);
        help.add(cheats);
        help.add(skins);
        skins.add(glorp);
        skins.add(ignignokt);
        
        myMenubar.add(file);
        myMenubar.add(settings);
        myMenubar.add(help);
        
        setJMenuBar(myMenubar);
    }

    /**
     * activates the riddle panel and returns the runnable object to open a new thread
     * @return
     */
    public RiddlePanel getRunnableRiddlePanel(Riddle theRiddle) {
        myRiddlePanel.startUp(theRiddle);
        return myRiddlePanel;
    }
    
    public void music(File theAudioFile) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	    if(theAudioFile == null) myBackgroundMusic.close();
	    else {
		    AudioInputStream ais = AudioSystem.getAudioInputStream(theAudioFile);
		    myBackgroundMusic.open(ais);
		    myBackgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
	    }
    }

	/**
	 * @return the myItemView
	 */
	public GlorpPanel getGlorpPanel() {
		return myGlorpPanel;
	}
	
    public void setFocusToRoom() {
        myRoomPanel.setFocusable(true);
        myRoomPanel.setRequestFocusEnabled(true);
        myRoomPanel.requestFocusInWindow(); 
       // myRoomPanel.grabFocus();
    }
    
    public void setFocusToRiddle() {
        myRiddlePanel.grabFocus();
    }

    public JPanel getRoomPanel() {
        // TODO Auto-generated method stub
        return myRoomPanel;
    }
}