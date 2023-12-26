/**
 * Purpose: A ship object which stores information about a ship on the Battleship board
 * 
 * @author Balazs
 * @version 1.0
 */

public class Ship extends ShipTemplate {
    private boolean isHorizontal;
    private String myType;
    private int numParts;
    private int xLoc;
    private int yLoc;
    
    public Ship(boolean destroyed, boolean horizontal, String type, int num, int x, int y) {
        super(destroyed, type);
        isHorizontal = horizontal;
        numParts = num;
        xLoc = x;
        yLoc = y;
    }
    
    // getter methods
    public boolean isHorizontal() {
        return isHorizontal;
    }
    public int getNum() {
        return numParts;
    }
    public int getX() {
        return xLoc;
    }
    public int getY() {
        return yLoc;
    }
    
    // toString
    public String toString() {
        return String.format("%-6b %-6b %-12s %-2d %-2d", isDestroyed(), isHorizontal, getType(), getX(), getY());
    }
}