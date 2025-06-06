import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
    Jonathan Fessler
    Weather App Java Final Project
    4/28/2025
    VM: --module-path "C:\Users\jfessler1\OneDrive - Northeast Community College O365\Documents\ProgramminginJava\Source\javafx-sdk-23.0.2\lib" --add-modules=javafx.fxml,javafx.controls
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("WeatherAppFXML.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Weather App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
