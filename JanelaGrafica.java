import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JOptionPane;

public class JanelaGrafica {
    private List<PessoaFisica> clientesFisicos = new ArrayList<>();
    private List<PessoaJuridica> clientesJuridicos = new ArrayList<>();
    
    public void menuPrincipal() {
        String aux = "✨ Banco Star ✨\n";
        String text = "Selecione uma opção para te direcionarmos: \n";
        aux += "1. Clientes\n"; 
        aux += "2. Contas\n";
        aux += "3. Finalizar o sistema\n";

        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux));
        if (opcao < 1 || opcao > 3) {
            JOptionPane.showMessageDialog(null, "Opção inválida");
        } else {
            // vamos separar o menu principal em menu menores, para facilitar o entendimento do usuário
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
        String text = "Selecione uma opção: \n";
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
    
        // pergunta o tipo de cliente para o cadastro
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
    
        if (tipo == 0) { // cliente físico
            String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:").replaceAll("\\D", "");
            
            // típicas verificações de CPF
            if (cpf == null || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return;
            } if (cpf.length() != 11) {
                JOptionPane.showMessageDialog(null, "CPF inválido. O CPF deve conter 11 dígitos.");
                return;
            } 
            boolean cpfExistente = false;
            for (PessoaFisica cliente : clientesFisicos) {
                if (cliente.getCpf().replaceAll("\\D", "").equals(cpf)) {
                    cpfExistente = true;
                    break;
                }
            }
            if (cpfExistente) {
                JOptionPane.showMessageDialog(null, "CPF já cadastrado.");
                return;
            }
            
            PessoaFisica cliente = new PessoaFisica(id, nome, telefone, cpf);
            clientesFisicos.add(cliente);

    
        } else if (tipo == 1) { // cliente jurídico
            String cnpj = JOptionPane.showInputDialog(null, "Digite o CNPJ do cliente:");

           // típicas verificações de CNPJ
           if (cnpj == null || cnpj.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            return;
        } if (cnpj.length() != 14) {
            JOptionPane.showMessageDialog(null, "CNPJ inválido. O CNPJ deve conter 14 dígitos.");
            return;
        }
    boolean cnpjExistente = false;
            for (PessoaJuridica cliente : clientesJuridicos) {
                if (cliente.getCnpj().replaceAll("\\D", "").equals(cnpj)) {
                    cnpjExistente = true;
                    break;
                }
            }
            if (cnpjExistente) {
                JOptionPane.showMessageDialog(null, "CNPJ já cadastrado.");
                return;
            }

            PessoaJuridica cliente = new PessoaJuridica(id, nome, telefone, cnpj);
            clientesJuridicos.add(cliente);
        }
    
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    }

    private void listarClientes() {
        StringBuilder listaClientes = new StringBuilder();
        listaClientes.append("==== Clientes já cadastrados ====\n\n");
        
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
        String[] opcoes = {"Físico", "Jurídico"};
        int tipo = JOptionPane.showOptionDialog(
            null, 
            "Tipo de cliente:", 
            "Remoção de clientes", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opcoes, 
            opcoes[0]
        );
    
        if (tipo == 0) { // cliente físico
            String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente que deseja remover:");
            if (cpf == null) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return;
            }
            boolean removido = false;
            for (int i = 0; i < clientesFisicos.size(); i++) {
                if (clientesFisicos.get(i).getCpf().replaceAll("\\D", "").equals(cpf)) {
                    clientesFisicos.remove(i);
                    removido = true;
                    break;
                }
            }

            if (removido) {
                JOptionPane.showMessageDialog(null, "Cliente físico removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente físico não encontrado.");
            }
    
        } else if (tipo == 1) {
            String cnpj = JOptionPane.showInputDialog(null, "Digite o CNPJ do cliente que deseja removê-lo:");
            if (cnpj == null) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return;
            }
            boolean removido = false;
            for (int i = 0; i < clientesJuridicos.size(); i++) {
                if (clientesJuridicos.get(i).getCnpj().replaceAll("\\D", "").equals(cnpj)) {
                    clientesJuridicos.remove(i);
                    removido = true;
                    break;
                }
            }

            if (removido) {
                JOptionPane.showMessageDialog(null, "Cliente jurídico removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente jurídico não encontrado.");
            }
        }
    }

    // parte para as contas
    public void menuContas(){
        String aux = "==== Menu Contas ==== \n";
        String text = "Selecione uma opção: \n";
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

    private void cadastrarConta() {
        String[] opcoes = {"Poupança", "Corrente", "Investimento"};
        int tipo = JOptionPane.showOptionDialog(
            null, 
            "Tipo de contas:", 
            "Cadastro", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opcoes, 
            opcoes[0]
        );
        String agencia = JOptionPane.showInputDialog(null, "Agência:");
        String numero = JOptionPane.showInputDialog(null, "Número da conta:");
        String[] tipoClienteOpcoes = {"Físico", "Jurídico"};
        int tipoCliente = JOptionPane.showOptionDialog(
            null, 
            "Cliente é do tipo:", 
            "Tipo de Cliente", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            tipoClienteOpcoes, 
            tipoClienteOpcoes[0]
        );
    
        String documento = (tipoCliente == 0) 
            ? JOptionPane.showInputDialog(null, "Digite o CPF do cliente:")
            : JOptionPane.showInputDialog(null, "Digite o CNPJ do cliente:");
    
        if (documento == null || documento.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            return;
        }
    
        Conta novaConta = null;
        switch (tipo) {
            case 0:
                novaConta = new ContaPoupanca(agencia, numero);
                break;
            case 1:
                novaConta = new ContaCorrente(agencia, numero);
                break;
            case 2:
                novaConta = new ContaInvestimento(agencia, numero);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de conta inválido.");
                return;
        }
    
        boolean adicionada = false;
    
        if (tipoCliente == 0) {
            for (PessoaFisica cliente : clientesFisicos) {
                if (cliente.getCpf().replaceAll("\\D", "").equals(documento.replaceAll("\\D", ""))) {
                    cliente.getContas().add(novaConta);
                    adicionada = true;
                    break;
                }
            }
        } else if (tipoCliente == 1) {
            for (PessoaJuridica cliente : clientesJuridicos) {
                if (cliente.getCnpj().replaceAll("\\D", "").equals(documento.replaceAll("\\D", ""))) {
                    cliente.getContas().add(novaConta);
                    adicionada = true;
                    break;
                }
            }
        }
    
        if (adicionada) {
            JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado. Verifique o CPF ou CNPJ.");
        }
    }


    }

   
private void listarContas() {
    StringBuilder lista = new StringBuilder("==== Contas Cadastradas ====\n");

    boolean encontrou = false;

    for (PessoaFisica cliente : clientesFisicos) {
        if (!cliente.getContas().isEmpty()) {
            lista.append("\n-- Cliente Físico: ").append(cliente.getNome()).append(" --\n");
            for (Conta conta : cliente.getContas()) {
                lista.append("Agência: ").append(conta.getAgencia()).append("\n");
                lista.append("Número: ").append(conta.getNumero()).append("\n");
                lista.append("Tipo: ").append(conta.getClass().getSimpleName()).append("\n");
                lista.append("Saldo: R$ ").append(conta.getSaldo()).append("\n");
                lista.append("------------------------\n");
                encontrou = true;
            }
        }
    }

    for (PessoaJuridica cliente : clientesJuridicos) {
        if (!cliente.getContas().isEmpty()) {
            lista.append("\n-- Cliente Jurídico: ").append(cliente.getNome()).append(" --\n");
            for (Conta conta : cliente.getContas()) {
                lista.append("Agência: ").append(conta.getAgencia()).append("\n");
                lista.append("Número: ").append(conta.getNumero()).append("\n");
                lista.append("Tipo: ").append(conta.getClass().getSimpleName()).append("\n");
                lista.append("Saldo: R$ ").append(conta.getSaldo()).append("\n");
                lista.append("------------------------\n");
                encontrou = true;
            }
        }
    }

    if (!encontrou) {
        lista.append("\nNenhuma conta cadastrada.");
    }

    JOptionPane.showMessageDialog(null, lista.toString());
}


    private void removerConta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerConta'");
    }

    public static void mensagemErroDepositar(String string, String string2) {
        JOptionPane.showMessageDialog(null, string + string2, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemErroSacar(String string, String string2) {
        JOptionPane.showMessageDialog(null, string + string2, "Erro", JOptionPane.ERROR_MESSAGE);
    }


