import java.util.ArrayList;
import java.util.List;

public class PessoaFisica extends Cliente {
    private String cpf;
     private List<Conta> contas = new ArrayList<>();

    public PessoaFisica(String name, String cpf) {
        super(name);
        this.cpf = cpf;
    }

    public PessoaFisica(String id, String name, String telefone, String cpf) {
        super(id, name, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
    public List<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) { 
        contas.add(conta);
    }

    @Override
    public String toString() {
        return super.toString() + " (" + cpf + ")";
    }

    

}