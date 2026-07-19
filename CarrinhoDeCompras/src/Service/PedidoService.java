package Service;

import Model.Cliente;
import Model.ItemCarrinho;
import Model.Pedido;
import Model.Carrinho;
import Service.CarrinhoService;

import java.util.ArrayList;
import java.util.Scanner;


/*
 * ==========================================================
 * CLASSE PEDIDOSERVICE
 * ==========================================================
 *
 * Responsável por gerenciar todas as operações relacionadas
 * aos pedidos realizados no sistema.
 *
 * Suas principais responsabilidades são:
 *
 * -> Criar novos pedidos;
 * -> Finalizar uma compra;
 * -> Processar o pagamento por meio do PagamentoService;
 * -> Registrar o histórico de pedidos;
 * -> Buscar pedidos pelo ID;
 * -> Listar todos os pedidos realizados;
 * -> Cancelar pedidos e devolver os produtos ao estoque;
 * -> Gerenciar a geração automática dos números dos pedidos.
 *
 * Para realizar essas operações, a classe utiliza:
 *
 * • Uma lista de objetos Pedido para armazenar o histórico
 *   de compras;
 * • O CarrinhoService para obter as informações do carrinho;
 * • O PagamentoService para processar as diferentes formas
 *   de pagamento;
 * • Um Scanner para receber as entradas do usuário.
 *
 * Durante a finalização de uma compra, esta classe realiza
 * diversas validações, como:
 *
 * -> Verificar se existe um cliente selecionado;
 * -> Verificar se o carrinho possui itens;
 * -> Criar o pedido a partir dos dados do carrinho;
 * -> Processar o pagamento;
 * -> Atualizar o status do pedido;
 * -> Registrar a forma de pagamento utilizada;
 * -> Armazenar o pedido no histórico;
 * -> Limpar o carrinho após a conclusão da compra.
 *
 * No cancelamento de um pedido, a classe também é
 * responsável por devolver ao estoque todos os produtos
 * pertencentes ao pedido e atualizar seu status para
 * "CANCELADO".
 *.
 */

public class PedidoService {

    /*
     * ==================================================
     * ATRIBUTOS
     * ==================================================
     */

    private ArrayList<Pedido> pedidos;

    private int contadorPedidos;

    private PagamentoService pagamentoService;

    private Scanner scanner;

    private CarrinhoService carrinhoService;

    /*
     * ==================================================
     * CONSTRUTOR
     * ==================================================
     */
    public PedidoService(Scanner scanner, CarrinhoService carrinhoService) {

        pedidos = new ArrayList<>();

        contadorPedidos = 1;

        this.scanner = scanner;

        this.carrinhoService = carrinhoService;

        pagamentoService = new PagamentoService();

    }

    /*
     * ==================================================
     * GETTERS
     * ==================================================
     */

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }



    /*
     * ==================================================
     * CRIAR PEDIDO
     * ==================================================
     *
     * Responsável por criar um objeto Pedido a partir
     * dos dados do cliente e do carrinho.
     *
     * Este método NÃO salva o pedido na lista.
     * Ele apenas cria e devolve o objeto.
     *
     * A responsabilidade de salvar o pedido ficará
     * com o método finalizarPedido().
     */

    private Pedido criarPedido(Cliente cliente, Carrinho carrinho) {

        // cópia dos itens do carrinho
        ArrayList<ItemCarrinho> itensPedido =
                new ArrayList<>(carrinho.getItens());



        // calcula o valor total da compra
        double valorTotal = carrinhoService.calcularTotal();

        //cria o pedido
        Pedido pedido = new Pedido(

                contadorPedidos,

                cliente,

                itensPedido,

                valorTotal

        );

        //atualiza contador
        contadorPedidos++;


        return pedido;

    }

    /*
     * ==================================================
     * FINALIZAR PEDIDO
     * ==================================================
     *
     * Responsável por concluir uma compra.
     *
     * Fluxo:
     *
     * 1 - Verifica se existe cliente;
     * 2 - Verifica se o carrinho possui itens;
     * 3 - Cria o pedido;
     * 4 - Envia o valor para o PagamentoService;
     * 5 - Verifica se o pagamento foi aprovado;
     * 6 - Salva o pedido no histórico;
     * 7 - Exibe o resumo da compra;
     * 8 - Limpa o carrinho.
     *
     */

    public void finalizarPedido(Cliente cliente, Carrinho carrinho) {

        // verifica se existe um cliente selcionado
        if (cliente == null) {

            System.out.println(
                    "\nNenhum cliente foi selecionado."
            );

            return;

        }


        // verifica se existem produtos adicionados ao carrinho
        if (carrinho.getItens().isEmpty()) {


            System.out.println(
                    "\nO carrinho está vazio."
            );


            return;

        }


        //cria o pedido com cliente selcionado , itens do carrinho
        // e valor total da compra
        Pedido pedido = criarPedido(
                cliente,
                carrinho
        );




        // processa o pagamento enviando o valor total do pedido
        // para o PagamentoService , lá que vai ser deicidido
        // o tipo  de pagamento
        boolean pagamentoAprovado =
                pagamentoService.realizarPagamento(
                        pedido.getValorTotal()
                );


        // vefica se o pagamento foi aprovado
        // se não for aprovado o pedido não sera salvo
        if (!pagamentoAprovado) {


            System.out.println(
                    "\nPagamento não realizado."
            );


            System.out.println(
                    "Pedido cancelado."
            );


            return;

        }


        // pagamento aprovado atualiza o status do pedido
        pedido.setStatus("PAGO");

        pedido.setFormaPagamento(
                pagamentoService.getFormaPagamento()
        );

        // caso o pagamento tenha sido com cartao de crédito
        // adiciona parcelamento
        if (pedido.getFormaPagamento().equals("CARTÃO DE CRÉDITO")) {

            pedido.setParcelas(
                    pagamentoService.getParcelas()
            );

            pedido.setValorParcela(
                    pagamentoService.getValorParcela(
                            pedido.getValorTotal()
                    )
            );

        }



        // salva o pedido no histórico
        pedidos.add(pedido);

        // status confirmado para pago
        pedido.setStatus("PAGO");

        // exibe o reusmo completo do pedido
        System.out.println("\nResumo do Pedido:\n");
        System.out.println(pedido);



        // após a compra ser concluída , limpa o carrinho
        carrinhoService.limparCarrinho();

    }



    /*
     * ==================================================
     * BUSCAR PEDIDO POR ID
     * ==================================================
     *
     */
    public Pedido buscarPedidoPorId(int id){

        //percore a lista de pedidos
        for(Pedido pedido : pedidos){


            //verifica se o id informado é igual
            // ao id do pedido atual

            if(pedido.getId() == id){

                return pedido;

            }

        }


        return null;

    }

    /*
     * ==================================================
     * LISTAR PEDIDOS
     * ==================================================
     *
     */

    public void listarPedidos(){

        // verifica se existe pedido
        if(pedidos.isEmpty()){

            System.out.println("\nNenhum pedido foi realizado.");

            return;

        }

        System.out.println("\n========== HISTÓRICO DE PEDIDOS ==========\n");

        //percorre e exibe cada pedido
        for(Pedido pedido : pedidos){

            System.out.println(pedido);

            System.out.println("------------------------------------------");

        }

    }


    /*
     * ==================================================
     * CANCELAR PEDIDO
     * ==================================================
     *
     */

    public void cancelarPedido() {

        // verifica se existe algum pedido ativo
        boolean existePedidoAtivo = false;

        for (Pedido pedido : pedidos) {

            // ignora os pedidos que já foram cancelados
            if (!pedido.getStatus().equalsIgnoreCase("CANCELADO")) {

                existePedidoAtivo = true;
                break;

            }

        }

        // se não existe pedido ativo
        if (!existePedidoAtivo) {

            System.out.println("\nNão existem pedidos disponíveis para cancelamento.");

            return;

        }

        // exibe só os pedidos que estÃo ativos
        System.out.println("\n========== PEDIDOS DISPONÍVEIS ==========\n");

        for (Pedido pedido : pedidos) {

            if (!pedido.getStatus().equalsIgnoreCase("CANCELADO")) {

                System.out.println(pedido);
                System.out.println("------------------------------------------");

            }

        }

        // solicita o id do pedido
        System.out.print("\nDigite o ID do pedido que deseja cancelar: ");
        int id = scanner.nextInt();
        Pedido pedido = buscarPedidoPorId(id);

        // caso o número do pedido informado não exista no sistema
        if (pedido == null) {

            System.out.println("\nPedido não encontrado.");

            return;

        }

        // caso o usuário informe o número de um pedido que já foi cancelado
        if (pedido.getStatus().equalsIgnoreCase("CANCELADO")) {
            System.out.println("\nEste pedido já foi cancelado.");
            return;

        }

        // quando cancela o produto volta para o estoque
        for (ItemCarrinho item : pedido.getItens()) {

            item.getProduto().aumentarEstoque(
                    item.getQuantidade()
            );

        }

        // status do pedido é atualizado para cancelado
        pedido.setStatus("CANCELADO");

        // exibe a confirmação do cancelamento para o usuário
        System.out.println("\n====================================");
        System.out.println("PEDIDO CANCELADO COM SUCESSO!");
        System.out.println("====================================");
        System.out.println("\nPedido Nº: " + pedido.getId());
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("Os produtos retornaram ao estoque.");

    }



}