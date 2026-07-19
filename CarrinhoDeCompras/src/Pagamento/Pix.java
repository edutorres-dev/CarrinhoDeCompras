package Pagamento;

/*
 * ==========================================================
 * CLASSE PIX
 * ==========================================================
 *
 * Responsável por processar pagamentos realizados por meio
 * do PIX.
 *
 * Esta classe implementa a interface Pagamento,
 * fornecendo uma regra específica para esse método de
 * pagamento.
 *
 * Durante o processamento do pagamento, a classe:
 *
 * -> Calcula o desconto concedido ao cliente;
 * -> Aplica o desconto sobre o valor da compra;
 * -> Exibe o valor original, o desconto e o valor final;
 * -> Simula a conclusão da transação, retornando
 *   verdadeiro quando o pagamento é realizado.
 *
 * Nesta implementação, pagamentos via PIX recebem um
 * desconto de 5% sobre o valor total da compra.
 *
 * Esta implementação representa apenas uma simulação de
 * pagamento, não realizando integração com instituições
 * financeiras ou com o Sistema de Pagamentos Instantâneos
 * (SPI) do Banco Central.
 *
 */

public class Pix implements Pagamento {

    /*
     *====================
     * PAGAR COM PIX
     * ===================
     * */
    @Override
    public boolean pagar(double valor) {

        // pagamento pix tem desconto = valor x 0,05
        double desconto = valor * 0.05;

        //valor final com desconto
        double valorFinal = valor - desconto;

        System.out.println("\n===== Pagamento realizado via PIX ===== ");
        System.out.printf("Valor da compra: R$ %.2f%n", valor);
        System.out.printf("Desconto: R$ %.2f%n", desconto);
        System.out.printf("Valor final: R$ %.2f%n", valorFinal);

        return true;

    }
}
