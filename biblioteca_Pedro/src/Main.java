import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        System.out.println("📚 SISTEMA DE BIBLIOTECA");
        System.out.println("Autor: Pedro Daniel Cardoso");



        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Adicionar novo livro");
            System.out.println("2. Listar todos os livros");
            System.out.println("3. Emprestar livro");
            System.out.println("4. Devolver livro");
            System.out.println("5. Buscar livro por título");
            System.out.println("6. Ver livros disponíveis");
            System.out.println("7. Ver livros emprestados");
            System.out.println("0. Sair do sistema");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o título do livro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Digite o nome do autor: ");
                        String autor = scanner.nextLine();
                        biblioteca.adicionarLivro(titulo, autor);
                        break;

                    case 2:
                        biblioteca.listarTodosLivros();
                        break;

                    case 3:
                        System.out.print("Digite o ID do livro para empréstimo: ");
                        int idEmprestar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o nome do usuário: ");
                        String usuario = scanner.nextLine();
                        biblioteca.emprestarLivro(idEmprestar, usuario);
                        break;

                    case 4:
                        System.out.print("Digite o ID do livro para devolução: ");
                        int idDevolver = scanner.nextInt();
                        biblioteca.devolverLivro(idDevolver);
                        break;

                    case 5:
                        System.out.print("Digite o título para busca: ");
                        String busca = scanner.nextLine();
                        biblioteca.buscarPorTitulo(busca);
                        break;

                    case 6:
                        biblioteca.listarLivrosDisponiveis();
                        break;

                    case 7:
                        biblioteca.listarLivrosEmprestados();
                        break;

                    case 0:
                        executando = false;
                        System.out.println("\nObrigado por usar o Sistema de Biblioteca!");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: entrada inválida!");
                scanner.nextLine(); // Limpar buffer
            }
        }

        scanner.close();
    }
}

// ============================================
// CLASSE LIVRO
// ============================================
class Livro {
    private static int contadorId = 1;

    private int id;
    private String titulo;
    private String autor;
    private boolean disponivel;
    private String usuarioEmprestimo;

    public Livro(String titulo, String autor) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
        this.usuarioEmprestimo = "";
    }

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponivel() { return disponivel; }
    public String getUsuarioEmprestimo() { return usuarioEmprestimo; }

    // Métodos
    public boolean emprestar(String usuario) {
        if (disponivel) {
            disponivel = false;
            usuarioEmprestimo = usuario;
            return true;
        }
        return false;
    }

    public boolean devolver() {
        if (!disponivel) {
            disponivel = true;
            usuarioEmprestimo = "";
            return true;
        }
        return false;
    }

    public void mostrarInformacoes() {
        String status = disponivel ? "✅ Disponível" : "⏳ Emprestado para: " + usuarioEmprestimo;
        System.out.println("ID: " + id + " | Título: " + titulo +
                " | Autor: " + autor + " | Status: " + status);
    }
}

// ============================================
// CLASSE BIBLIOTECA
// ============================================
class Biblioteca {
    private ArrayList<Livro> livros;

    public Biblioteca() {
        livros = new ArrayList<>();
    }

    // Adicionar um novo livro
    public void adicionarLivro(String titulo, String autor) {
        Livro novoLivro = new Livro(titulo, autor);
        livros.add(novoLivro);
        System.out.println("\n✅ Livro adicionado com sucesso!");
        System.out.println("ID: " + novoLivro.getId() + " | Título: " + titulo);
    }

    // Listar todos os livros
    public void listarTodosLivros() {
        System.out.println("\n📖 TODOS OS LIVROS CADASTRADOS:");
        System.out.println("Total: " + livros.size() + " livros");
        System.out.println("----------------------------------------");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado na biblioteca.");
        } else {
            for (Livro livro : livros) {
                livro.mostrarInformacoes();
            }
        }
    }

    // Emprestar um livro
    public void emprestarLivro(int id, String usuario) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                if (livro.emprestar(usuario)) {
                    System.out.println("\n✅ Livro emprestado com sucesso!");
                    System.out.println("Livro: " + livro.getTitulo());
                    System.out.println("Usuário: " + usuario);
                } else {
                    System.out.println("\n❌ Livro já está emprestado!");
                }
                return;
            }
        }
        System.out.println("\n❌ Livro não encontrado (ID: " + id + ")");
    }

    // Devolver um livro
    public void devolverLivro(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                if (livro.devolver()) {
                    System.out.println("\n✅ Livro devolvido com sucesso!");
                    System.out.println("Livro: " + livro.getTitulo());
                } else {
                    System.out.println("\n❌ Este livro já está disponível!");
                }
                return;
            }
        }
        System.out.println("\n❌ Livro não encontrado (ID: " + id + ")");
    }

    // Buscar livro por título
    public void buscarPorTitulo(String tituloBusca) {
        System.out.println("\n🔍 RESULTADOS DA BUSCA: '" + tituloBusca + "'");
        boolean encontrou = false;

        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(tituloBusca.toLowerCase())) {
                livro.mostrarInformacoes();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum livro encontrado com esse título.");
        }
    }

    // Listar apenas livros disponíveis
    public void listarLivrosDisponiveis() {
        System.out.println("\n✅ LIVROS DISPONÍVEIS PARA EMPRÉSTIMO:");
        int contador = 0;

        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                livro.mostrarInformacoes();
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("Não há livros disponíveis no momento.");
        } else {
            System.out.println("Total disponível: " + contador + " livro(s)");
        }
    }

    // Listar livros emprestados
    public void listarLivrosEmprestados() {
        System.out.println("\n⏳ LIVROS EMPRESTADOS:");
        int contador = 0;

        for (Livro livro : livros) {
            if (!livro.isDisponivel()) {
                livro.mostrarInformacoes();
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("Não há livros emprestados no momento.");
        } else {
            System.out.println("Total emprestado: " + contador + " livro(s)");
        }
    }

    // Método para obter livro por ID (útil para futuras expansões)
    public Livro buscarPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }
}