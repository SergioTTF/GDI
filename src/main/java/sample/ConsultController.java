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
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
            String CPF = consultCPF.getText();
            Connection con;
            ConnectionDatabase driverCon = new ConnectionDatabase();
            con = driverCon.setConnection();

            if (con != null) {
                Pessoa pessoa = new Pessoa();
                try {
                    Person person = pessoa.consult(CPF);
                    if (person != null) {
                        showPerson(person);
                    }
                    updateButton.setDisable(false);
                    deleteButton.setDisable(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        });
        updateButton.setOnAction(event -> {
            String CPF = consultCPF.getText();
            String name = personName.getText();
            String sex = choiceBox.getValue().toString();
            java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());

            Connection con;
            ConnectionDatabase driverCon = new ConnectionDatabase();
            con = driverCon.setConnection();

            if (con != null) {
                Pessoa pessoa = new Pessoa();
                try {
                    pessoa.update(name, CPF, date, sex);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        });

        deleteButton.setOnAction( event -> {
            String CPF = consultCPF.getText();
            Connection con;
            ConnectionDatabase driverCon = new ConnectionDatabase();
            con = driverCon.setConnection();

            if (con != null) {
                Pessoa pessoa = new Pessoa();
                try {
                    pessoa.remove(CPF);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        });
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    public void showPerson(Person person) {
        Platform.runLater(() -> {
            try {

                personCPF.setText(person.getCPF());
                personName.setText(person.getNome());
                datePicker.setValue(LOCAL_DATE(person.getData().toString()));
                choiceBox.setValue(person.getSexo());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
