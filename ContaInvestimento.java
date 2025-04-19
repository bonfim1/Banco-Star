public class ContaInvestimento extends Conta implements Ganho {

    private double taxa = 0.0022;

    public ContaInvestimento(String agencia, String numero) {
        super(agencia, numero);
    }

    @Override
    public void sacar(double valor) {
        if (valor > saldo) {
            JanelaGrafica.mensagemErroSacar("Saldo insuficiente", "Saldo insuficiente para realizar o saque.");
            return;
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
