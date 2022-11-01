/**
 *  Custom Pacman Game Project
 * .java
 * @author Marko Antoljak
 */

import java.io.Serializable;
/**
 * Class for Getting Status
 * - didn't use it in the end
 */
public class Status implements Serializable {

    // attributes
    private int ID;
    private int sliderStatus;

    // Constructor
    public Status(int id, int sliderStatus) {
        this.ID = id;
        this.sliderStatus = sliderStatus;
    }
    
    /** 
     * Getter for ID
     * @return int
     */
    int getID() {
        return ID;
    }

    /** 
     * Getter for Slider Status
     * @return int
     */
    int getSliderStatus() {
        return sliderStatus;
    }


}