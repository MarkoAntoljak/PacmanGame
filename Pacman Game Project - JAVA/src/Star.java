
/**
 *  Custom Pacman Game Project
 * .java
 * @author Marko Antoljak
 */

import java.io.FileInputStream;
import java.util.EventListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Class for SUPERpower Star Modification
 */
class Star extends Pane implements EventListener {

    // attributes
    private int posX = 0; // x position of the star
    private int posY = 0; // y position of the star
    public ImageView starImageView; // a view of the icon ... used to display and move the image
    private Image starImg = null;

    // variables
    public double iconWidth = 0;
    public double iconHeight = 0;
    private int raceROT = 0;

    // Constructor
    public Star() {
        // Draw the icon for the racer
        try {
            starImg = new Image(new FileInputStream("resources/star.png"));
            starImageView = new ImageView(starImg);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.exit(0);
        }
        this.getChildren().add(starImageView);

        // Get image size
        iconWidth = (int) starImg.getWidth();
        iconHeight = (int) starImg.getHeight();
    }

    /**
    * Method that updates Star(SUPERpower) Movement and Direction
    */
    public void update() {
        starImageView.setTranslateX(posX);
        starImageView.setTranslateY(posY);
        starImageView.setRotate(raceROT);
        raceROT += 0.5;

    } // end update()

} // end inner class Racer
