package Pagamento;

/*
 * ==========================================================
 * Classe CartaoDebito
 * ==========================================================
 *
 * Responsável por processar pagamentos realizados por meio
 * de cartão de débito.
 *
 * Esta classe implementa a interface Pagamento,
 * fornecendo uma regra específica para esse método de
 * pagamento.
 *
 * Durante o processamento do pagamento, a classe:
 *
 * -> Exibe o valor total da compra;
 * -> Simula a aprovação da transação;
 * -> Retorna verdadeiro quando o pagamento é concluído.
 *
 * Como pagamentos no débito são realizados em uma única
 * cobrança, não há cálculo de parcelamento.
 *
 * Esta implementação representa apenas uma simulação de
 * pagamento, não realizando comunicação com operadoras
 * de cartão ou instituições financeiras.
 *
 *
 */

public class CartaoDebito implements Pagamento {
    /*
     *====================
     * PAGAR COM DÉBITO
     * ===================
     * */
    @Override
    public boolean pagar(double valor) {

        System.out.println("===== Pagamento realizado com Cartão de Débito =====");
        System.out.printf("Valor pago: R$ %.2f%n", valor);

        return true;

    }
}
