public class ContaInvestimento extends Conta implements Ganho {

    private double taxa = 0.0022;

    public ContaInvestimento(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void sacar(double valor) {
        if (valor > saldo) {
            throw new BancoException("S01", "Saldo insuficiente para saque.");
        }
        saldo -= valor;
    }

    @Override
    public void render() {
        this.saldo *= 1 + taxa;
    }

    @Override
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
    
}
