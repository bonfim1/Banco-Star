import java.util.UUID;

public class Cliente {
    public final String id;
    public final String nome;
    public String telefone;

    public Cliente(String nome){  
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
    }

    public String getId() { 
        return id;
    }
    public String getNome() { 
        return nome;
    }

    @Override
    public String toString() {
        return "[ ID: " + id + "]: " + nome;
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
