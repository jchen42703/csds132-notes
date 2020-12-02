import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/** An example of a JavaFX Application with clickable buttons */
public class ButtonGUI extends Application {
 
  // The four buttons
  private Button button1;
  private Button button2;
  private Button button3;
  private Button button4;
  
  // Stores the amount we rotate the buttons in degrees
  private int rotation = 0;
        
  /**
   * Creates the JavaFX application with four buttons
   * @param primaryStage the main window
   */
  public void start(Stage primaryStage) {
    
    // layout the buttons in to 4 regions
    BorderPane layout = new BorderPane();
    
    // create the buttons
    button1 = new Button("click me");
    button2 = new Button("reset");
    button3 = new Button("spin");
    button4 = new Button("click me, too");
    
    // two buttons have a simple message that gets printed when clicked
    BasicClick handler = new BasicClick();
    button1.setOnAction(handler);
    button4.setOnAction(handler);
    
    // amount to rotate with each click
    final int rotateDelta = 27;
    
    // clicking button 3 rotates all four buttons
    button3.setOnAction(
      (e) -> {
        rotation += rotateDelta;
        button1.setRotate(rotation);
        button2.setRotate(rotation);
        button3.setRotate(rotation);
        button4.setRotate(rotation);
      }
    );
    
    // clicking button 4 resets the rotation of all buttons
    button2.setOnAction(this::reset);
    
    // add the buttons to the layout
    layout.setTop(button1);
    layout.setLeft(button2);
    layout.setRight(button3);
    layout.setBottom(button4);
    
    // add the scene to the stage/window
    Scene scene = new Scene(layout);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * Resets all the rotations of all the buttons 
   * @param e input is ignored
   */
  public void reset(ActionEvent e) {
    this.rotation = 0;
    button1.setRotate(0);
    button2.setRotate(0);
    button3.setRotate(0);
    button4.setRotate(0);
  }
  
  /**
   * A method to launch the JavaFX GUI
   * @param args the command line arguments
   */
  public static void launchApplication(String[] args) {
    Application.launch(args);
  }
  
  
  /**
   * Launch the JavaFX application
   * @param args the command line arguments are currently ignored
   */
  public static void main(String[] args) {
    Thread launchThread = new Thread() {
      public void run() {
        launchApplication(args);
      }
    };
    launchThread.start();
  }
  
  /** A class to handle a button click and print a message when it is clicked */
  private class BasicClick implements EventHandler<ActionEvent> {
    
    /**
     * Prints a simple message depending on the button
     * @param e info about the button click
     */
    public void handle(ActionEvent e) {
      Button b = (Button)e.getSource();
      if (b == button1)
        System.out.println("Ouch");
      else if (b == button4)
        System.out.println("Stop it!");
    }
  }
  
}