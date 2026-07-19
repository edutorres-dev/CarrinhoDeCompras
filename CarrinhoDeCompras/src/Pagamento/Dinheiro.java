package Pagamento;

import java.util.Scanner;

/*
 * ==========================================================
 * CLASSE DINHEIRO
 * ==========================================================
 *
 * Responsável por processar pagamentos realizados em
 * dinheiro.
 *
 * Esta classe implementa a interface Pagamento,
 * fornecendo uma regra específica para esse método de
 * pagamento.
 *
 * Durante o processamento do pagamento, a classe:
 *
 * -> Exibe o valor total da compra;
 * -> Solicita o valor entregue pelo cliente;
 * -> Calcula o valor do troco;
 * -> Exibe o troco ao usuário;
 * -> Simula a conclusão da transação, retornando
 * -> verdadeiro quando o pagamento é realizado.
 *
 * Esta implementação representa apenas uma simulação de
 * pagamento, não realizando integração com equipamentos
 * de caixa ou sistemas financeiros.
 *
 */

public class Dinheiro implements Pagamento {

    //inicializa o scanner para entrada do usuário
    Scanner entrada = new Scanner(System.in);

    /*
     *====================
     * PAGAR EM DINHEIRO
     * ===================
     * */
    @Override
    public boolean pagar(double valor) {

        System.out.println("\n===== Pagamento realizado em Dinheiro =====");
        System.out.printf("Valor da compra: R$ %.2f%n", valor);
        System.out.print("Valor recebido: R$ ");
        double recebido = entrada.nextDouble();
        double troco = recebido - valor;
        System.out.printf("Troco: R$ %.2f%n", troco);

        return true;

    }
}
