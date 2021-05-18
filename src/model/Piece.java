/// trash this --> replaced with gamepiece

package model;

import java.awt.Point;

public interface Piece{
    /**
     * Return the coordinates of this object
     * @return
     */
    public Point getCoordinate(); // is Point mutable? 
    
   // public int canInteract(Point theCoordinate); // is the coordinate in the vacinity 
   // public int canInteract(Piece thePiece);
}
