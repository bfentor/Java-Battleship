/**
 * Purpose: A template ship object
 * 
 * @author Balazs
 * @version 1.0
 */

public class ShipTemplate {
    private boolean isDestroyed;
    private boolean isHorizontal;
    private String myType;
    private int xLoc;
    private int yLoc;
    
    public ShipTemplate(boolean destroyed, String type) {
        isDestroyed = destroyed;
        myType = type;
    }
    
    // getter methods
    public boolean isDestroyed() {
        return isDestroyed;
    }
    public String getType() {
        return myType;
    }
    
    // setter methods
    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
}