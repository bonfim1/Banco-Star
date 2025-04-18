import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JOptionPane;

public class JanelaGrafica {
    private List<PessoaFisica> clientesFisicos = new ArrayList<>();
    private List<PessoaJuridica> clientesJuridicos = new ArrayList<>();
     public void menuPrincipal() {
        String aux = "==== Sistema do Banco Star ==== \n";
        String text = "Selecione uma opção para te direcionarmos: \n";
        aux += "1. Clientes\n"; 
        aux += "2. Contas\n";
        aux += "3. Finalizar\n";

        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux));
        if (opcao < 1 || opcao > 3) {
            JOptionPane.showMessageDialog(null, "Opção inválida");
        } else {
            switch (opcao) {
                case 1:
                    menuClientes();       
                    break;    
                case 2:
                    menuContas();
                    break;    
                case 3:
                    JOptionPane.showMessageDialog(null, "Sistema encerrado.");
                    return;
                }
        }
        menuPrincipal();
    }

    public void menuClientes(){
        String aux = "==== Menu Clientes ==== \n";
        String text = "Selecione uma opção para te direcionarmos: \n";
        aux += "1. Cadastrar\n"; 
        aux += "2. Listar\n";
        aux += "3. Remover\n";
        aux += "4. Voltar\n";

        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux));
        if (opcao < 1 || opcao > 4) {
            JOptionPane.showMessageDialog(null, "Opção inválida");
        } else {
            switch (opcao) {
                case 1:
                    cadastrarCliente();        
                    break;    
                case 2:
                    listarClientes();
                    break;    
                case 3:
                    removerCliente();
                    break;    
                case 4:
                    menuPrincipal();
                    break;
            }
        }
        menuClientes();
    }

    private void cadastrarCliente() {
        String id = UUID.randomUUID().toString();
        String nome = JOptionPane.showInputDialog(null, "Nome do cliente:");
        String telefone = JOptionPane.showInputDialog(null, "Telefone do cliente:");
    
        if (nome == null || telefone == null) {
            JOptionPane.showMessageDialog(null, "O seu cadastro foi cancelado.");
            return;
        }
    
        // Pergunta o tipo de cliente
        String[] opcoes = {"Físico", "Jurídico"};
        int tipo = JOptionPane.showOptionDialog(
            null, 
            "Tipo de cliente:", 
            "Cadastro", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opcoes, 
            opcoes[0]
        );
    
        if (tipo == 0) { // Cliente Físico (CPF)
            String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:");
            if (cpf == null) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return;
            }
            PessoaFisica cliente = new PessoaFisica(id, nome, telefone, cpf);
            clientesFisicos.add(cliente);
    
        } else if (tipo == 1) { // Cliente Jurídico (CNPJ)
            String cnpj = JOptionPane.showInputDialog(null, "Digite o CNPJ do cliente:");
            if (cnpj == null) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return;
            }
            PessoaJuridica cliente = new PessoaJuridica(id, nome, telefone, cnpj);
            clientesJuridicos.add(cliente);
        }
    
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    }

    private void listarClientes() {
        StringBuilder listaClientes = new StringBuilder();
        listaClientes.append("==== CLIENTES CADASTRADOS ====\n\n");
        
        listaClientes.append("--- Pessoas Físicas ---\n");
        if (clientesFisicos.isEmpty()) {
            listaClientes.append("Nenhum cliente físico cadastrado.\n");
        } else {
            for (PessoaFisica cliente : clientesFisicos) {
                listaClientes.append("ID: ").append(cliente.getId()).append("\n");
                listaClientes.append("Nome: ").append(cliente.getNome()).append("\n");
                listaClientes.append("Telefone: ").append(cliente.getTelefone()).append("\n");
                listaClientes.append("CPF: ").append(cliente.getCpf()).append("\n");
                listaClientes.append("----------------------------\n");
            }
        }
        
        listaClientes.append("\n--- Pessoas Jurídicas ---\n");
        if (clientesJuridicos.isEmpty()) {
            listaClientes.append("Nenhum cliente jurídico cadastrado.\n");
        } else {
            for (PessoaJuridica cliente : clientesJuridicos) {
                listaClientes.append("ID: ").append(cliente.getId()).append("\n");
                listaClientes.append("Nome: ").append(cliente.getNome()).append("\n");
                listaClientes.append("Telefone: ").append(cliente.getTelefone()).append("\n");
                listaClientes.append("CNPJ: ").append(cliente.getCnpj()).append("\n");
                listaClientes.append("----------------------------\n");
            }
        }
        
        JOptionPane.showMessageDialog(null, listaClientes.toString());
    }

    private void removerCliente() {
        String id = JOptionPane.showInputDialog(null, "Digite o ID do cliente a ser removido:");
        
        if (id == null) {
            JOptionPane.showMessageDialog(null, "Remoção cancelada.");
            return;
        }
        
        // Remover cliente da lista de clientes (não implementado aqui)
        JOptionPane.showMessageDialog(null, "Cliente removido com sucesso: " + id);
    }

    public void menuContas(){
        String aux = "==== Menu Contas ==== \n";
        String text = "Selecione uma opção para te direcionarmos: \n";
        aux += "1. Cadastrar\n"; 
        aux += "2. Listar\n";
        aux += "3. Remover\n";
        aux += "4. Voltar\n";

        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux));
        if (opcao < 1 || opcao > 4) {
            JOptionPane.showMessageDialog(null, "Opção inválida");
        } else {
            switch (opcao) {
                case 1:
                    cadastrarConta();        
                    break;    
                case 2:
                    listarContas();
                    break;    
                case 3:
                    removerConta();
                    break;    
                case 4:
                    menuPrincipal();
                    break;
            }
        }
        menuContas();
    }

    private void removerConta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerConta'");
    }

    private void listarContas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarContas'");
    }

    private void cadastrarConta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrarConta'");
    }
}

