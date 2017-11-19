package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static sample.InsertController.addTextLimiter;

public class ConsultController implements Initializable {
    @FXML
    Button cancelButton;
    @FXML
    Button consultButton;
    @FXML
    Button updateButton;
    @FXML
    Button deleteButton;
    @FXML
    TextField consultCPF;
    @FXML
    TextField personCPF;
    @FXML
    TextField personName;
    @FXML
    DatePicker datePicker;
    @FXML
    ChoiceBox choiceBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTextLimiter(personName, 50);
        addTextLimiter(personCPF, 14);
        addTextLimiter(consultCPF, 14);

        cancelButton.setOnAction(event -> {
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/patient_layout.fxml"));
                    fxmlLoader.setController(new PatientController());
                    final Parent root = fxmlLoader.load();
                    Stage actualStage = (Stage) cancelButton.getScene().getWindow();
                    actualStage.setScene(new Scene(root, 600, 400));
                    actualStage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        });

        consultButton.setOnAction(event -> {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            datePicker.setValue(LOCAL_DATE("01-05-2016"));
            choiceBox.setValue("M");
            updateButton.setDisable(false);
            deleteButton.setDisable(false);
        });
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
