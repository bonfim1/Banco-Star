public class ContaPoupanca extends Conta implements Ganho {

    private double taxa = 0.005;

    public ContaPoupanca(String agencia, String numero) {
        super(agencia, numero);
    }
    

    public ContaPoupanca(Cliente cliente, double saldo, double taxa) {
        super(cliente, agencia, numero);
        this.saldo = saldo;
        this.taxa = taxa;
    }
    

    @Override
    public void sacar(double valor) {
        if (valor < 0) {
            throw new RuntimeException("Saque negativo");
        } else if (valor <= saldo) {
            this.saldo -= valor;
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
    }

    @Override
    public void render() {
        saldo *= 1 + taxa;
    }

    @Override
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
    
}