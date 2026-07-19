package Service;

import Model.Carrinho;
import Model.ItemCarrinho;
import Model.Produto;

import java.util.Iterator;
import java.util.Scanner;

/*
 * ==========================================================
 * Classe CarrinhoService
 * ==========================================================
 *
 * Responsável por gerenciar todas as operações relacionadas
 * ao carrinho de compras.
 *
 * Esta classe pertence à camada Service da aplicação,
 * funcionando como intermediária entre a interface do usuário
 * (camada de apresentação) e as classes da camada Model.
 *
 * Suas principais responsabilidades são:
 *
 * -> Adicionar produtos ao carrinho;
 * -> Alterar a quantidade de produtos;
 * -> Remover produtos do carrinho;
 * -> Exibir os itens adicionados;
 * -> Calcular o valor total da compra;
 * -> Validar os dados informados pelo usuário antes de
 *    executar qualquer operação.
 *
 * Para realizar essas tarefas, esta classe utiliza:
 *
 * -> Carrinho, responsável apenas por armazenar os itens
 *    adicionados pelo cliente;
 *
 * -> ProdutoService, responsável por localizar os produtos
 *    cadastrados no sistema;
 *
 * -> Scanner, utilizado para receber as entradas do usuário
 *    através do console.
 *
 * Antes de adicionar ou alterar um item no carrinho,
 * esta classe realiza diversas validações, como:
 *
 * -> Existência do produto;
 * -> Quantidade informada;
 * -> Disponibilidade em estoque.
 *
 * Após as validações, o CarrinhoService executa toda a
 * regra de negócio necessária para manter o carrinho e o
 * estoque consistentes.
 *
 */


public class CarrinhoService {

    /*
     * ==========================================
     * ATRIBUTOS
     * ==========================================
     */

    private Carrinho carrinho;
    private ProdutoService produtoService;
    private Scanner scanner;

    /*
     * ==========================================
     * CONSTRUTOR
     * ==========================================
     */

    public CarrinhoService(Carrinho carrinho,
                           ProdutoService produtoService) {

        this.carrinho = carrinho;
        this.produtoService = produtoService;
        this.scanner = new Scanner(System.in);

    }

    /*
     * ==========================================
     * ADICIONAR PRODUTO AO CARRINHO
     * ==========================================
     */

    public void adicionarProdutoCarrinho() {

        produtoService.listarProdutos();

        System.out.print("\nDigite o ID do produto: ");

        int id = scanner.nextInt();

        Produto produto = produtoService.buscarProdutoPorId(id);

        if (produto == null) {

            System.out.println("\nProduto não encontrado.");

            return;

        }

        System.out.print("Quantidade: ");

        int quantidade = scanner.nextInt();

        if (quantidade <= 0) {

            System.out.println("\nQuantidade inválida.");

            return;

        }

        if (quantidade > produto.getEstoque()) {

            System.out.println("\nEstoque insuficiente.");

            return;

        }

        /*
         * Verifica se o produto já existe
         * dentro do carrinho.
         */

        for (ItemCarrinho item : carrinho.getItens()) {

            if (item.getProduto().getId() == produto.getId()) {

                item.setQuantidade(
                        item.getQuantidade() + quantidade
                );

                produto.diminuirEstoque(quantidade);

                System.out.println("\nQuantidade atualizada no carrinho.");

                return;

            }

        }

        /*
         * Caso ainda não exista,
         * cria um novo ItemCarrinho.
         */

        ItemCarrinho novoItem =
                new ItemCarrinho(produto, quantidade);

        carrinho.getItens().add(novoItem);

        produto.diminuirEstoque(quantidade);

        System.out.println("\nProduto adicionado ao carrinho com sucesso!");

    }

    /*
     * ==========================================
     * REMOVER PRODUTO DO CARRINHO
     * ==========================================
     */

    public void removerProdutoCarrinho() {

        if (carrinho.getItens().isEmpty()) {

            System.out.println("\nCarrinho vazio.");

            return;

        }

        visualizarCarrinho();

        System.out.print("\nDigite o ID do produto: ");

        int id = scanner.nextInt();

        Iterator<ItemCarrinho> iterator =
                carrinho.getItens().iterator();

        while (iterator.hasNext()) {

            ItemCarrinho item = iterator.next();

            if (item.getProduto().getId() == id) {

                item.getProduto().aumentarEstoque(
                        item.getQuantidade()
                );

                iterator.remove();

                System.out.println("\nProduto removido do carrinho.");

                return;

            }

        }

        System.out.println("\nProduto não encontrado no carrinho.");

    }

    /*
     * ==========================================
     * ALTERAR QUANTIDADE
     * ==========================================
     */

    public void alterarQuantidadeCarrinho() {

        if (carrinho.getItens().isEmpty()) {

            System.out.println("\nCarrinho vazio.");

            return;

        }

        visualizarCarrinho();

        System.out.print("\nDigite o ID do produto: ");

        int id = scanner.nextInt();

        ItemCarrinho itemEncontrado = null;

        for (ItemCarrinho item : carrinho.getItens()) {

            if (item.getProduto().getId() == id) {

                itemEncontrado = item;

                break;

            }

        }

        if (itemEncontrado == null) {

            System.out.println("\nProduto não encontrado.");

            return;

        }

        System.out.print("Nova quantidade: ");

        int novaQuantidade = scanner.nextInt();

        if (novaQuantidade <= 0) {

            System.out.println("\nQuantidade inválida.");

            return;

        }

        Produto produto = itemEncontrado.getProduto();

        int quantidadeAtual = itemEncontrado.getQuantidade();

        int diferenca = novaQuantidade - quantidadeAtual;

        /*
         * Aumentando quantidade
         */

        if (diferenca > 0) {

            if (diferenca > produto.getEstoque()) {

                System.out.println("\nEstoque insuficiente.");

                return;

            }

            produto.diminuirEstoque(diferenca);

        }

        /*
         * Diminuindo quantidade
         */

        else if (diferenca < 0) {

            produto.aumentarEstoque(-diferenca);

        }

        itemEncontrado.setQuantidade(novaQuantidade);

        System.out.println("\nQuantidade atualizada com sucesso!");

    }

    /*
     * ==========================================
     * VISUALIZAR CARRINHO
     * ==========================================
     */

    public void visualizarCarrinho() {

        if (carrinho.getItens().isEmpty()) {

            System.out.println("\nCarrinho vazio.");

            return;

        }

        System.out.println("\n========== CARRINHO ==========\n");

        for (ItemCarrinho item : carrinho.getItens()) {

            System.out.println(item);

            System.out.println("------------------------------");

        }

        System.out.printf(
                "TOTAL: R$ %.2f%n",
                calcularTotal()
        );

    }

    /*
     * ==========================================
     * CALCULAR TOTAL
     * ==========================================
     */

    public double calcularTotal() {

        double total = 0;

        for (ItemCarrinho item : carrinho.getItens()) {

            total += item.calcularSubtotal();

        }

        return total;

    }

    /*
     * ==========================================
     * LIMPAR CARRINHO
     * ==========================================
     */

    public void limparCarrinho() {

        carrinho.getItens().clear();

    }



}