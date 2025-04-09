import java.util.UUID;

public class Cliente {
    public final String id;
    public final String nome;
    public String telefone;

    public Cliente(String nome){  
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
    }

    public Cliente(String id, String nome){  
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String id, String nome, String telefone){  
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getId() { 
        return id;
    }
    public String getNome() { 
        return nome;
    }
    public String getTelefone() { 
        return telefone;
    }
    public void setTelefone(String telefone) { 
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        return 
            (obj != null) &&
            (obj instanceof Cliente) &&
            ((Cliente) obj).getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
