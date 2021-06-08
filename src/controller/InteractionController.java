package controller;

import model.Door;
import model.Player;

public class InteractionController {
    
// methods moved here from room
    
//  /**
//   * Get the item from this room.
//   * @return the myItem
//   */
//  public GamePiece removeGamePiece(Point theCoordinates) throws IllegalArgumentException{ // pass in player coordinates? or player object?
//      //if not passing in GamePiece itself, create a dummy GamePiece 
//      GamePiece inDummy = new Player(); // doesnt really matter what implementation the GamePiece is
//      
//      for(GamePiece p : myGamePieces) {
//          if (((Player) p).compareTo(inDummy) == 0) { // will need to 
//              inDummy = p;
//          }
//      }
//      
//      if(!(myGamePieces.remove(inDummy))) { // if dummy was set to p, it will contain it and skip the below statement
//          throw new IllegalArgumentException("Error: no GamePiece at these coordinates.");
//      }
//      
//      return inDummy; 
//  }
    
//    /**
//  * Get the item from this room.
//  * @return Boolean indicator if the add was a success
//  */
// public Boolean addPlayer(Player thePlayer, Door theUnlockedDoor) throws IllegalArgumentException{ // pass in player coordinates? or player object?
//     Boolean inSuccess = false;
//     if(theUnlockedDoor.isUnlocked()) { //first check if unlockedDoor is even unlocked
//         for(Door d : myDoors) {
//             if(theUnlockedDoor.equals(d)) {
//                 
//                 inSuccess = true;
//             }
//         }
//     }
//     return inSuccess;
//    
// }
}
