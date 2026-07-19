package Service;

import Model.Cliente;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * ==================================================
 * Classe CLienteService
 * ==================================================
 *
 * Responsável por gerenciar todos os clientes
 * cadastrados no sistema.
 *
 * Esta classe possui como responsabilidades:
 *
 * -> Cadastrar clientes;
 * -> Listar clientes;
 * -> Buscar cliente por ID;
 * -> Editar clientes;
 * -> Remover clientes;
 * -> Selecionar o cliente que realizará a compra.
 *
 *
 * Dessa forma a classe Menu não precisa manipular
 * diretamente os objetos Cliente.
 */

public class ClienteService {

    /*
     * ==================================================
     * ATRIBUTOS
     * ==================================================
     */

    private ArrayList<Cliente> clientes;

    private Scanner scanner;

    private Cliente clienteSelecionado;

    private int proximoId;



    /*
     * ==================================================
     * CONSTRUTOR
     * ==================================================
     *
     * Recebe a lista de clientes e o Scanner criados
     * pela classe Menu.
     *
     * Assim toda a aplicação utiliza a mesma lista
     * compartilhada de clientes.
     */

    public ClienteService(ArrayList<Cliente> clientes,
                          Scanner scanner) {

        this.clientes = clientes;
        this.scanner = scanner;

        // Primeiro ID disponível
        proximoId = 1;

    }

    /*
     * ==================================================
     * GETTERS E SETTERS
     * ==================================================
     */

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    /*
     * ==================================================
     * BUSCAR CLIENTE POR ID
     * ==================================================
     */

    public Cliente buscarClientePorId(int id) {

        // Percorre toda a lista de clientes
        for (Cliente cliente : clientes) {

            // Verifica se encontrou o ID informado
            if (cliente.getId() == id) {

                return cliente;

            }

        }

        // Nenhum cliente encontrado
        return null;

    }


    /*
     * ==================================================
     * CADASTRAR CLIENTE
     * ==================================================
     *
     * Solicita os dados do cliente e realiza seu cadastro.
     *
     * Não permite que dois clientes possuam o mesmo ID.
     */

    public void cadastrarCliente() {

        scanner.nextLine(); // limpa o enter que ficou do menu


        System.out.println("\n========== CADASTRO DE CLIENTE ==========\n");

        // Gera automaticamente o ID
        int id = proximoId++;
        //mostra para o usuário
        System.out.println("ID: " + id);


        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        // cria um Cliente
        Cliente cliente = new Cliente(

                id,

                nome,

                email

        );


        //adiciona um clien na lista
        clientes.add(cliente);
        //exibe confirmação para o usuário
        System.out.println("\nCliente cadastrado com sucesso!");

    }

    /*
     * ==================================================
     * LISTAR CLIENTES
     * ==================================================
     *
     * */

    public void listarClientes() {

        // Verifica se existem clientes cadastrados
        if (clientes.isEmpty()) {

            System.out.println("\nNenhum cliente cadastrado.");

            return;

        }

        System.out.println("\n========== CLIENTES ==========\n");

        // Percorre toda a lista
        for (Cliente cliente : clientes) {

            System.out.println(cliente);

            System.out.println("------------------------------");

        }

    }

    /*
     * ==================================================
     * EDITAR CLIENTE
     * ==================================================
     */

    public void editarCliente() {

        System.out.println("\n========== EDITAR CLIENTE ==========\n");

        System.out.print("Digite o ID do cliente: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = buscarClientePorId(id);

        if (cliente == null) {

            System.out.println("\nCliente não encontrado.");

            return;

        }

        System.out.println("\nCliente Atual:");

        System.out.println(cliente);

        System.out.print("\nNovo Nome: ");

        cliente.setNome(scanner.nextLine());

        System.out.print("Novo E-mail: ");

        cliente.setEmail(scanner.nextLine());

        System.out.println("\nCliente atualizado com sucesso!");

    }


    /*
     * ==================================================
     * REMOVER CLIENTE
     * ==================================================
     *
     * */

    public void removerCliente() {

        System.out.println("\n========== REMOVER CLIENTE ==========\n");

        System.out.print("Digite o ID do cliente: ");

        int id = scanner.nextInt();

        Cliente cliente = buscarClientePorId(id);

        if (cliente == null) {

            System.out.println("\nCliente não encontrado.");

            return;

        }

        // caso o cliente removido seja o mesmo selcionado para compra
        // sua seleção é automaticamente cancelada
        if (cliente == clienteSelecionado) {

            clienteSelecionado = null;

        }

        //remove o cliente
        clientes.remove(cliente);
        //mostra confirmação para o usuário
        System.out.println("\nCliente removido com sucesso!");

    }


    /*
     * ==================================================
     * SELECIONAR CLIENTE
     * ==================================================
     *
     * Permite escolher qual cliente utilizará
     * o sistema de compras .
     *
     * O cliente selecionado ficará armazenado
     * no atributo clienteSelecionado.
     *
     */

    public void selecionarCliente() {

       // verifica se existe cliente cadastrado
        if (clientes.isEmpty()) {

            System.out.println("\nNenhum cliente cadastrado.");

            return;

        }

        //mostra todos os clientes cadastrados para o usuário não selcionar errado
        listarClientes();

        System.out.print("\nDigite o ID do cliente: ");
        //entrada para o usuário

        int id = scanner.nextInt();
        // faz a busca
        Cliente cliente = buscarClientePorId(id);


        //verifica se o cliente existe
        if (cliente == null) {

            System.out.println("\nCliente não encontrado.");

            return;

        }

        // seleciona o cliente
        clienteSelecionado = cliente;

        System.out.println("\nCliente selecionado com sucesso!");

        System.out.println(clienteSelecionado);

    }

    /*
     * ==================================================
     * CARREGAR CLIENTES PADRÃO (TESTE NO DESENVOLVIMENTO)
     * ==================================================
     */

    public void carregarClientesPadrao() {

        clientes.add(new Cliente(
                1,
                "Eduardo Torres",
                "eduardo@email.com"
        ));

        clientes.add(new Cliente(
                2,
                "Maria Silva",
                "maria@email.com"
        ));

        clientes.add(new Cliente(
                3,
                "João Souza",
                "joao@email.com"
        ));

    }




}