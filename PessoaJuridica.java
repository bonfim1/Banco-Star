import java.util.ArrayList;
import java.util.List;

public class PessoaJuridica extends Cliente {
    private String cnpj;
     private List<Conta> contas = new ArrayList<>();

    public PessoaJuridica(String name, String cnpj) {
        super(name);
        this.cnpj = cnpj;
    }

    public PessoaJuridica(String id, String name, String telefone, String cnpj) {
        super(id, name, telefone);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }
     public List<Conta> getContas() { 
        return contas;
    }
    public void adicionarConta(Conta conta) { 
        contas.add(conta);
    }

}
