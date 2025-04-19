
import java.util.UUID;

public abstract class Conta {
    private String id = UUID.randomUUID().toString();
    protected double saldo = 0;
    protected static String agencia;
    protected static String numero;
    protected static Cliente cliente = null;

    public Conta(Cliente cliente, String agencia, String numero) {
        Conta.cliente = cliente;
        Conta.agencia = agencia;
        Conta.numero = numero;
    }
    public void depositar(double valor) {
        if (valor <= 0) {
            JanelaGrafica.mensagemErroDepositar("Valor inválido", "Valor deve ser maior que zero.");
            return;
        }
        this.saldo += valor;
    }

    public abstract void sacar(double valor);

    public String getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        Conta.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

   
    @Override
    public String toString() {
        return "[" + Conta.cliente + "]: " + this.saldo;
    }

} 
