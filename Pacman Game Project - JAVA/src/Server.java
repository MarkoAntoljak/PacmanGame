
/**
 *  Custom Pacman Game Project
 * .java
 * @author Marko Antoljak
 */

import java.io.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.net.*;

/**
 * Server Class
 */
public class Server extends Application implements EventHandler<ActionEvent> {
    // Window Attributes
    private Stage stage;
    private Scene scene;
    private VBox root = null;
    TextArea taServer = new TextArea();

    // socket
    public static final int SERVER_PORT = 1234;
    public static final String IP_ADDRESS = "localhost";
    // List<ObjectOutputStream> nameOfWriters = new ArrayList<>();

    // variables
    int clientIDCounter = 0;

    /**
     * Main Program
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Init Method
     * 
     * @param _stage
     */
    public void start(Stage _stage) {

        // inital setting and sizing
        stage = _stage;
        stage.setTitle("SERVER side");
        final int WIDTH = 450;
        final int HEIGHT = 400;
        final int X = 200;
        final int Y = 100;
        stage.setX(X);
        stage.setY(Y);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent evt) {
                System.exit(0);
            }
        });

        // Set the scene and show the stage
        root = new VBox();
        taServer.setDisable(true);
        root.getChildren().addAll(taServer);
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();

        doServerStuff();
    }

    /**
     * Starts Thread That Runs Server
     */
    private void doServerStuff() {
        ServerThread st = new ServerThread();
        st.start();

        // do on a main thread
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                taServer.appendText("Starting Server on port " + SERVER_PORT + "...\n");
            }
        });
    }

    /**
     * Inner Class for Main Thread Handling
     */
    class ServerThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("Openning SOCKET PORT");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        taServer.appendText("Waiting client to connect...\n");
                    }

                });
                ServerSocket sSocket = new ServerSocket(SERVER_PORT);

                //waiting for multiple connections all the time
                while (true) {
                    Socket cSocket = sSocket.accept();
                    ClientThread cT = new ClientThread(cSocket);
                    cT.start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inner Class Handles Client
     */
    class ClientThread extends Thread {

        // attributes
        private Socket cSocket;
        private ObjectOutputStream oos = null;
        private ObjectInputStream ois = null;

        // Constructor
        public ClientThread(Socket cSocket) {
            this.cSocket = cSocket;
        }

        // sending objects to the server and receiving objects from the server 
        @Override
        public void run() {
            try {
                this.ois = new ObjectInputStream(this.cSocket.getInputStream());
                this.oos = new ObjectOutputStream(this.cSocket.getOutputStream());

                // read client name input and append to server text area
                String clientName = ois.readUTF();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        taServer.appendText(clientName + " player connected\n");
                        clientIDCounter++;
                        taServer.appendText("Number of players connected: " + clientIDCounter);
                    }
                });
                oos.writeUTF(clientName + " - Connection successful!\n");
                oos.flush();

                while (true) {
                    try {
                        // reading command...
                        Object object = ois.readObject();

                        // cases
                        switch ((String) object) {
                            case "CHAT":
                                // read sent message
                                String message = ois.readUTF();
                                System.out.println(message);
                                // send message back to client
                                oos.writeUTF(message);
                                oos.flush();
                                break;

                            default:
                                break;
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Exception Alert
     * 
     * @param type
     * @param message
     */
    public void showAlert(AlertType type, String message) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(type, message);
                alert.showAndWait();
            }
        });
    }

    /**
     * @param arg0
     * Handle Method to Avoid Errors (program didn't compile without this method)
     */
    @Override
    public void handle(ActionEvent arg0) {
    }
}
