package Model;

/*
 * ==========================================================
 * Classe ProdutoDigital
 * ==========================================================
 *
 * Responsável por representar um produto digital
 *
 * Esta classe é uma especialização da classe Produto,
 * herdando todas as suas características (ID, nome, preço
 * e estoque) e adicionando informações específicas para
 * produto digital (no caso tamanho do arquivo)
 *
 * Como produtos digitais não necessitam de transporte
 * físico, o cálculo do frete sempre retorna R$ 0,00.
 *
 *
 */

public class ProdutoDigital extends Produto {

    /*
     *====================
     * ATRIBUTOS
     * ===================
     * */
    private double tamanhoArquivo;

    /*
     *====================
     * CONSTRUTOR
     * ===================
     * */
    public ProdutoDigital(int id , String nome , double preco , int estoque,
                          double tamanhoArquivo){
        super(id, nome, preco, estoque);
        this.tamanhoArquivo=tamanhoArquivo;
    }

    /*
     *====================
     * GETTERS E SETTERS
     * ===================
     * */
    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }
    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }


    /*
     *====================
     * CÁLCULO DO FRETE
     * ===================
     * */
    @Override
    public double calcularFrete() {
        return 0.0;
    }


    /*
     *====================
     * CONVERTE PARA STRING
     * ===================
     * */
    @Override
    public String toString() {

        return super.toString() +
                "\nArquivo: " +
                tamanhoArquivo +
                " MB";

    }

}
