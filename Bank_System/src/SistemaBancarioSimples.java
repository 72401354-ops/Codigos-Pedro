import java.util.ArrayList;
import java.util.Scanner;

public class SistemaBancarioSimples {

    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA BANCÁRIO SIMPLES ===\n");

        // Criar alguns dados de exemplo
        banco.criarDadosExemplo();

        boolean executando = true;

        while (executando) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Criar conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Ver saldo");
            System.out.println("5. Ver todas as contas");
            System.out.println("6. Transferir entre contas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Tipo (1-Corrente, 2-Poupança): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    Conta novaConta;
                    if (tipo == 1) {
                        System.out.print("Limite do cheque especial: ");
                        double limite = scanner.nextDouble();
                        scanner.nextLine();
                        novaConta = banco.criarContaCorrente(nome, cpf, limite);
                    } else {
                        novaConta = banco.criarContaPoupanca(nome, cpf);
                    }

                    System.out.println("Conta criada! Número: " + novaConta.numero);
                    break;

                case 2:
                    System.out.print("Número da conta: ");
                    String numDeposito = scanner.nextLine();
                    System.out.print("Valor para depositar: ");
                    double valorDeposito = scanner.nextDouble();
                    scanner.nextLine();

                    if (banco.depositar(numDeposito, valorDeposito)) {
                        System.out.println("Depósito realizado com sucesso!");
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("Número da conta: ");
                    String numSaque = scanner.nextLine();
                    System.out.print("Valor para sacar: ");
                    double valorSaque = scanner.nextDouble();
                    scanner.nextLine();

                    if (banco.sacar(numSaque, valorSaque)) {
                        System.out.println("Saque realizado com sucesso!");
                    } else {
                        System.out.println("Erro: conta não encontrada ou saldo insuficiente.");
                    }
                    break;

                case 4:
                    System.out.print("Número da conta: ");
                    String numSaldo = scanner.nextLine();
                    banco.verSaldo(numSaldo);
                    break;

                case 5:
                    banco.listarTodasContas();
                    break;

                case 6:
                    System.out.print("Conta de origem: ");
                    String origem = scanner.nextLine();
                    System.out.print("Conta de destino: ");
                    String destino = scanner.nextLine();
                    System.out.print("Valor: ");
                    double valorTransf = scanner.nextDouble();
                    scanner.nextLine();

                    if (banco.transferir(origem, destino, valorTransf)) {
                        System.out.println("Transferência realizada!");
                    } else {
                        System.out.println("Erro na transferência.");
                    }
                    break;

                case 0:
                    executando = false;
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}

// CLASSE CONTA (pai)
class Conta {
    String numero;
    String clienteNome;
    String clienteCpf;
    double saldo;

    // Construtor
    Conta(String clienteNome, String clienteCpf) {
        this.numero = gerarNumeroConta();
        this.clienteNome = clienteNome;
        this.clienteCpf = clienteCpf;
        this.saldo = 0.0;
    }

    private String gerarNumeroConta() {
        return "C" + (int)(Math.random() * 1000000);
    }

    void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depositado: R$ " + valor);
        } else {
            System.out.println("Valor inválido!");
        }
    }

    boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Sacado: R$ " + valor);
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido!");
            return false;
        }
    }

    void verSaldo() {
        System.out.println("Saldo da conta " + numero + ": R$ " + saldo);
    }
}

// CLASSE CONTA CORRENTE (filha de Conta)
class ContaCorrente extends Conta {
    double limiteChequeEspecial;

    ContaCorrente(String clienteNome, String clienteCpf, double limiteChequeEspecial) {
        super(clienteNome, clienteCpf);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    boolean sacar(double valor) {
        double saldoDisponivel = saldo + limiteChequeEspecial;

        if (valor > 0 && valor <= saldoDisponivel) {
            saldo -= valor;
            System.out.println("Sacado: R$ " + valor);

            if (saldo < 0) {
                System.out.println("Atenção: usando cheque especial!");
            }
            return true;
        } else {
            System.out.println("Saldo + limite insuficiente!");
            return false;
        }
    }

    @Override
    void verSaldo() {
        super.verSaldo();
        System.out.println("Limite cheque especial: R$ " + limiteChequeEspecial);
        System.out.println("Saldo disponível: R$ " + (saldo + limiteChequeEspecial));
    }
}

// CLASSE CONTA POUPANÇA (filha de Conta)
class ContaPoupanca extends Conta {
    double taxaRendimento;

    ContaPoupanca(String clienteNome, String clienteCpf) {
        super(clienteNome, clienteCpf);
        this.taxaRendimento = 0.005; // 0.5% ao mês
    }

    void aplicarRendimento() {
        double rendimento = saldo * taxaRendimento;
        saldo += rendimento;
        System.out.println("Rendimento aplicado: R$ " + rendimento);
    }

    @Override
    void verSaldo() {
        super.verSaldo();
        System.out.println("Taxa de rendimento: " + (taxaRendimento * 100) + "% ao mês");
    }
}

// CLASSE BANCO (gerencia todas as contas)
class Banco {
    ArrayList<Conta> contas;

    Banco() {
        contas = new ArrayList<>();
        System.out.println("Banco inicializado!");
    }

    // Método para criar dados de exemplo
    void criarDadosExemplo() {
        ContaCorrente cc1 = new ContaCorrente("João Silva", "111.222.333-44", 1000.0);
        cc1.depositar(500.0);
        contas.add(cc1);

        ContaPoupanca cp1 = new ContaPoupanca("Maria Santos", "555.666.777-88");
        cp1.depositar(1000.0);
        contas.add(cp1);

        ContaCorrente cc2 = new ContaCorrente("Carlos Oliveira", "999.888.777-66", 500.0);
        cc2.depositar(300.0);
        contas.add(cc2);

        System.out.println("Dados de exemplo criados!");
    }

    ContaCorrente criarContaCorrente(String nome, String cpf, double limite) {
        ContaCorrente novaConta = new ContaCorrente(nome, cpf, limite);
        contas.add(novaConta);
        return novaConta;
    }

    ContaPoupanca criarContaPoupanca(String nome, String cpf) {
        ContaPoupanca novaConta = new ContaPoupanca(nome, cpf);
        contas.add(novaConta);
        return novaConta;
    }

    boolean depositar(String numeroConta, double valor) {
        Conta conta = buscarConta(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
            return true;
        }
        return false;
    }

    boolean sacar(String numeroConta, double valor) {
        Conta conta = buscarConta(numeroConta);
        if (conta != null) {
            return conta.sacar(valor);
        }
        return false;
    }

    void verSaldo(String numeroConta) {
        Conta conta = buscarConta(numeroConta);
        if (conta != null) {
            conta.verSaldo();
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    boolean transferir(String contaOrigem, String contaDestino, double valor) {
        Conta origem = buscarConta(contaOrigem);
        Conta destino = buscarConta(contaDestino);

        if (origem != null && destino != null) {
            if (origem.sacar(valor)) {
                destino.depositar(valor);
                System.out.println("Transferência de R$ " + valor +
                        " de " + contaOrigem + " para " + contaDestino);
                return true;
            }
        }
        return false;
    }

    void listarTodasContas() {
        System.out.println("\n=== LISTA DE TODAS AS CONTAS ===");

        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        for (Conta conta : contas) {
            System.out.println("\n-------------------------");
            System.out.println("Número: " + conta.numero);
            System.out.println("Cliente: " + conta.clienteNome);
            System.out.println("CPF: " + conta.clienteCpf);
            System.out.println("Saldo: R$ " + conta.saldo);

            if (conta instanceof ContaCorrente) {
                System.out.println("Tipo: Conta Corrente");
                System.out.println("Limite: R$ " + ((ContaCorrente) conta).limiteChequeEspecial);
            } else if (conta instanceof ContaPoupanca) {
                System.out.println("Tipo: Conta Poupança");
                System.out.println("Rendimento: " + (((ContaPoupanca) conta).taxaRendimento * 100) + "%");
            }
        }
    }

    // Método auxiliar para buscar conta
    private Conta buscarConta(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.numero.equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }
}