package model;
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
	
	public Room() {
		createDoors();
		myItem = null;
		containsPlayer = false;
		visitedFlag = false;
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
	
	
	
}
