package controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.JFrame;

import model.Direction;
import model.Door;
import model.Fixture;
import model.FixtureType;
import model.Item;
import model.ItemType;
import model.Maze;
import model.PiecePoint;
import model.Player;
import model.Riddle;
import model.Room;
import view.GameIcon;
import view.GlorpGUI;
import view.InputPanel;
import view.Music;
import view.RiddlePanel;
import view.SoundEffect;


/**
 * The controller, mediates the maze model and GUI. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public class GlorpController {
    // fields
	private Maze myMaze;
	private Player myPlayer;
	private GlorpGUI myWindow; 
	private boolean myRiddleOpenFlag;
	private final Set<Integer> myPressedKeys = new HashSet<Integer>();
	private final HashMap<Direction, Point> myPositionChange = new HashMap<Direction, Point>();
	private static final String[] correctSphinxResponse = {"You will never escape!", "Grrrrrrrr...", ">:(", "Beginners luck won't save you everytime.", "..."};
	private static final String[] incorrectSphinxResponse = {"Now this passage is sealed... like your fate.", "*sinister laughter*", ">:)", "I've known smarter scarabs.", "..."};
	private static final String[] runawaySphinxResponse = {"I can smell your fear.", "Coward!", "Running away can't save you.", "...", "I see your confidence is dwindling"};
	private final static String PRESSED = "pressed ";
    private final static String RELEASED = "released ";
    private final static int SPINX_RESPONSE_TIME = 1500;
    private final static int SHORT_EXPLANATION_TIME = 1500;
    private final static int LONG_EXPLANATION_TIME = 7000;

	private static final Random RAND = new Random();

	
	/**
	 * Default constructor for GlorpController
	 * @throws LineUnavailableException 
	 */
	public GlorpController() throws LineUnavailableException{
		myMaze = Maze.getInstance();
		myPlayer = myMaze.getPlayer();	
		myRiddleOpenFlag = false;

		myWindow = new GlorpGUI();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
        myWindow.setTitle("GLORP");
        
        
        //myWindow.addKeyListener(this);    
        myWindow.repaint();
        
        // set up hashmap
        // TODO: Fix magic numbers! & make this a helper?
        myPositionChange.put(Direction.EAST, new Point(20, 200));
        myPositionChange.put(Direction.WEST, new Point(380, 200));
        myPositionChange.put(Direction.NORTH, new Point(200, 380));
        myPositionChange.put(Direction.SOUTH, new Point(200, 20));
        
        
        // key bindings instead of keylisteners
        String[] theDirections = {"LEFT", "RIGHT", "UP", "DOWN"};
        for(String key : theDirections) {
            addKeyActions(key);
       }
    }


	/**
	 * A helper method, checks for item, fixture, and door interactions after a key event.
	 */
    private void checkInteractions() {
    	checkItems();
    	checkFixtures();
    	
    	Direction inDir = checkDoorZones(); 
    	Door inCurrDoor = myMaze.getCurrRoom().getDoors().get(inDir);
    	
    	if(inDir != null) { //if near a door 
    	    if(myMaze.isValidTraversal(inDir, myMaze.getCurrRoom())) { // If valid to attempt to move in that direction
                if(inCurrDoor.isUnlocked()) { // If the door is unlocked, move that direction
                    attemptMapTraversal(inDir); //update map & player
                }else if(!(myRiddleOpenFlag)) { // riddle threads are not already open
                    myRiddleOpenFlag = true;
                    openRiddleThreads(inDir); //open producer and consumer threads to watch for riddle activity
                }
    	    }
    	}
	}
    
    private void checkItems() {
    	// check items
    	Item inItem = myMaze.getCurrRoom().getItem();
    	if(inItem != null && myPlayer.getIconArea().intersects(inItem.getIconArea())) {
    		myPlayer.getInventory().add(myMaze.getCurrRoom().getItem().getType());
    		myMaze.getCurrRoom().setItem(null);
    		myMaze.getCurrRoom().setCurrentRoom(true);
    		myWindow.getGlorpPanel().getItemView().update(myPlayer);
    		SoundEffect.ITEM.play();
    	}
    }
    
    private void checkFixtures() {
        // check fixtures
    	Fixture inFixture = myMaze.getCurrRoom().getFixture();
    	if(inFixture != null && myPlayer.canInteract(inFixture) && myPlayer.getIconArea().intersects(inFixture.getInteractionZone())) {
    		if(myPlayer.getInventory().contains(ItemType.KEY) 
    				&& inFixture.getType() == FixtureType.CHEST) {
    			myPlayer.getInventory().remove(ItemType.KEY);
    			myMaze.getCurrRoom().addItem(new Item(new PiecePoint(250,250), ItemType.GEM));
    			myWindow.getGlorpPanel().getItemView().update(myPlayer);
    			inFixture.setBase(new Rectangle(new Dimension(0,0)));
    			inFixture.setIconArea(new Rectangle(new Dimension(0,0)));
    			inFixture.setInteractionZone(new Rectangle(new Dimension(0,0)));
    			inFixture.setMyYCoordinate(inFixture.getMyYCoordinate()-60);
    			inFixture.setMyXCoordinate(inFixture.getMyXCoordinate()-10);
    			SoundEffect.EXPLOSION.play();
    			TimerTask task = new TimerTask() {
    		        int i = 0;
    		        @Override
    		        public void run() {
    		            if (i <= 15) {
    		            	inFixture.setIcon(new GameIcon("src/icons/explosion/frame_" + i + ".png", 200, 250));
    	    				myWindow.repaint();
    		                i++;
    		            }
    		            else {
    		                cancel();
    		            }
    		        }
    		    };
    		    Timer timer = new Timer();
    		    timer.scheduleAtFixedRate(task, 0, 100);
    		}
    		// TODO: clean this up, 2 near identical sections of spaghetti
    		// also, this is messy, I use the player piece's icon to display the win message
    		// I also added a field and methods to fix his location
    		if(myPlayer.getInventory().contains(ItemType.GEM) 
    			&& inFixture.getType() == FixtureType.ALTSHIP){
    			inFixture.setIcon(new GameIcon("src/icons/alt_ship_win.png", 200, 275));
        		myPlayer.setCoordinate(new PiecePoint(150,175));
        		myPlayer.setRoomIcon(new GameIcon("src/icons/win_message_icon.png", 220, 150));
        		myPlayer.setFixed(true);
        		Music.stop();
        		SoundEffect.WIN.play();
    			TimerTask task = new TimerTask() {
    		        int i = 0;
    		        @Override
    		        public void run() {
    		            if (i <= 50) {
    		            	inFixture.setMyYCoordinate(inFixture.getMyYCoordinate()-10);
    		            	myWindow.repaint();
    		                i++;
    		            }
    		            else {
    		                cancel();
    		            }
    		        }
    		    };
    		    Timer timer = new Timer();
    		    timer.scheduleAtFixedRate(task, 0, 100);
        		myPlayer.getInventory().clear();
        		myWindow.getGlorpPanel().getItemView().update(myPlayer);
    		}

    		if(myPlayer.getInventory().contains(ItemType.GEM) 
        		&& inFixture.getType() == FixtureType.SHIP){
        		inFixture.setIcon(new GameIcon("src/icons/ship_win.png", 200, 150));
        		myPlayer.setCoordinate(new PiecePoint(150,175));
        		myPlayer.setRoomIcon(new GameIcon("src/icons/win_message_icon.png", 220, 150));
        		myPlayer.setFixed(true);
        		Music.stop();
        		SoundEffect.WIN.play();
    			TimerTask task = new TimerTask() {
    		        int i = 0;
    		        @Override
    		        public void run() {
    		            if (i <= 50) {
    		            	inFixture.setMyYCoordinate(inFixture.getMyYCoordinate()-10);
    		            	myWindow.repaint();
    		                i++;
    		            }
    		            else {
    		                cancel();
    		            }
    		        }
    		    };
    		    Timer timer = new Timer();
    		    timer.scheduleAtFixedRate(task, 0, 100);
        		myPlayer.getInventory().clear();
        		myWindow.getGlorpPanel().getItemView().update(myPlayer);
    		}
    	}
    }
    
    /**
     * returns the direction of the door in region of or null. ... not the best, but whatev
     * 
     * return inner class obj, boolean hasDoor, doorDirection? 
     * 
     * 
     */
    private Direction checkDoorZones() {
        // duplicate code, create doorZone class/ hashmap with rectangle, Direction
        // east door zone
        if(myPlayer.getIconArea().intersects(Room.getEastDoorZone())) {
            return Direction.EAST; 
        }
        // west door zone
        else if(myPlayer.getIconArea().intersects(Room.getWestDoorZone())) {
            return Direction.WEST;   
        }
        // north door zone
        else if(myPlayer.getIconArea().intersects(Room.getNorthDoorZone())) {
            return Direction.NORTH; 
        }
        // south door zone
        else if(myPlayer.getIconArea().intersects(Room.getSouthDoorZone())) {
            return Direction.SOUTH; 
        }else
            return null;
    }
    
    /**
     * returns the direction of the door in region of or null. ... not the best, but whatev
     * 
     * return inner class obj, boolean hasDoor, doorDirection? 
     * 
     * 
     */
    private void attemptMapTraversal(Direction theDirection) {
        if(myMaze.isValidTraversal(theDirection, myMaze.getCurrRoom())) { // If the move is valid
        	
        	// Grab the relevant Door
        	Door currDoor = myMaze.getCurrRoom().getDoors().get(theDirection);
        	
        	// If the door is unlocked, move that direction
        	if(currDoor.isUnlocked()) {
				myMaze.traverseMaze(theDirection); 
				myPlayer.getCoordinate().setLocation(myPositionChange.get(theDirection)); 
				myPlayer.updateRectangles(); 				
        	} 
        }
    }
    
    /**
     * Open Producer and Consumer Threads for Riddle Panel 
     * 
     */
    private void openRiddleThreads(Direction theDirection) {
        // open producer
        Riddle currRiddle = myMaze.getCurrRoom().getDoors().get(theDirection).getMyRiddle();
        
        RiddlePanel inRiddlePanel = myWindow.getRunnableRiddlePanel(currRiddle);
        InputPanel inputPanel = inRiddlePanel.getInputPanel();
                
        Thread inRiddleProducer = new Thread(inRiddlePanel); 
        inRiddleProducer.start(); // show riddle prompt and wait for message
        
        // open consumer
        Thread inConsumer = new RiddleConsumer(inRiddlePanel, inputPanel, inRiddleProducer);
        inConsumer.start();

    }
	
    /*
     * Private inner class for consumer thread
     */
	private class RiddleConsumer extends Thread{
	    private RiddlePanel myRiddlePanel;
	    private InputPanel myInputPanel;
	    
	    public RiddleConsumer(RiddlePanel thePanel, InputPanel theInputPanel, Thread theProducer) {
	        myRiddlePanel = thePanel;
	        myInputPanel = theInputPanel;
	    }
	    
	    /*
	     * Returns true if the message response equals the answer in the riddle
	     */
	    private boolean answerCorrect() {
	        boolean inCorrect = myRiddlePanel.getRiddle().verifyAnswer(myInputPanel.getResponse(myRiddlePanel.getRiddle()));
	        return inCorrect;
	    }

        @Override
        public void run() {
          //while no message || player still in door region 
            while( (!myRiddlePanel.hasResponse()) && checkDoorZones() != null){          
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Error in GlorpController run method!");
                    e.printStackTrace();
                }
            }                
            Direction inDir = checkDoorZones();
                
            if(myRiddlePanel.hasResponse() && inDir != null) {
                if(answerCorrect()) {
                    myMaze.getCurrRoom().getDoors().get(inDir).setUnlocked();
                    attemptMapTraversal(inDir);
                    displayRiddleExplanation();
                    myRiddlePanel.sphinxResponse(correctSphinxResponse[RAND.nextInt(correctSphinxResponse.length)]); //change to be randomized
                } else {
                    myMaze.getCurrRoom().getDoors().get(inDir).setBlocked();
                    if(!myMaze.canWin()) {
                    	myPlayer.setCoordinate(new PiecePoint(50,175));
                		myPlayer.setRoomIcon(new GameIcon("src/icons/trapped_message_icon.png", 400, 150));
                		myPlayer.setFixed(true); // change to trigger lose scenario
//                		try {
//							myWindow.music(null);
//						} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
                		Music.stop();
                		SoundEffect.LOSE.play();
                    }
                	displayRiddleExplanation();
                    myRiddlePanel.sphinxResponse(incorrectSphinxResponse[RAND.nextInt(incorrectSphinxResponse.length)]); 
                }
                    
            } else {
                myRiddlePanel.sphinxResponse(runawaySphinxResponse[RAND.nextInt(runawaySphinxResponse.length)]); 
            }
    
            // terminate this thread & producer thread 
            myWindow.repaint();
            try {
                Thread.sleep(SPINX_RESPONSE_TIME);
                    
            } catch (InterruptedException e) {
                System.out.println("Error in GlorpController run method!");
                e.printStackTrace();
            }
                
            myRiddlePanel.shutDown(); 
            myWindow.repaint();
            myRiddleOpenFlag = false;
        }
        private void displayRiddleExplanation() {
    		String explanation = myRiddlePanel.getRiddle().getExplanation();
    		if(explanation.length() > 1) {
    			try {
            		myRiddlePanel.riddleExplanation(explanation);
            		if(explanation.length() > 60) Thread.sleep(LONG_EXPLANATION_TIME);
            		else Thread.sleep(SHORT_EXPLANATION_TIME);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    	}
	}
	
	
	
	// Key Binding set up method & inner Action class
	   
    /*
     * Multiple keys key binding solution, 
     * Modified code of KeyBoardAnimation.java 
     * from https://tips4java.wordpress.com/2013/06/09/motion-using-the-keyboard/
     */
    private void addKeyActions(String keyStroke){
        //  Separate the key identifier from the modifiers of the KeyStroke

        // don't really need... removes modifiers like "shift" or "control"
        int offset = keyStroke.lastIndexOf(" ");
        String key = offset == -1 ? keyStroke :  keyStroke.substring( offset + 1 );
        String modifiers = keyStroke.replace(key, "");

        //  Get the InputMap and ActionMap of the component

        InputMap inputMap = myWindow.getRoomPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = myWindow.getRoomPanel().getActionMap();

        //  Create Action and add binding for the pressed key

        Action pressedAction = new KeyBinder(key, true);
        String pressedKey = PRESSED + key;
        KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKey);
        inputMap.put(pressedKeyStroke, pressedKey);
        actionMap.put(pressedKey, pressedAction);

        //  Create Action and add binding for the released key

        Action releasedAction = new KeyBinder(key, false);
        String releasedKey = modifiers + RELEASED + key;
        KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKey);
        inputMap.put(releasedKeyStroke, releasedKey);
        actionMap.put(releasedKey, releasedAction);
    }
	
	// private class for key bindings
	
	   private class KeyBinder extends AbstractAction implements Action {
	        private String myKey;
	        private final boolean myAddFlag;
	        
	        private KeyBinder(String theKey, boolean theAddFlag) {
	            myKey = theKey;
	            myAddFlag = theAddFlag;
	        }
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(myAddFlag) {
	                
	            
	            helper(true); // add to pressedKeys
	            
	            Direction inDirection = Direction.generateDirection(myPressedKeys);
	            Direction validDirection = null;

	            try {
	                validDirection = myMaze.getCurrRoom().validateDirection(myPlayer, inDirection);
	                myPlayer.move(validDirection);
	            } catch (CloneNotSupportedException e1) {
	                e1.printStackTrace();
	            }
	            checkInteractions();
	            myWindow.repaint();
	            }else {
	                helper(false); //remove from pressedKeys
	            }
	        }
	        
	        //helper
	        /*
	         * If true, add key
	         * if false, remove key 
	         */
	        private void helper( boolean theAddFlag) {
	            int inKey = -1;
	            
	            if(myKey.equals("LEFT")) {
	                inKey = KeyEvent.VK_LEFT;
	            }else if(myKey.equals("RIGHT")) {
	                inKey = KeyEvent.VK_RIGHT;
	            }else if(myKey.equals("UP")) {
	                inKey = KeyEvent.VK_UP; 
	            }else if(myKey.equals("DOWN")) {
	                inKey = KeyEvent.VK_DOWN;
	            }
	            
	            if(inKey != -1) {
	                if(theAddFlag) {
	                    myPressedKeys.add(inKey);
	                }else {
	                    myPressedKeys.remove(inKey);
	                    if(myPressedKeys.isEmpty()) { // is this needed? 
	                      myPlayer.setStride(0);
	                      myPlayer.setSkipFrame(false);
	                    }
	                    myWindow.repaint();
	                }
	            }
	                
	        }
	    }

}