import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

public class Practice9 extends Application {

    @Override
    public void start(Stage stage) {
        ProgressBar bar = new ProgressBar(0.0);
        Button button = new Button();

        button.setOnAction(e -> {
            if (bar.getProgress() >= 1) {
                bar.setProgress(0.0);
            } else {
                bar.setProgress(bar.getProgress() + 0.01);
            }
            System.out.println(bar.getProgress());
        });

        BorderPane pane = new BorderPane();
        pane.setTop(bar);
        pane.setLeft(button);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
