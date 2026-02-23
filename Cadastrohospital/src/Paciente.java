import java.util.ArrayList;
import java.util.Scanner;


public class Paciente {

    private String nome;
    private int idade;
    private String doenca;
    private static int totalPacientes = 0;

    public Paciente(String nome, int idade, String doenca) {
        this.nome = nome;
        this.idade = idade;
        this.doenca = doenca;
        totalPacientes ++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public static int getTotalPacientes() {
        return totalPacientes;
    }

    public static void setTotalPacientes(int totalPacientes) {
        Paciente.totalPacientes = totalPacientes;
    }

    @Override
    public String toString(){
        return  "Paciente: " + nome + "| Idade: " + idade + "| Doenca: " + doenca;
    }
}
