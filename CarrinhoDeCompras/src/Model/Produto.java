package Model;

/*
 * ==========================================================
 * Classe Produto (Abstrata)
 * ==========================================================
 *
 * Responsável por representar um produto no sistema
 *
 * Esta é uma classe abstrata, ou seja, não pode ser
 * instanciada diretamente. Sua principal finalidade é servir
 * como base para os diferentes tipos de produtos da aplicação(
 * no caso ProdutoFisico e ProdutoDigital) ,compartilhando atributos
 * e comportamentos comuns.
 *
 * Cada produto possui:
 *
 * -> Identificador único (ID)
 * -> Nome do produto
 * -> Preço
 * -> Estoque
 *
 * Além de armazenar essas informações, a classe oferece
 * operações comuns a todos os produtos, como:
 *
 * • Consultar e alterar seus dados;
 * • Aumentar o estoque;
 * • Diminuir o estoque.
 *
 * O cálculo do frete é definido como um método abstrato,
 * obrigando cada classe filha a implementar sua própria
 * regra de cálculo conforme o tipo de produto.
 *
 * Atualmente, esta classe é especializada por:
 * ->  ProdutoFisico;
 * ->  ProdutoDigital.
 *
 */

public abstract class Produto {
    /*
     *====================
     * ATRIBUTOS
     * ===================
     * */
    private int id;
    private String nome;
    private double preco;
    private int estoque;
    /*
     * ===================
     * CONSTRUTOR
     * ====================
     * */
    public Produto(int id , String nome , double preco , int estoque){
        this.id=id;
        this.nome = nome;
        this.preco=preco;
        this.estoque=estoque;
    }

    /*
     * =========================
     * GETTERS
     * ==========================
     * */
    public int getId() {
        return id;
    }
    public String getNome(){
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public int getEstoque(){
        return estoque;
    }

    /*
     * =========================
     * SETTERS
     * ==========================
     * */
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setPreco(double preco){
        this.preco=preco;
    }
    public void setEstoque(int estoque){
        this.estoque=estoque;
    }

    /*
     * ===========================
     * AUMENTAR E DIMINUIR ESTOQUE
     * ============================
     * */
    public void diminuirEstoque(int quantidade){
        estoque -= quantidade;
    }
    public void aumentarEstoque(int quantidade){
        estoque += quantidade;
    }


    /*
     * ======================
     *  CÁLCULO DO FRETE
     * ======================
     * */
    public abstract double calcularFrete();


    /*
     * ====================
     * CONVERSÃO PARA STRING
     * =====================
     * */
    public String toString(){
        return "ID: " +id+
                "\nNome: " + nome +
                "\nPreço: R$ " +preco+
                "\nEstoque: " +estoque +" unidade(s)";

    }

}
