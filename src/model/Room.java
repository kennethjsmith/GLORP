package model;

import javax.swing.ImageIcon;

/**
 * A room can has 4 doors an item, and contains flags indicating 
 * player presence and whether or not the room has been visited.
 * @author
 * @version
 */
//TODO: create subclasses (start and end rooms)
public class Room {
	//Map<Door, Room> myDoors= new TreeMap<>();
	
	private Door[] myDoors;
	private Item myItem;
	private boolean containsPlayer;
	private boolean visitedFlag;
	private static ImageIcon ROOM_ICON = new ImageIcon("src/icons/room_for_map.png");
	boolean isWinRoom;

	
	public Room() {
		createDoors();
		myItem = null;
		containsPlayer = false;
		visitedFlag = false;
		isWinRoom = false;
	}
	
	private void createDoors() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Get the item from this room.
	 * @return the myItem
	 */
	public Item getMyItem() {
		return myItem;
	}

	/**
	 * Place an item in this room
	 * @param myItem the myItem to set
	 */
	public void setMyItem(Item theItem) {
		myItem = theItem;
	}

	public void setVisitedFlag() {
		visitedFlag = true;
	}
	
	public boolean isVisited() {
		return visitedFlag;
	}
	
	public boolean containsPlayer() {
		return containsPlayer;
	}
	
	public String toString() {
		if(containsPlayer) return "true";
		else return "false";
	}
	
	public ImageIcon getSmallIcon() {
		return	ROOM_ICON;

	}
	
	public boolean isWinRoom() {
		return true;
	}
	
	public void setWinRoom() {
		isWinRoom = true;
	}
	
	
	
}
