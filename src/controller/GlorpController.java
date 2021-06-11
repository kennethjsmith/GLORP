/*
 * GLORP: Revenge of the Sphinx
 */

package controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.LineUnavailableException;
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
 * @version 1.0
 */
public class GlorpController {
	
    // Fields
	
	// Sphinx responses are displayed on the riddle panel after the user interacts with it.
    private static final String[] correctSphinxResponse = {"You will never escape!", "Grrrrrrrr...", ">:(", "Beginners luck won't save you everytime.", "...", "..."};
    private static final String[] incorrectSphinxResponse = {"Now this passage is sealed... like your fate.", "*sinister laughter*", ">:)", "I've known smarter scarabs.", "...", "..."};
    private static final String[] runawaySphinxResponse = {"I can smell your fear.", "Coward!", "Running away can't save you.", "I see your confidence is dwindling", "...", "..."};
    
    // Random object used to select a random response for the array of sphinx responses.
    private static final Random RAND = new Random();

    // True if the riddle panel is currently being used, false otherwise.
	private boolean myRiddleOpenFlag;
    
    // The amount of time the responses are displayed on the riddle panel.
    // The response times must all be unique. 
    private final static int SPHINX_RESPONSE_TIME = 1000;    
    private final static int SHORT_EXPLANATION_TIME = 1500;
    private final static int LONG_EXPLANATION_TIME = 3000;
    
    // Used for key bindings. 
    private final static String PRESSED = "pressed ";
    private final static String RELEASED = "released ";
  
    // A set of the direction keys currently being pressed.
    private final Set<Integer> myPressedKeys = new HashSet<Integer>();
    private final HashMap<Direction, Point> myPositionChange = new HashMap<Direction, Point>();
   
    // Maze and Player are objects from Model that Controller interacts with.
	private Maze myMaze;
	private Player myPlayer;
	
	// The JFrame that all GUI components are added to.
	private GlorpGUI myWindow; 
	
	/**
	 * Default constructor for GlorpController.
	 * @throws LineUnavailableException 
	 */
	public GlorpController() throws LineUnavailableException{
		myMaze = Objects.requireNonNull(Maze.getInstance());
		myPlayer = Objects.requireNonNull(myMaze.getPlayer());
		myRiddleOpenFlag = false; // The riddle panel is not open when the game starts up

		// Set up the JFrame.
		myWindow = new GlorpGUI();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
        myWindow.setTitle("GLORP"); 
        myWindow.repaint();
        
        // Set up hashmap.
        myPositionChange.put(Direction.EAST, new Point(20, 200));
        myPositionChange.put(Direction.WEST, new Point(380, 200));
        myPositionChange.put(Direction.NORTH, new Point(200, 380));
        myPositionChange.put(Direction.SOUTH, new Point(200, 20));
        
        // Set up key bindings used for movement.
        String[] theDirections = {"LEFT", "RIGHT", "UP", "DOWN"};
        for(String key : theDirections) {
            addKeyActions(key);
        }
    }

	// Helper method checks for item, fixture, and door interactions after a key event.
    private void checkInteractions() {
    	checkItems(); // Check for item interactions.
    	checkFixtures(); // Check for fixture interactions.
    	
    	// Checks if we are near a door and need to open the riddle panel.
    	Direction inDir = checkDoorZones(); 
    	Door inCurrDoor = myMaze.getCurrRoom().getDoors().get(inDir); // Grab the current door.
    	
    	if(inDir != null) { // If we are near a door:
    	    // If valid to attempt to move in that direction.
    	    if(myMaze.isValidTraversal(inDir, myMaze.getCurrRoom())) { 
    	        // If the door is unlocked, move that direction and update map and player.
                if(inCurrDoor.isUnlocked()) { 
                    attemptMapTraversal(inDir); 
                // Check if riddle threads are not already open. 
                // If they are not, open producer and consumer threads to watch for riddle activity.
                }else if(!(myRiddleOpenFlag)) { 
                    myRiddleOpenFlag = true;
                    openRiddleThreads(inDir); 
                }
    	    }
    	}
	}
    
    // Helper method checks if we are in the vicinity of an item.
    private void checkItems() {
    	Item inItem = myMaze.getCurrRoom().getItem(); // Grab any items in the current room.
    	if(inItem != null && myPlayer.getIconArea().intersects(inItem.getIconArea())) { 
    		myPlayer.getInventory().add(myMaze.getCurrRoom().getItem().getType()); // Add item to player inventory.
    		myMaze.getCurrRoom().setItem(null); // Remove item from room inventory.
    		myMaze.getCurrRoom().setCurrentRoom(true); 
    		myWindow.getGlorpPanel().getItemView().update(myPlayer); // Add item to item panel.
    		SoundEffect.ITEM.play();
    	}
    }
    
    // Helper method checks if we are in the vicinity of a fixture. 
    private void checkFixtures() {
    	Fixture inFixture = myMaze.getCurrRoom().getFixture(); // Grab any fixtures in the current room.
    	if(inFixture != null && myPlayer.canInteract(inFixture) && myPlayer.getIconArea().intersects(inFixture.getInteractionZone())) {
    		
    		// If there is a Chest fixture and we have the Key item:
    		if(myPlayer.getInventory().contains(ItemType.KEY)  
    				&& inFixture.getType() == FixtureType.CHEST) { 
    			myPlayer.getInventory().remove(ItemType.KEY); // Remove key from player inventory.
    			myMaze.getCurrRoom().addItem(new Item(new PiecePoint(250,250), ItemType.GEM)); // Add Gem to player inventory. 
    			myWindow.getGlorpPanel().getItemView().update(myPlayer); // Update item panel.
    			
    			// Remove fixture from room.
    			inFixture.setBase(new Rectangle(new Dimension(0,0))); 
    			inFixture.setIconArea(new Rectangle(new Dimension(0,0))); 
    			inFixture.setInteractionZone(new Rectangle(new Dimension(0,0)));
    			inFixture.setMyYCoordinate(inFixture.getMyYCoordinate()-60);
    			inFixture.setMyXCoordinate(inFixture.getMyXCoordinate()-10);
    			
    			// Display explosion animation and sound.
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
    		
    		// If there is a Ship fixture for Ignignokt and we have the Gem item:
    		if(myPlayer.getInventory().contains(ItemType.GEM)  
    			&& inFixture.getType() == FixtureType.ALTSHIP){
    			inFixture.setIcon(new GameIcon("src/icons/alt_ship_win.png", 200, 275));
        		myPlayer.setCoordinate(new PiecePoint(150,175));
        		myPlayer.setRoomIcon(new GameIcon("src/icons/win_message_icon.png", 220, 150));
        		myPlayer.setFixed(true);
        		
        		// Play the win sequence and sound.
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
        		myPlayer.getInventory().clear(); // Update player inventory.
        		myWindow.getGlorpPanel().getItemView().update(myPlayer); // Update item panel.
    		}

    		// If there is a Ship fixture for Glorp and we have the Gem item:
    		if(myPlayer.getInventory().contains(ItemType.GEM) 
        		&& inFixture.getType() == FixtureType.SHIP){
        		inFixture.setIcon(new GameIcon("src/icons/ship_win.png", 200, 150));
        		myPlayer.setCoordinate(new PiecePoint(150,175));
        		myPlayer.setRoomIcon(new GameIcon("src/icons/win_message_icon.png", 220, 150));
        		myPlayer.setFixed(true);
        		
        		// Play the win sequence and sound.
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
        		myPlayer.getInventory().clear(); // Update player inventory. 
        		myWindow.getGlorpPanel().getItemView().update(myPlayer); // Update item panel.
    		}
    	}
    }

    // Returns the direction of the door the player is interescting with.
    // If the player is not interecting with a door, returns null.
    private Direction checkDoorZones() {       
        if(myPlayer.getIconArea().intersects(Room.getEastDoorZone())) return Direction.EAST;  // East door zone.
        else if(myPlayer.getIconArea().intersects(Room.getWestDoorZone())) return Direction.WEST; // West door zone.
        else if(myPlayer.getIconArea().intersects(Room.getNorthDoorZone())) return Direction.NORTH; // North door zone.
        else if(myPlayer.getIconArea().intersects(Room.getSouthDoorZone())) return Direction.SOUTH; // South door zone.
        else return null;
    }
    
    // Attempts to move the player to a new spot on the map.
    private void attemptMapTraversal(Direction theDirection) {
    	Objects.requireNonNull(theDirection);
        if(myMaze.isValidTraversal(theDirection, myMaze.getCurrRoom())) { // If the move is valid. 
        	Door currDoor = myMaze.getCurrRoom().getDoors().get(theDirection); // Grab the relevant Door.
        	if(currDoor.isUnlocked()) { // If the door is unlocked, move that direction.
				myMaze.traverseMaze(theDirection); 
				myPlayer.getCoordinate().setLocation(myPositionChange.get(theDirection)); 
				myPlayer.updateRectangles(); 				
        	} 
        }
    }
    
    // Open Producer and Consumer Threads for Riddle Panel.
    private void openRiddleThreads(Direction theDirection) {
    	Objects.requireNonNull(theDirection);
        Riddle currRiddle = myMaze.getCurrRoom().getDoors().get(theDirection).getMyRiddle(); // Open producer.        
        RiddlePanel inRiddlePanel = myWindow.getRunnableRiddlePanel(currRiddle); // Grab riddle panel.
        InputPanel inputPanel = inRiddlePanel.getInputPanel(); // Grab input panel for current riddle.
        Thread inConsumer = new RiddleConsumer(inRiddlePanel, inputPanel); // Open consumer.
        inConsumer.start();
    }
	
    // Private inner class for consumer thread.
    // Process riddle display and riddle response from the player. 
	private class RiddleConsumer extends Thread{
	   // The RiddlePanel is a JPanel that contains both question and answer panels.
		private RiddlePanel myRiddlePanel;
		// The InputPanel is the answer Panel. It is a JPanel that contains the panel for the current riddle.
	    private InputPanel myInputPanel;
	    
	    /**
	     * Creates the riddle consumer based on the RiddlePanel and InputPanels recieved. 
	     * RiddleConsumer is a private inner class inside GlorpController.
	     * @param thePanel The riddle panel for the GUI
	     * @param theInputPanel The input panel for the GUI
	     */
	    public RiddleConsumer(RiddlePanel thePanel, InputPanel theInputPanel) {
	        myRiddlePanel = Objects.requireNonNull(thePanel);
	        myInputPanel = Objects.requireNonNull(theInputPanel);
	    }
	    
	    // Returns true if the message response equals the answer in the riddle.
	    private boolean answerCorrect() {
	        boolean inCorrect = myRiddlePanel.getRiddle().verifyAnswer(myInputPanel.getResponse(myRiddlePanel.getRiddle()));
	        return inCorrect;
	    }

	    // 
	    // When player answers, processes their response to the question.
	    // Displays appropriate sphinx response based on players action. 
        @Override
        public void run() {
            int responseTime = SPHINX_RESPONSE_TIME;
          //while no message or the player still in door region: 
            while( (!myRiddlePanel.hasResponse()) && checkDoorZones() != null){          
                try {
                    RiddleConsumer.sleep(5); // Sleep the thread for 5 milliseconds if the player hasn't answered yet
                } catch (InterruptedException e) {
                    System.out.println("Error in GlorpController run method!");
                    e.printStackTrace();
                }
            }                
            Direction inDir = checkDoorZones();
            if(myRiddlePanel.hasResponse() && inDir != null) { // The player answered the question, check if answer was correct.
            	if(answerCorrect()) correctAnswerUpdate(inDir); // If player answers the question correctly, unlock the door.
            	else wrongAnswerUpdate(inDir, responseTime); // The player answered the question incorrectly, permanently block the door.
   
        	// The player ran away from the door, display sphinx resposne.
            } else myRiddlePanel.sphinxResponse(runawaySphinxResponse[RAND.nextInt(runawaySphinxResponse.length)]);

            // Sleep thread while the response is displayed.
            try {
                Thread.sleep(responseTime);
            } catch (InterruptedException e) {
                System.out.println("Error in GlorpController run method!");
                e.printStackTrace();
            }
                
            // terminate this thread & producer thread 
            myRiddlePanel.shutDown(); 
            myWindow.repaint();
            myRiddleOpenFlag = false;
        }
        
        // Helper method updates the model and view when the riddle answer is correct.
        private void correctAnswerUpdate(Direction theDirection) {
        	Objects.requireNonNull(theDirection);
        	myMaze.getCurrRoom().getDoors().get(theDirection).setUnlocked();
            attemptMapTraversal(theDirection);
            myRiddlePanel.sphinxResponse(correctSphinxResponse[RAND.nextInt(correctSphinxResponse.length)]); //change to be randomized
        }
        
        // Help method updates the model and view when the riddle answer is incorrect.
        private void wrongAnswerUpdate(Direction theDirection, int theResponseTime) {
			Objects.requireNonNull(theDirection);
			Objects.requireNonNull(theResponseTime);
			myMaze.getCurrRoom().getDoors().get(theDirection).setBlocked();
		    // If player no longer has a path to win, display the lose sequence.
		    if(!myMaze.canWin()) playLoseSequence();
		    theResponseTime = displayRiddleExplanation();
		    if(theResponseTime == SPHINX_RESPONSE_TIME) myRiddlePanel.sphinxResponse(incorrectSphinxResponse[RAND.nextInt(incorrectSphinxResponse.length)]);
        }
        
        // Helper method plays the lose sequence when the riddle answer is incorrect
        // and the player no longer has a path to win.
        private void playLoseSequence() {
         	myPlayer.setCoordinate(new PiecePoint(50,175));
    		myPlayer.setRoomIcon(new GameIcon("src/icons/trapped_message_icon.png", 400, 150));
    		myPlayer.setFixed(true); // Setting this to false triggers the lose animation.
    		Music.stop();
    		SoundEffect.LOSE.play();
        }
        
        // Helper method displays the explanation when the riddle answer is wrong.
        // This method is necessary because longer explanations are displayed for longer
        // so that the player has time to read the entire explanation.
        private int displayRiddleExplanation() {
            String explanation = myRiddlePanel.getRiddle().getExplanation();
            if(explanation.length() > 1) { 
    			myRiddlePanel.riddleExplanation(explanation);
            	if(explanation.length() > 60) return LONG_EXPLANATION_TIME;
            	 else return SHORT_EXPLANATION_TIME;
    		} else return SPHINX_RESPONSE_TIME;
    	} 
	}
	
	// Key Binding set up method and inner Action class.
	// Modified code of KeyBoardAnimation.java 
    // from https://tips4java.wordpress.com/2013/06/09/motion-using-the-keyboard/
    private void addKeyActions(String theKey){
    	Objects.requireNonNull(theKey);
    	
        //  Get the InputMap and ActionMap of the component.
        InputMap inputMap = myWindow.getRoomPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = myWindow.getRoomPanel().getActionMap();

        //  Create Action and add binding for the pressed key.
        Action pressedAction = new KeyBinder(theKey, true);
        String pressedKey = PRESSED + theKey;
        KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKey);
        inputMap.put(pressedKeyStroke, pressedKey);
        actionMap.put(pressedKey, pressedAction);

        //  Create Action and add binding for the released key.
        Action releasedAction = new KeyBinder(theKey, false);
        String releasedKey = RELEASED + theKey;
        KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKey);
        inputMap.put(releasedKeyStroke, releasedKey);
        actionMap.put(releasedKey, releasedAction);
    }
	
   // Private class for key bindings.
   private class KeyBinder extends AbstractAction implements Action {

	   	// Auto generated serialized ID used for serialization.
		private static final long serialVersionUID = 2272429193842620197L;
		
		// The key being pressed.
		private String myKey;
		
		// True if a new key is pressed for movement, false otherwise.
        private final boolean myAddFlag;
        
        // Private constructor creates the key binding.
        private KeyBinder(String theKey, boolean theAddFlag) {
            myKey = Objects.requireNonNull(theKey);
            myAddFlag = Objects.requireNonNull(theAddFlag);
        }
        
        /**
         * Perofms appropriate movement action for Player based on the keys pressed.
         * @param ActionEvent e The action event that moves the Player
         */
        @Override
        public void actionPerformed(ActionEvent theActionEvent) {
        	Objects.requireNonNull(theActionEvent);
            if(myAddFlag) { // If we have keys to add, add to pressedKeys.
            	addToPressedKeys(true);
	            Direction inDirection = Direction.generateDirection(myPressedKeys);
	            Direction validDirection = null;
	            
	            // Try to move the player.
	            try {
	                validDirection = myMaze.getCurrRoom().validateDirection(myPlayer, inDirection);
	                myPlayer.move(validDirection);
	            } catch (CloneNotSupportedException e1) {
	                e1.printStackTrace();
	            }
	            
	            checkInteractions();
	            myWindow.repaint();
	        }
            else addToPressedKeys(false); // Remove the key from pressedKeys
        }

        // If theAddFlag is true, add key otherwise remove key.
        private void addToPressedKeys( boolean theAddFlag) {
        	Objects.requireNonNull(theAddFlag);
            int inKey = -1;
            
            if(myKey.equals("LEFT")) inKey = KeyEvent.VK_LEFT;
            else if(myKey.equals("RIGHT")) inKey = KeyEvent.VK_RIGHT;
            else if(myKey.equals("UP")) inKey = KeyEvent.VK_UP; 
            else if(myKey.equals("DOWN")) inKey = KeyEvent.VK_DOWN;
        
            if(inKey != -1) {
                if(theAddFlag) myPressedKeys.add(inKey);
                else {
                    myPressedKeys.remove(inKey);
                    if(myPressedKeys.isEmpty()) { 
                      myPlayer.setStride(0);
                      myPlayer.setSkipFrame(false);
                    }
                    myWindow.repaint();
                }
            }    
        }
    }
}