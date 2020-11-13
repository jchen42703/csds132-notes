import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/** A GUI with buttons arranged in a row */
public class MyWindow extends Application {
  
  // fields storing the buttons of the GUI
  private Button button1;
  private Button button2;
  private Button button3;
  
  /**
   * Create the GUI with three buttons arranged in a row
   * @param primaryStage the window of the GUI
   */
  public void start(Stage primaryStage) {
    
    button1 = new Button("button1");
    button2 = new Button("click count: 0");
    button3 = new Button("button3");
    
    HandleClicks eventHandler = new HandleClicks();
    button1.setOnAction(eventHandler);
    button3.setOnAction(eventHandler);
      
    FlowPane layout = new FlowPane(button1, button2, button3);
    
    button2.setOnAction(new EventHandler<ActionEvent>() {
      // counts the number of times a button is clicked
      private int clickCount = 0;
      
      public void handle(ActionEvent e) {
        button2.setText("click count: " + (++clickCount));
      }
    });
        
    Scene scene = new Scene(layout);
    
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /** 
   * An Event Handler that identifies the left button from the right button
   */
  private class HandleClicks implements EventHandler<ActionEvent> {
    
    /** 
     * On a button click identify if this button is the left or right button or another one
     */
    public void handle(ActionEvent e) {
      Button b = (Button)e.getSource();
      
      if (b == button1)
        System.out.println("You clicked the left button");
      else if (b == button3)
        System.out.println("You clicked the right button");
      else
        System.out.println("You clicked some other button");
    }
  }
  
  /**
   * Start the application
   * @param args the command line arguments are currently ignored.
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
}