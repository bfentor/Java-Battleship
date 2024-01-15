/**
 * Purpose: A ship object which stores information about a ship on the Battleship board
 * 
 * @author Balazs
 * @version 1.0
 */

public class ShipPart extends ShipTemplate {
    public ShipPart(boolean destroyed, String type) {
        super(destroyed, type);
    }
    // toString
    public String toString() {
        return String.format("%b %s", isDestroyed(), getType());
    }
}