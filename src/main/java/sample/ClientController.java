package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable{
    @FXML
    Button connectButton;
    public void initialize(URL location, ResourceBundle resources) {
        connectButton.setOnAction(event -> {
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/patient_layout.fxml"));
                    fxmlLoader.setController(new PatientController());
                    final Parent root = fxmlLoader.load();
                    Stage actualStage = (Stage) connectButton.getScene().getWindow();
                    actualStage.setScene(new Scene(root, 600, 400));
                    actualStage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        });
    }
}
