import java.util.Scanner;

public class SistemaHospital {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaHospitalar sistema = new SistemaHospitalar();
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE CADASTRO HOSPITALAR ===");
            System.out.println("[1] Cadastrar paciente");
            System.out.println("[2] Consultar paciente");
            System.out.println("[3] Doencas cadastradas");
            System.out.println("[4] Numero de pacientes");
            System.out.println("[0] Sair");
            System.out.println("Escolha uma opcao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CADASTRO DE PACIENTE ---");
                    System.out.print("Nome do paciente: ");
                    String nome = sc.nextLine();

                    System.out.print("idade do paciente: ");
                    int idade = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Doenca do paciente: ");
                    String doenca = sc.nextLine();

                    sistema.cadastrarPacientes(nome, idade, doenca);
                    break;

                case 2:
                    System.out.println("\n--- CONSULTAR PACIENTE ---");
                    System.out.print("Digite o nome do paciente: ");
                    String nomeConsultar = sc.nextLine();
                    sistema.consultarPaciente(nomeConsultar);
                    break;

                case 3:

                    System.out.println("\n--- DOENÇAS CADASTRADAS ---");
                    sistema.listarDoencas();
                    break;

                case 4:
                    System.out.println("\n--- NUMERO DE PACIENTES ---");
                    sistema.exibirNumeroPacientes();
                    break;

                case 0:
                    System.out.println("encerrando o sistema...");
                    break;


                default:
                    System.out.println("Opçao invalida! Tente novamente. ");


            }
        } while (opcao != 0);

        sc.close();

    }

}

