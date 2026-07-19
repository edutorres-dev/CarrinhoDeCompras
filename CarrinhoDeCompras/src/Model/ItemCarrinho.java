package Model;

/*
 * ==========================================================
 * Classe ItemCarrinho
 * ==========================================================
 *
 * Responsável por representar um item presente no carrinho
 * de compras.
 *
 * Cada objeto ItemCarrinho associa um produto à quantidade
 * escolhida pelo cliente, permitindo que o mesmo produto
 * seja tratado individualmente dentro do carrinho.
 *
 * Cada item possui:
 *
 * -> Produto selecionado;
 * -> Quantidade do produto adicionada ao carrinho.
 *
 * Além de armazenar essas informações, a classe também é
 * responsável por realizar os cálculos relacionados ao
 * próprio item, como:
 *
 * -> Valor do frete do item;
 * -> Subtotal da compra (preço × quantidade + frete).
 *
 * Os objetos desta classe compõem o carrinho de compras
 * (Carrinho) e posteriormente são utilizados na criação
 * de um Pedido, servindo como base para o cálculo do valor
 * total da compra.
 *
 *
 */


public class ItemCarrinho {

    /*
     * ======================
     * ATRIBUTOS
     * ======================
     * */

    private Produto produto;
    private int quantidade;


    /*
     * ======================
     * CONSTRUTOR
     * ======================
     * */

    public ItemCarrinho(Produto produto, int quantidade){
        this.produto=produto;
        this.quantidade=quantidade;
    }


    /*
     * ======================
     * GETTERS E SETTERS
     * ======================
     * */

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }



    /*
     * ======================
     * CÁLCULO DO FRETE ( FRETE * QTD)
     * ======================
     * */    public double calcularFrete() {
        return produto.calcularFrete() * quantidade;
    }


    /*
     * =================================================
     * CÁLCULO DO SUBTOTAL DO ITEM ( PREÇO X QTD + FRETE)
     * =================================================
     * */
    public double calcularSubtotal(){

        double subtotal = produto.getPreco() * quantidade;

        return subtotal + calcularFrete();

    }


    /*
     * ======================
     * CONVERTE PARA STRING
     * ======================
     * */
    @Override
    public String toString() {

        return "\nID: " + produto.getId() +
                "\nProduto: " + produto.getNome() +
                "\nPreço: R$ " + String.format("%.2f", produto.getPreco()) +
                "\nQuantidade: " + quantidade +
                "\nFrete: R$ " + String.format("%.2f", calcularFrete()) +
                "\nSubtotal: R$ " + String.format("%.2f", calcularSubtotal()) +
                "\n====================";

    }
}