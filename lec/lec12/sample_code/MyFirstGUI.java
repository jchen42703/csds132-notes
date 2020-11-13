import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/** An example of a JavaFX application */
public class MyFirstGUI extends Application {
  
  /**
   * Creates the application.
   * @param primaryStage the main window
   */
  public void start(Stage primaryStage) {
    
    Button button = new Button("Click me");
    Scene scene = new Scene(button);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * Start the JavaFX application
   * @param args the command line arguments are currently ignored
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
}