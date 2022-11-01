
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
 * Class for Pacman modification
 */
class Pacman extends Pane implements EventListener {
   
   // attributes
   public int racePosX = 200; // x position of the racer
   public int racePosY = 550; // y position of the racer
   private int raceROT = 0; // x position of the racer
   public ImageView racerImageView;
   private Image pacmanImg = null;
   private Image pacmanImg2 = null;
   private Game game = null;

   // variables
   public double iconWidth = 0;
   public double iconHeight = 0;

   // Constructor
   public Pacman(Game game) {
      // Draw the icon for the racer
      this.game = game;
      try {
         pacmanImg = new Image(new FileInputStream("resources/pacman.gif"));
         racerImageView = new ImageView(pacmanImg);
      } catch (Exception e) {
         System.out.println("Exception: " + e);
         System.exit(0);
      }

      this.getChildren().add(racerImageView);
      // Get image size
      iconWidth = (int) pacmanImg.getWidth();
      iconHeight = (int) pacmanImg.getHeight();
   }

   /**
    * Changing Pacman Image 
    */
   public void changeImg() {

      this.getChildren().remove(racerImageView);
      try {
         pacmanImg2 = new Image(new FileInputStream("resources/pacmanLeft.gif"));
         racerImageView = new ImageView(pacmanImg2);
      } catch (Exception e) {
         System.out.println("Exception: " + e);
         System.exit(0);
      }

      this.getChildren().add(racerImageView);
   }

   /**
    * Changing Pacman Image to Original
    */
   public void firstImg() {

      this.getChildren().remove(racerImageView);
      try {
         pacmanImg2 = new Image(new FileInputStream("resources/pacman.gif"));
         racerImageView = new ImageView(pacmanImg2);
      } catch (Exception e) {
         System.out.println("Exception: " + e);
         System.exit(0);
      }

      this.getChildren().add(racerImageView);
   }

   /**
    * Method that updates Pacman Movement and Direction
    */
   public void update() {

      racerImageView.setTranslateX(racePosX);
      racerImageView.setTranslateY(racePosY);
      racerImageView.setRotate(raceROT);

   } // end update()

   // methods for movement
   public static final int MOVING_SPEED = 40;

   /**
    * Method that updates Pacman Movement and Direction to Up
    */
   public void goUP() {
      racePosY = racePosY - MOVING_SPEED;
      raceROT = 270;
      firstImg();
   }

   /**
    * Method that updates Pacman Movement and Direction to Down
    */
   public void goDOWN() {
      racePosY = racePosY + MOVING_SPEED;
      raceROT = 90;
      firstImg();
   }

   /**
    * Method that updates Pacman Movement and Direction to Left
    */
   public void goLEFT() {
      racePosX = racePosX - MOVING_SPEED;
      raceROT = 0;
      changeImg();
   }

   /**
    * Method that updates Pacman Movement and Direction to Right
    */
   public void goRIGHT() {
      racePosX = racePosX + MOVING_SPEED;
      raceROT = 0;
      firstImg();
   }

} // end inner class Racer
