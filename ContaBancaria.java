public interface ContaBancaria {
    void depositar(double valor);
    boolean sacar(double valor);
    double getSaldo();
    String getTipoConta();
}

abstract class Conta implements ContaBancaria {
    protected CadastroCliente cliente;
    protected double saldo;
    protected String numeroConta;

    public Conta(CadastroCliente cliente, String numeroConta) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.saldo = 0;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public CadastroCliente getCliente() {
        return cliente;
    }
}


class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(CadastroCliente cliente, String numeroConta) {
        this(cliente, numeroConta, 0); 
    }

    public ContaCorrente(CadastroCliente cliente, String numeroConta, double limite) {
        super(cliente, numeroConta);
        this.limite = limite;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && (saldo + limite) >= valor) {
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
    public ContaPoupanca(CadastroCliente cliente, String numeroConta) {
        super(cliente, numeroConta);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public void render(double taxa) {
        saldo += saldo * taxa;
    }

    @Override
    public String getTipoConta() {
        return "Poupança";
    }
}

class ContaRendimento extends Conta {
    public ContaRendimento(CadastroCliente cliente, String numeroConta) {
        super(cliente, numeroConta);
    }

    @Override
    public boolean sacar(double valor) {
        return false; 
    }

    public void render(double taxa) {
        saldo += saldo * taxa;
    }

    @Override
    public String getTipoConta() {
        return "Rendimento";
    }
} 

