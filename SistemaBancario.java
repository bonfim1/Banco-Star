import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Hierarquia de Clientes
abstract class Cliente {
    private String nome;
    private String identificador;

    public Cliente(String nome, String identificador) {
        this.nome = nome;
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public abstract void exibirDetalhes();
}

class PessoaFisica extends Cliente {
    private String cpf;
    private String rg;

    public PessoaFisica(String nome, String cpf, String rg) {
        super(nome, cpf);
        this.cpf = cpf;
        this.rg = rg;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("CPF: " + cpf);
        System.out.println("RG: " + rg);
    }
}

class PessoaJuridica extends Cliente {
    private String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome, cnpj);
        this.cnpj = cnpj;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("CNPJ: " + cnpj);
    }
}

// Hierarquia de Contas
interface ContaBancaria {
    void depositar(double valor);
    boolean sacar(double valor);
    double getSaldo();
    String getTipoConta();
    boolean permiteRendimento();
    void aplicarRendimento();
}

abstract class Conta implements ContaBancaria {
    protected Cliente cliente;
    protected double saldo;
    protected String numero;

    public Conta(Cliente cliente, String numero) {
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = 0;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) saldo += valor;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public boolean permiteRendimento() {
        return false;
    }

    @Override
    public void aplicarRendimento() {
        // Implementação padrão vazia
    }
}

class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(Cliente cliente, String numero) {
        this(cliente, numero, 0);
    }

    public ContaCorrente(Cliente cliente, String numero, double limite) {
        super(cliente, numero);
        this.limite = limite;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo + limite) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public String getTipoConta() {
        return "Corrente";
    }
}

class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente, String numero) {
        super(cliente, numero);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public String getTipoConta() {
        return "Poupança";
    }

    @Override
    public boolean permiteRendimento() {
        return true;
    }

    @Override
    public void aplicarRendimento() {
        saldo += saldo * 0.005; // 0.5%
    }
}

class ContaInvestimento extends Conta {
    public ContaInvestimento(Cliente cliente, String numero) {
        super(cliente, numero);
    }

    @Override
    public boolean sacar(double valor) {
        return false;
    }

    @Override
    public String getTipoConta() {
        return "Investimento";
    }

    @Override
    public boolean permiteRendimento() {
        return true;
    }

    @Override
    public void aplicarRendimento() {
        saldo += saldo * 0.01; // 1%
    }
}

// Sistema Bancário
public class SistemaBancario {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== SISTEMA BANCÁRIO ===");
            System.out.println("1. Clientes");
            System.out.println("2. Contas");
            System.out.println("3. Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuContas();
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuClientes() {
        while (true) {
            System.out.println("\n=== MENU CLIENTES ===");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("3. Remover");
            System.out.println("4. Voltar");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> removerCliente();
                case 4 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarCliente() {
        System.out.println("\nTipo de Cliente:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Documento (CPF/CNPJ): ");
        String doc = scanner.nextLine();

        Cliente cliente;
        if (tipo == 1) {
            System.out.print("RG: ");
            String rg = scanner.nextLine();
            cliente = new PessoaFisica(nome, doc, rg);
        } else {
            cliente = new PessoaJuridica(nome, doc);
        }

        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n=== CLIENTES ===");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
            clientes.get(i).exibirDetalhes();
            System.out.println("-------------------");
        }
    }

    private static void removerCliente() {
        listarClientes();
        System.out.print("\nNúmero do cliente a remover: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > clientes.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Cliente cliente = clientes.remove(num - 1);
        contas.removeIf(conta -> conta.getCliente().equals(cliente));
        System.out.println("Cliente removido com sucesso!");
    }

    private static void menuContas() {
        while (true) {
            System.out.println("\n=== MENU CONTAS ===");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("3. Operações");
            System.out.println("4. Voltar");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarConta();
                case 2 -> listarTodasContas();
                case 3 -> menuOperacoes();
                case 4 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarConta() {
        if (clientes.isEmpty()) {
            System.out.println("Cadastre um cliente primeiro!");
            return;
        }

        listarClientes();
        System.out.print("\nNúmero do cliente: ");
        int numCliente = scanner.nextInt();
        scanner.nextLine();

        if (numCliente < 1 || numCliente > clientes.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Cliente cliente = clientes.get(numCliente - 1);

        System.out.println("\nTipo de Conta:");
        System.out.println("1. Corrente");
        System.out.println("2. Poupança");
        System.out.println("3. Investimento");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();

        Conta conta;
        switch (tipo) {
            case 1 -> {
                System.out.print("Limite: ");
                double limite = scanner.nextDouble();
                conta = new ContaCorrente(cliente, numero, limite);
            }
            case 2 -> conta = new ContaPoupanca(cliente, numero);
            case 3 -> conta = new ContaInvestimento(cliente, numero);
            default -> {
                System.out.println("Tipo inválido!");
                return;
            }
        }

        contas.add(conta);
        System.out.println("Conta criada com sucesso!");
    }

    private static void listarTodasContas() {
        System.out.println("\n=== CONTAS ===");
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);
            System.out.println((i + 1) + ". " + conta.getTipoConta() + " - " + conta.getNumero());
            System.out.println("   Cliente: " + conta.getCliente().getNome());
            System.out.println("   Saldo: R$ " + conta.getSaldo());
            System.out.println("-------------------");
        }
    }

    private static void menuOperacoes() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada!");
            return;
        }

        listarTodasContas();
        System.out.print("\nNúmero da conta: ");
        int numConta = scanner.nextInt();
        scanner.nextLine();

        if (numConta < 1 || numConta > contas.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Conta conta = contas.get(numConta - 1);

        while (true) {
            System.out.println("\n=== OPERAÇÕES ===");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            if (conta.permiteRendimento()) {
                System.out.println("3. Aplicar Rendimento");
            }
            System.out.println("4. Voltar");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    conta.depositar(valor);
                    System.out.println("Depósito realizado!");
                }
                case 2 -> {
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    if (conta.sacar(valor)) {
                        System.out.println("Saque realizado!");
                    } else {
                        System.out.println("Saldo insuficiente!");
                    }
                }
                case 3 -> {
                    if (conta.permiteRendimento()) {
                        conta.aplicarRendimento();
                        System.out.println("Rendimento aplicado!");
                    }
                }
                case 4 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}