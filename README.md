# Atividade Banco
## Professor: Humberto Sandmann
## Integrantes:
- Bianca Fagundes [https://github.com/araujozb]
- Luzivania Bonfim [https://github.com/bonfim1]
- Maria Gabriela Vieira [https://github.com/mgabriel4]

## Sobre o exercício:
O mínimo que o sistema deve ter:

1. Cadastro de cliente (Pessoa Física/Jurídica);

2. Cadastro de contas para o cliente:

Conta corrente (tem ou não limite);
Conta poupança (não tem limite e rende);
Conta rendimento (apenas rende);
3. Permitir saques/depósitos (respeitando as regras das conta);

4. Listar contas e clientes.

5. Remover clientes e suas contas do banco.

**Obrigatório:**

a. uso de classes, interfaces;

b. sobrecarga de métodos e construtores;

c. uso de casting e listagem por forEach.

```mermaid
classDiagram
   class Conta{
    -String id
    #double saldo
    -Cliente cliente
    +sacar(double valor)
    +depositar(double valor)

   }
    class Cliente{
      -String id
      -String nome
      -String telefone
      -List<Conta> contas
    }
    class PessoaFisica{
      -String cpf
    }
    class PessoaJuridica{
      -String cnpj
    }
    class ContaCorrente{
        -double limite
        +sacar(double valor)
    }
    class ContaPoupanca{
        +sacar(double valor)
        +render(double valor)
    }
    class ContaInvestimento{
        +sacar(double valor)
        +render(double valor)
    }
    Conta *-- Cliente
    Conta <|-- ContaCorrente
    Conta <|-- ContaPoupanca
    Conta <|-- ContaInvestimento
    Cliente <|-- PessoaFisica
    Cliente <|-- PessoaJuridica``
