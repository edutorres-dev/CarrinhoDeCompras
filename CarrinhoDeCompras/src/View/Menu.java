package View;

import Model.Carrinho;
import Model.Cliente;
import Model.Produto;
import Service.ProdutoService;
import Service.PedidoService;
import Service.CarrinhoService;
import Service.ClienteService;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * ==========================================================
 * CLASSE MENU
 * ==========================================================
 *
 * Responsável por controlar a interação do usuário com o
 * sistema por meio do menu principal , coodernando o fluxo
 * da aplicação
 *
 *
 * Suas principais responsabilidades são:
 *
 * -> Exibir o menu principal do sistema;
 * -> Receber as opções escolhidas pelo usuário;
 * -> Criar e inicializar os objetos necessários para a
 *   execução da aplicação;
 * -> Compartilhar listas e objetos entre os serviços;
 * -> Encaminhar cada operação para o serviço responsável.
 *
 * Durante sua inicialização, a classe cria:
 *
 * • A lista de produtos;
 * • A lista de clientes;
 * • O carrinho de compras;
 * • O Scanner utilizado em toda a aplicação;
 * • As instâncias dos serviços do sistema.
 *
 */


public class Menu {


    // ==========================
    // ATRIBUTOS
    // ==========================

    private Scanner scanner;
    private Carrinho carrinho;
    private ArrayList<Produto> produtos;
    private ArrayList<Cliente> clientes;


    // Services responsáveis pelas regras do sistema

    private ProdutoService produtoService;
    private ClienteService clienteService;
    private CarrinhoService carrinhoService;
    private PedidoService pedidoService;



    // ==========================
    // CONSTRUTOR
    // ==========================

    public Menu(){


        scanner = new Scanner(System.in);


        // cria a lista que será compartilhada
        produtos = new ArrayList<>();


        clientes = new ArrayList<>();


        // cria o carrinho vazio
        carrinho = new Carrinho();



        // envia a mesma lista e scanner para o ProdutoService
        produtoService = new ProdutoService(
                produtos,
                scanner
        );


        clienteService = new ClienteService(
                clientes,
                scanner
        );



        carrinhoService = new CarrinhoService(
                carrinho,
                produtoService
        );



        this.pedidoService = new PedidoService(
                scanner,
                carrinhoService
        );

    }



    // ==========================
    // EXIBIR MENU
    // ==========================

    public void exibirMenu() {

        System.out.println("\n====================================");
        System.out.println("   SISTEMA DE CARRINHO DE COMPRAS");
        System.out.println("====================================");

        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Selecionar Cliente");
        System.out.println("4 - Cadastrar Produtos");
        System.out.println("5 - Listar Produtos");
        System.out.println("6 - Editar Produto");
        System.out.println("7 - Adicionar Produto ao Carrinho");
        System.out.println("8 - Remover Produto do Carrinho");
        System.out.println("9 - Alterar Quantidade do Carrinho");
        System.out.println("10 - Visualizar Carrinho");
        System.out.println("11 - Finalizar Pedido");
        System.out.println("12 - Histórico de Pedidos");
        System.out.println("13 - Cancelar Pedido");
        System.out.println("0 - Sair");

        // Prompt para entrada do usuário
        System.out.print("\nEscolha uma opção: ");
    }





    // ==========================
    // EXECUTAR MENU
    // ==========================

    public void executar(){

        // produtos padrões para teste
//        produtoService.carregarProdutosPadrao();

        // produtos padrões para teste
//        clienteService.carregarClientesPadrao();


        int opcao;


        do{


            exibirMenu();


            opcao = scanner.nextInt();



            switch(opcao){


                case 1:
                    clienteService.cadastrarCliente();
                    break;

                case 2:
                    clienteService.listarClientes();
                    break;

                case 3:
                    clienteService.selecionarCliente();
                    break;

                case 4:
                    produtoService.cadastrarProduto();
                    break;

                case 5:
                    produtoService.listarProdutos();
                    break;

                case 6:
                    produtoService.editarProduto();
                    break;

                case 7:
                    carrinhoService.adicionarProdutoCarrinho();
                    break;

                case 8:
                    carrinhoService.removerProdutoCarrinho();
                    break;

                case 9:
                    carrinhoService.alterarQuantidadeCarrinho();
                    break;

                case 10:
                    carrinhoService.visualizarCarrinho();
                    break;

                case 11:
                    pedidoService.finalizarPedido(
                            clienteService.getClienteSelecionado(),
                            carrinho
                    );
                    break;

                case 12:
                    pedidoService.listarPedidos();
                    break;

                case 13:
                    pedidoService.cancelarPedido();
                    break;

                case 0:
                    System.out.println("Obrigado por utilizar o sistema.");
                    break;

                default:
                    System.out.println("Opção inválida.");

            }



        }while(opcao != 0);


    }


}