package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

import model.Direction;
import model.Fixture;
import model.FixtureType;
import model.Maze;
import model.Player;
import model.Riddle;
import model.SkinType;
/**
 * The main frame for the GUI. Extends JFrame.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class GlorpGUI extends JFrame {
    // A serialized ID for serialization.
    private static final long serialVersionUID = 0;
    
    private GlorpPanel myGlorpPanel;
    private RoomPanel myRoomPanel;
    private RiddlePanel myRiddlePanel;
	
    private static final String TITLE = "Glorp";
    private ImageIcon ICON = new ImageIcon("src/icons/space_invader.png");
    private final int POP_UP_WIDTH = 400;
	private final int POP_UP_HEIGHT = 300;
  
    /**
     * Constructs the GUI.
     */
    public GlorpGUI() {
        super();
        setTitle(TITLE);
        setResizable(false);
        setIconImage(ICON.getImage());
        
        // Music.
        Music.WLAE.play();
        
        // Set layout.
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
       
        myGlorpPanel = new GlorpPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        add(myGlorpPanel, c);
        
        // Add room panel to GUI.
        myRoomPanel = new RoomPanel();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        add(myRoomPanel, c);
        
        // Add riddle panel to GUI.
        myRiddlePanel = new RiddlePanel();
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        add(myRiddlePanel, c);
        
        addMenuBar();
              
        pack();
        repaint();  
    }

    // Create and add the menu bar.
    private void addMenuBar() {
    	JMenuBar myMenubar = new JMenuBar();
        
        // Create file menu.
    	JMenu file = new JMenu("File");
    	JMenuItem restart = new JMenuItem("Restart");
        JMenuItem save = new JMenuItem("Save Game");
        JMenuItem load = new JMenuItem("Load Game");
        JMenuItem exit = new JMenuItem("Exit");
        save.addActionListener(e ->{
            Maze.serializeMyObjects();
            System.out.println("Save game");
        });
        
        load.addActionListener(e ->{
            Maze.deserializeMyObjects();
            repaint();
            System.out.println("Load game");
        });
        
        exit.addActionListener(e ->{
        	System.exit(ABORT);
        });
        
        // Create settings menu.
        JMenu settings = new JMenu("Settings");
        JMenu sound = new JMenu("Sound");
        JMenu music = new JMenu("Music Volume");
        JMenu effects = new JMenu("SFX Volume");
        
        JSlider musicSlider = new JSlider(0,Volume.HIGH.getLevel(),Volume.MEDIUM.getLevel());
        musicSlider.setPaintTicks(true);
        musicSlider.setPaintLabels(true);
        musicSlider.setMajorTickSpacing(1);
        musicSlider.addChangeListener(e ->{
        	int inLevel = musicSlider.getValue();
        	Volume target = Volume.LOW;
        	for(Volume vol : Volume.values()) {
        		if(inLevel == vol.getLevel()) target = vol;
        	}
        	Music.setVolume(target.getGain());
        });
        
        JSlider SFXSlider = new JSlider(0,Volume.HIGH.getLevel(),Volume.MEDIUM.getLevel());
        SFXSlider.setPaintTicks(true);
        SFXSlider.setPaintLabels(true);
        SFXSlider.setMajorTickSpacing(1);
        SFXSlider.addChangeListener(e ->{
        	int inLevel = SFXSlider.getValue();
        	Volume target = Volume.LOW;
        	for(Volume vol : Volume.values()) {
        		if(inLevel == vol.getLevel()) target = vol;
        	}
        	SoundEffect.setVolume(target.getGain());
        });
        
        
        music.add(musicSlider);
        effects.add(SFXSlider);
        sound.add(music);
        sound.add(effects);
        
        // Create help menu.
        JMenu help = new JMenu("Help");
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
	        	Music.WLAE.play();
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
	        	Music.ATHF.play();
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
     * Activates the riddle panel and returns the runnable object to open a new thread.
     * @return RiddlePanel The riddle panel
     */
    public RiddlePanel getRunnableRiddlePanel(Riddle theRiddle) {
    	Objects.requireNonNull(theRiddle);
        myRiddlePanel.startUp(theRiddle);
        return myRiddlePanel;
    }

	/**
	 * Getter for the GlorpPanel.
	 * @return GlorpPanel
	 */
	public GlorpPanel getGlorpPanel() {
		return myGlorpPanel;
	}
	
    /**
     * Sets the focus to the room.
     */
    public void setFocusToRoom() {
        myRoomPanel.setFocusable(true);
        myRoomPanel.setRequestFocusEnabled(true);
        myRoomPanel.requestFocusInWindow(); 
    }
    
    /**
     * Sets the focus to the riddle panel.
     */
    public void setFocusToRiddle() {
        myRiddlePanel.grabFocus();
    }

    /**
     * Getter for the RoomPanel.
     * @return JPanel The RoomPanel
     */
    public JPanel getRoomPanel() {
        return myRoomPanel;
    }
}