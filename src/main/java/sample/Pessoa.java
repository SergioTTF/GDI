package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Pessoa {

    String table_name = "Pessoa";
    Connection con;


    public void insert(String name, String cpf_input, java.sql.Date date, String sexo) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String CHECK = "SELECT * FROM Pessoa WHERE CPF = '" + cpf_input + "'";
        PreparedStatement check = this.con.prepareStatement(CHECK);
        ResultSet resCheck = check.executeQuery();

        if (resCheck != null) {
            final String INSERIR = "INSERT INTO Pessoa (CPF, Nome, Data_Nascimento, Sexo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = this.con.prepareStatement(INSERIR);
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            stmt.setString(2, name);
            stmt.setString(1, cpf_input);
            stmt.setDate(3, date);
            stmt.setString(4, sexo);
            stmt.executeUpdate();

            final String INSERIR2 = "INSERT INTO Paciente (CPF_Paciente) VALUES (?)";
            PreparedStatement stmt2 = this.con.prepareStatement(INSERIR2);
            stmt2.setString(1, cpf_input);
            stmt2.executeUpdate();
        }

        this.con.commit();
        this.con.close();
    }

    public void remove(String cpf_input) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String CHECK = "SELECT * FROM Pessoa WHERE CPF = '" + cpf_input + "'";
        PreparedStatement check = this.con.prepareStatement(CHECK);
        ResultSet resCheck = check.executeQuery();

        if (resCheck != null) {
            final String REMOVE2 = "DELETE FROM Paciente WHERE CPF_Paciente = '" + cpf_input + "'";
            PreparedStatement stmt2 = this.con.prepareStatement(REMOVE2);
            stmt2.executeUpdate();

            final String REMOVE = "DELETE FROM Pessoa WHERE CPF = '" + cpf_input + "'";
            PreparedStatement stmt = this.con.prepareStatement(REMOVE);
            stmt.executeUpdate();
        }

        this.con.commit();
        this.con.close();
    }
    public Person consult(String cpf_input) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String CHECK = "SELECT * FROM Pessoa WHERE CPF = '" + cpf_input + "'";
        PreparedStatement check = this.con.prepareStatement(CHECK);
        ResultSet resCheck = check.executeQuery();
        Person resultado = null;
        boolean resCheckResult = resCheck.next();

        if (resCheckResult) {
            resultado = new Person(resCheck.getString(1), resCheck.getString(2), resCheck.getDate(3), resCheck.getString(4));
        }
        this.con.commit();
        this.con.close();
        return resultado;
    }


    public void update(String name, String cpf_input, java.sql.Date date, String sexo) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String CHECK = "SELECT * FROM Pessoa WHERE CPF = '" + cpf_input + "'";
        PreparedStatement check = this.con.prepareStatement(CHECK);
        ResultSet resCheck = check.executeQuery();

        if (resCheck != null) {
            final String UPDATER = "UPDATE Pessoa SET Nome = '" + name + "', Data_Nascimento = ?, Sexo = '" + sexo + "' WHERE CPF = '" + cpf_input + "'";
            PreparedStatement stmt = this.con.prepareStatement(UPDATER);
            stmt.setDate(1, date);

            stmt.executeUpdate();
        }

        this.con.commit();
        this.con.close();
    }

}