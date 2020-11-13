import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;

/** A GUI with buttons arranged in a row */
public class MyWindow extends Application {
  
  /**
   * Create the GUI with three buttons arranged in a row
   * @param primaryStage the window of the GUI
   */
  public void start(Stage primaryStage) {
    
    Button button1 = new Button("button1");
    Button button2 = new Button("button2");
    Button button3 = new Button("button3");
    
    FlowPane layout = new FlowPane(button1, button2, button3);
    
    Scene scene = new Scene(layout);
    
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * Start the application
   * @param args the command line arguments are currently ignored.
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
}