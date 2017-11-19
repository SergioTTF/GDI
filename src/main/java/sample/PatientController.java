package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML
    Button insertButton;
    @FXML
    Button consultButton;
    public void initialize(URL location, ResourceBundle resources) {
        insertButton.setOnAction(event -> {
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insert_layout.fxml"));
                    fxmlLoader.setController(new InsertController());
                    final Parent root = fxmlLoader.load();
                    Stage actualStage = (Stage) insertButton.getScene().getWindow();
                    actualStage.setScene(new Scene(root, 600, 400));
                    actualStage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        });
        consultButton.setOnAction(event -> {
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/consult_layout.fxml"));
                    fxmlLoader.setController(new ConsultController());
                    final Parent root = fxmlLoader.load();
                    Stage actualStage = (Stage) insertButton.getScene().getWindow();
                    actualStage.setScene(new Scene(root, 600, 400));
                    actualStage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        });
    }
}
