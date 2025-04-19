import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JOptionPane;

public class JanelaGrafica {

    private List<PessoaFisica> clientesFisicos = new ArrayList<>();
    private List<PessoaJuridica> clientesJuridicos = new ArrayList<>();

    public static void mensagemErroDepositar(String titulo, String mensagem) {
        System.err.println(titulo + ": " + mensagem);
    }

    public void menuPrincipal() {
        while (true) {
            String aux = "✨ Banco Star ✨\n";
            aux += "1. Clientes \n2. Contas \n3.Finalizar o sistema\n";
            String text = "Selecione uma opção para te direcionarmos: \n";

            int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux));
            // vamos separar o menu principal em menu menores, para facilitar o entendimento
            // do usuário
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
                default:
                    JOptionPane.showMessageDialog(null, "[Opção Inválida] Informe uma opção válida");
            }
        }
    }

    public void menuClientes() {
        while (true) {
            String aux = "==== Menu Clientes ==== \n";
            String text = "Selecione uma opção: \n";
            aux += "1. Cadastrar\n2. Listar\n3. Remover\n4. Voltar";

            int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux));

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
                default:
                    JOptionPane.showMessageDialog(null, "[Opção Inválida] Informe uma opção válida");
            }
        }
    }

    private void cadastrarCliente() {
        String id = UUID.randomUUID().toString();
        String nome = JOptionPane.showInputDialog(null, "Nome do cliente:");
        String telefone = JOptionPane.showInputDialog(null, "Telefone do cliente:");

        if (nome == null || telefone == null || nome.trim().isEmpty() || telefone.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O seu cadastro foi cancelado ou está incompleto.");
            return;
        }

        // pergunta o tipo de cliente para o cadastro
        Object[] opcoes = { "Físico", "Jurídico" };
        int tipo = JOptionPane.showOptionDialog(
                null,
                "Tipo de cliente:",
                "Cadastro",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (tipo == 0) { // cliente físico
            String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:").replaceAll("\\D", "");

            // típicas verificações de CPF
            if (cpf == null || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return;
            }
            if (cpf.length() != 11) {
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
            }
            if (cnpj.length() != 14) {
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
        Object[] opcoes = { "Pessoas Físicas(PF)", "Pessoas Jurídicas(PJ)", "PF&PJ" };
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Qual tipo de cliente deseja listar?",
                "Seleção de Cliente",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                opcoes,
                opcoes[0]);

        if (escolha == JOptionPane.CLOSED_OPTION) {
            return;
        }

        StringBuilder listaClientes = new StringBuilder();
        listaClientes.append("==== Clientes já cadastrados ====\n\n");

        listaClientes.append("--- Pessoas Físicas ---\n");
        if (escolha == 0 || escolha == 2) {
            listaClientes.append("--- Pessoas Físicas(PF) ---\n");
            if (clientesFisicos.isEmpty()) {
                listaClientes.append("Nenhum cliente físico cadastrado.\n");
            } else {
                clientesFisicos.forEach(cliente -> {
                    listaClientes.append("ID: ").append(cliente.getId()).append("\n");
                    listaClientes.append("Nome: ").append(cliente.getNome()).append("\n");
                    listaClientes.append("Telefone: ").append(cliente.getTelefone()).append("\n");
                    listaClientes.append("CPF: ").append(cliente.getCpf()).append("\n");
                    listaClientes.append("----------------------------\n");
                });
            }
        }

        if (escolha == 1 || escolha == 2) {
            listaClientes.append("\n--- Pessoas Jurídicas ---\n");
            if (clientesJuridicos.isEmpty()) {
                listaClientes.append("Nenhum cliente jurídico cadastrado.\n");
            } else {
                clientesJuridicos.forEach(cliente -> {
                    listaClientes.append("ID: ").append(cliente.getId()).append("\n");
                    listaClientes.append("Nome: ").append(cliente.getNome()).append("\n");
                    listaClientes.append("Telefone: ").append(cliente.getTelefone()).append("\n");
                    listaClientes.append("CNPJ: ").append(cliente.getCnpj()).append("\n");
                    listaClientes.append("----------------------------\n");
                });
            }

            JOptionPane.showMessageDialog(null, listaClientes.toString());
        }
    }

    private void removerCliente() {
        Object[] opcoes = { "Físico", "Jurídico" };
        int tipo = JOptionPane.showOptionDialog(
                null,
                "Tipo de cliente:",
                "Remoção de clientes",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (tipo == 0) { // cliente físico
            String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente que deseja remover:");
            if (cpf == null || cpf.isEmpty() || cpf.replaceAll("\\D", "").length() != 11) {
                JOptionPane.showMessageDialog(null, "CPF inválido ou operação cancelada.");
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
            if (cnpj == null || cnpj.isEmpty() || cnpj.replaceAll("\\D", "").length() != 14) {
                JOptionPane.showMessageDialog(null, "CNPJ inválido ou operação cancelada.");
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
    public void menuContas() {
        while (true) {
            String aux = "==== Menu Contas ====\n";
            aux += "1. Cadastrar\n2. Listar\n3. Remover\n4. Voltar";

            int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux));

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
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "[Opção Inválida] Informe uma opção válida");
            }
        }
    }

    private void cadastrarConta() {
        Object[] opcoes = { "Poupança", "Corrente", "Investimento" };
        int tipo = JOptionPane.showOptionDialog(
                null,
                "Tipo de contas:",
                "Cadastro",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (tipo == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Operação cancelada");
            return;
        }

        String agencia = JOptionPane.showInputDialog(null, "Agência:");
        String numero = JOptionPane.showInputDialog(null, "Número da conta:");

        if (agencia == null || agencia.isEmpty() || numero == null || numero.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Agência ou número da conta inválidos.");
            return;
        }

        Object[] tipoClienteOpcoes = { "Físico", "Jurídico" };
        int tipoCliente = JOptionPane.showOptionDialog(
                null,
                "Cliente é do tipo:",
                "Tipo de Cliente",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipoClienteOpcoes,
                tipoClienteOpcoes[0]);

        if (tipoCliente == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Operação cancelada");
            return;
        }

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
                    for (Conta c : cliente.getContas()) {
                        if (c.getAgencia().equals(agencia) && c.getNumero().equals(numero)) {
                            JOptionPane.showMessageDialog(null, "Conta com os mesmos dados já existente");
                            return;
                        }
                    }
                    cliente.getContas().add(novaConta);
                    adicionada = true;
                    break;
                }
            }
        } else {
            for (PessoaJuridica cliente : clientesJuridicos) {
                if (cliente.getCnpj().replaceAll("\\D", "").equals(documento.replaceAll("\\D", ""))) {
                    for (Conta c : cliente.getContas()) {
                        if (c.getAgencia().equals(agencia) && c.getNumero().equals(numero)) {
                            JOptionPane.showMessageDialog(null, " Conta com os mesmos dados já existente");
                            return;
                        }
                    }
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

    private void listarContas() {
        Object[] opcoes = { "Pessoas Físicas", "Pessoas Jurídicas", "Ambos" };
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Qual tipo de conta você deseja listar?",
                "Listar Contas",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (escolha == JOptionPane.CLOSED_OPTION) {
            return; // usuário fechou a janela
        }

        StringBuilder lista = new StringBuilder("==== Contas Cadastradas ====\n\n");
        boolean encontrou = false;

        if (escolha == 0 || escolha == 2) {
            for (PessoaFisica cliente : clientesFisicos) {
                if (!cliente.getContas().isEmpty()) {
                    lista.append("-- Cliente Físico: ").append(cliente.getNome()).append(" --\n");
                    for (Conta conta : cliente.getContas()) {
                        lista.append("Agência: ").append(conta.getAgencia()).append("\n");
                        lista.append("Número: ").append(conta.getNumero()).append("\n");
                        lista.append("Tipo: ").append(conta.getClass().getSimpleName()).append("\n");
                        lista.append("Saldo: R$ ").append(String.format("%.2f", conta.getSaldo())).append("\n");
                        lista.append("------------------------\n");
                        encontrou = true;
                    }
                }
            }
        }

        if (escolha == 1 || escolha == 2) {
            for (PessoaJuridica cliente : clientesJuridicos) {
                if (!cliente.getContas().isEmpty()) {
                    lista.append("-- Cliente Jurídico: ").append(cliente.getNome()).append(" --\n");
                    for (Conta conta : cliente.getContas()) {
                        lista.append("Agência: ").append(conta.getAgencia()).append("\n");
                        lista.append("Número: ").append(conta.getNumero()).append("\n");
                        lista.append("Tipo: ").append(conta.getClass().getSimpleName()).append("\n");
                        lista.append("Saldo: R$ ").append(String.format("%.2f", conta.getSaldo())).append("\n");
                        lista.append("------------------------\n");
                        encontrou = true;
                    }
                }
            }
        }

        if (!encontrou) {
            lista.append("Nenhuma conta cadastrada.\n");
        }

        JOptionPane.showMessageDialog(null, lista.toString());

    }

    private void removerConta(){
        Object[] tipoClienteOpcoes = {"Físico", "Jurídico"};
        int tipoCliente = JOptionPane.showOptionDialog(null, 
        "Cliente é do tipo:", 
        "Remoção de Conta", 
        JOptionPane.DEFAULT_OPTION, 
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        tipoClienteOpcoes, 
        tipoClienteOpcoes[0]
        );

        if(tipoCliente == JOptionPane.CLOSED_OPTION){
            return;
        }

        String documento = (tipoCliente == 0)
            ? JOptionPane.showInputDialog(null, "Digite o CPF do Cliente")
            : JOptionPane.showInputDialog(null, "Digite o CNPJ do Cliente");

        if (documento == null || documento.isEmpty()){
            JOptionPane.showMessageDialog(null, "Operação cancelada");
            return;
        }

        String agencia = JOptionPane.showInputDialog(null, "Informe a agencia da conta:");
        String numero = JOptionPane.showInputDialog(null, "Informe a numero da conta:");
        
        if (agencia == null || agencia.isEmpty() || numero == null || numero.isEmpty()){
            JOptionPane.showMessageDialog(null, "Dados da conta inválidos.");
            return;
        }

        boolean removida = false;

        if (tipoCliente == 0){
            for( PessoaFisica cliente : clientesFisicos) {
                if (cliente.getCpf().replaceAll("\\D", "").equals(documento.replaceAll("\\D", ""))) {
                    removida = cliente.getContas().removeIf( c ->
                        c.getAgencia().equals(agencia) && c.getNumero().equals(numero));
                    break;
                }
            }
        }

        if (removida) {
            JOptionPane.showMessageDialog(null, "Conta removida com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, " Conta e/ou Cliente não encontrado.");
        }

    }

    public static void mensagemErroSacar(String string, String string2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mensagemErroSacar'");
    }
}
