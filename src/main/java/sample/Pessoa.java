package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pessoa {

    String table_name = "Pessoa";
    Connection con;


    public void insert(String name, String cpf_input, String date, char sexo) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String CHECK = "SELECT * FROM Pessoa WHERE CPF = '" + cpf_input + "'";
        PreparedStatement check = this.con.prepareStatement(CHECK);
        ResultSet resCheck = check.executeQuery();

        if (resCheck != null) {
            final String INSERIR = "INSERT INTO Pessoa (CPF, Nome, Data_Nascimento, Sexo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = this.con.prepareStatement(INSERIR);
            stmt.setString(2, name);
            stmt.setString(1, cpf_input);
            stmt.setString(3, date);
            stmt.setString(4, String.valueOf(sexo));
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
            final String REMOVE = "DELETE FROM Pessoa WHERE CPF = '" + cpf_input + "'";
            PreparedStatement stmt = this.con.prepareStatement(REMOVE);
            stmt.executeUpdate();
        }

        this.con.commit();
        this.con.close();
    }

    public String[] consult(String cpf_input) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String CHECK = "SELECT * FROM Pessoa WHERE CPF = '" + cpf_input + "'";
        PreparedStatement check = this.con.prepareStatement(CHECK);
        ResultSet resCheck = check.executeQuery();
        String[] resultado = null;

        if (resCheck != null) {
            final String FIND = "SELECT * FROM Pessoa WHERE CPF = '" + cpf_input + "'";
            PreparedStatement stmt = this.con.prepareStatement(FIND);
            ResultSet result = stmt.executeQuery();
            resultado = new String[5];

            for (int i = 1; result.next(); i++) {
                resultado[i] = result.getString(i); //Adiciona no array de acordo com o index da coluna
            }
        }
        this.con.commit();
        this.con.close();
        return resultado;
    }

    public void update(String name, String cpf_input, String date, char sexo) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String CHECK = "SELECT * FROM Pessoa WHERE CPF = '" + cpf_input + "'";
        PreparedStatement check = this.con.prepareStatement(CHECK);
        ResultSet resCheck = check.executeQuery();

        if (resCheck != null) {
            final String UPDATER = "UPDATE Pessoa SET Nome = '" + name + "', Data_Nascimento = '" + date + "', Sexo = '" + sexo + "' WHERE CPF_Pessoa = '" + cpf_input + "'";
            PreparedStatement stmt = this.con.prepareStatement(UPDATER);
            stmt.executeUpdate();
        }

        this.con.commit();
        this.con.close();
    }

}