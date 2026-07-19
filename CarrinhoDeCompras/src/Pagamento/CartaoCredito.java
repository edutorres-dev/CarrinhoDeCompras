package Pagamento;
/*
 * ==========================================================
 * Classe CartaoCredito
 * ==========================================================
 *
 * Responsável por processar pagamentos realizados por meio
 * de cartão de crédito.
 *
 * Esta classe implementa a interface Pagamento,
 * fornecendo uma regra específica para esse método de
 * pagamento.
 *
 * A classe armazena:
 *
 * -> Quantidade de parcelas escolhida pelo cliente.
 *
 * Durante o processamento do pagamento, a classe:
 *
 * ->  Calcula o valor de cada parcela;
 * -> Exibe o resumo do parcelamento;
 * -> Simula a aprovação da transação, retornando
 *  verdadeiro quando o pagamento é concluído.
 *
 * Esta implementação considera apenas uma simulação de
 * pagamento, não realizando comunicação com operadoras
 * de cartão ou instituições financeiras.
 *
 *
 */

public class CartaoCredito implements Pagamento {
    /*
     *====================
     * ATRIBUTOS
     * ===================
     * */
    private int parcelas;

    /*
     *====================
     * CONSTRUTOR
     * ===================
     * */

    public CartaoCredito(int parcelas) {
        this.parcelas = parcelas;
    }

    /*
     *====================
     * PAGAR COM PARCELAS
     * ===================
     * */
    @Override
    public boolean pagar(double valor) {

        double valorParcela = valor / parcelas;

        System.out.println("\n===== PAGAMENTO COM CARTÃO DE CRÉDITO =====");

        System.out.printf("Valor da compra: R$ %.2f%n", valor);

        System.out.printf("Parcelamento: %dx de R$ %.2f%n",
                parcelas,
                valorParcela);

        return true;
    }


}
