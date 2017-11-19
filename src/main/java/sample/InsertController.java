package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.sql.Connection;
import java.sql.SQLException;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class InsertController implements Initializable {
    @FXML
    Button confirmButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField personName;
    @FXML
    TextField personCPF;
    @FXML
    DatePicker datePicker;
    @FXML
    ChoiceBox choiceBox;
    public void initialize(URL location, ResourceBundle resources) {
        addTextLimiter(personName, 50);
        addTextLimiter(personCPF, 14);

        confirmButton.setOnAction(event -> {
            String name = personName.getText();
            String CPF = personCPF.getText();
            String sex = choiceBox.getValue().toString();
            String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            Connection con;
            ConnectionDatabase driverCon = new ConnectionDatabase();
            con = driverCon.setConnection();

            if (con != null) {
                Pessoa pessoa = new Pessoa();
                try {
                    pessoa.insert(name, CPF, date, sex.charAt(0));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        });


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

    }
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
}
