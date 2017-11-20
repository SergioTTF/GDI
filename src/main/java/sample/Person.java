package sample;

import java.sql.Date;

public class Person {
    String CPF;
    String Nome;
    Date data;
    String Sexo;

    public Person(String cpf, String nome, Date date, String sex) {
        this.CPF = cpf;
        this.Nome = nome;
        this.data = date;
        this.Sexo = sex;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getData() {
        return data;
    }

    public String getNome() {
        return Nome;
    }

    public String getSexo() {
        return Sexo;
    }
}