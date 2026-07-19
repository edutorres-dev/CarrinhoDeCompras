package Model;

/*
 * ==========================================================
 * Classe ProdutoFisico
 * ==========================================================
 *
 * Responsável por representar um produto físico
 *
 * Esta classe é uma especialização da classe Produto,
 * herdando todas as suas características (ID, nome, preço
 * e estoque) e adicionando informações específicas para
 * produtos ( no caso peso do produto)
 *
 *
 * Além de armazenar essas informações, a classe é
 * responsável por calcular o valor do frete com base
 * no peso do produto, sobrescrevendo o método
 * calcularFrete() definido na classe Produto
 *
 */

public class ProdutoFisico extends Produto {

    /*
     *====================
     * ATRIBUTOS
     * ===================
     * */
    private double peso;

    /*
     *====================
     * CONSTRUTOR
     * ===================
     * */
    public ProdutoFisico(int id , String nome , double preco , int estoque , double peso){
        super(id,nome,preco,estoque);
        this.peso=peso;
    }

    /*
     *====================
     * GETTERS E SETTERS
     * ===================
     * */
    public double getPeso(){
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }


    /*
     *====================
     * CÁLCULO DO FRETE
     * ===================
     * */
    @Override
    public double calcularFrete() {
        return peso*12;
    }

    /*
     *====================
     * CONVERTE PARA STRING
     * ===================
     * */
    @Override
    public String toString(){
        return super.toString() +
                "\nPeso: " +peso+ " kg";
    }

}
