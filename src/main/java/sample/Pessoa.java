package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pessoa {

    String table_name = "Pessoa";
    Connection con;


    public void update(String name, String cpf, String date, char sexo) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();



        this.con.commit();
        this.con.close();
    }

    public String[] consultar(String cpf_input) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String FIND = "SELECT * FROM Pessoa WHERE CPF = " + cpf_input;
        PreparedStatement stmt = this.con.prepareStatement(FIND);
        ResultSet result = stmt.executeQuery();
        String[] resultado = new String[4];

        for(int i = 0; result.next(); i++) {
            resultado[i] = result.getString(i); //Adiciona no array de acordo com o index da coluna
        }

        this.con.commit();
        this.con.close();
        return resultado;
    }

    public void insert(String name, String cpf, String date, char sexo) throws SQLException {
        ConnectionDatabase connectionBD = new ConnectionDatabase();
        this.con = connectionBD.setConnection();

        final String INSERIR = "INSERT INTO Pessoa (CPF, Nome, Data_Nascimento, Sexo) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.con.prepareStatement(INSERIR);
        preparedStatement.setString(2, name);
        preparedStatement.setString(1, cpf);
        preparedStatement.setString(3, date);
        preparedStatement.setString(4, String.valueOf(sexo));
        preparedStatement.executeUpdate();

        this.con.commit();
        this.con.close();
    }
}