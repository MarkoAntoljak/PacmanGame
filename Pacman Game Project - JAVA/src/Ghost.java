
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
 * Class for Ghost Modification
 */
class Ghost extends Pane implements EventListener {
   // attributes
   private int racePosX = 0; // x position of the racer
   private int racePosY = 0; // y position of the racer
   private int raceROT = 0; // x position of the racer
   public ImageView ghostImgView; // a view of the icon ... used to display and move the image
   private Image ghostImg = null;
   private Game game = null;

   private double ghostSpeed = 5;
   private double direction = 0;
   public double iconWidth = 0;
   public double iconHeight = 0;

   // Constructor
   public Ghost(Game game) {
      // Draw the icon for the racer
      this.game = game;
      try {
         ghostImg = new Image(new FileInputStream("resources/enemy_ghost.png"));
         ghostImgView = new ImageView(ghostImg);
      } catch (Exception e) {
         System.out.println("Exception: " + e);
         System.exit(0);
      }
      this.getChildren().add(ghostImgView);

      // Get image size
      iconWidth = (int) ghostImg.getWidth();
      iconHeight = (int) ghostImg.getHeight();
   }

   /**
    * Method That Updates the Ghost Position
    */
   public void update() {
      
      if (racePosX < 0) {
         racePosX = 0;
      }
      if (racePosY < 0) {
         racePosY = 0;
      }

      ghostImgView.setTranslateX(racePosX);
      ghostImgView.setTranslateY(racePosY);
      ghostImgView.setRotate(direction);

      
   } // end update()

} // end inner class Racer
