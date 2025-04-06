import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class CadastroCliente {
    private String nome;
    private String identificador;

    public CadastroCliente(String nome, String identificador) {
        this.nome = nome;
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificador() {
        return identificador;
    }
    
    // Método abstrato para exibir detalhes específicos
    public abstract void exibirDetalhes();
}

class PessoaFisica extends CadastroCliente {
    private String cpf;
    private String rg;

    public PessoaFisica(String nome, String identificador, String cpf, String rg) {
        super(nome, identificador);
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("CPF: " + cpf);
        System.out.println("RG: " + rg);
    }
}

class PessoaJuridica extends CadastroCliente {
    private String cnpj;

    public PessoaJuridica(String nome, String identificador, String cnpj) {
        super(nome, identificador);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("CNPJ: " + cnpj);
    }
}

public class Main {
    private static List<CadastroCliente> clientes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar Pessoa Física");
            System.out.println("2. Cadastrar Pessoa Jurídica");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    cadastrarPessoaFisica();
                    break;
                case 2:
                    cadastrarPessoaJuridica();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarPessoaFisica() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Identificador: ");
        String identificador = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();

        CadastroCliente cliente = new PessoaFisica(nome, identificador, cpf, rg);
        clientes.add(cliente);
        System.out.println("Pessoa Física cadastrada com sucesso!");
    }

    private static void cadastrarPessoaJuridica() {
        System.out.print("Nome/Razão Social: ");
        String nome = scanner.nextLine();
        System.out.print("Identificador: ");
        String identificador = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        CadastroCliente cliente = new PessoaJuridica(nome, identificador, cnpj);
        clientes.add(cliente);
        System.out.println("Pessoa Jurídica cadastrada com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("Lista de Clientes:");
        for (CadastroCliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Identificador: " + cliente.getIdentificador());
            cliente.exibirDetalhes(); // Método polimórfico
            System.out.println("-------------------------");
        }
    }
}