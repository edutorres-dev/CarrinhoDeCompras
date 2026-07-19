package Service;

import Pagamento.Pagamento;
import Pagamento.Pix;
import Pagamento.CartaoCredito;
import Pagamento.CartaoDebito;
import Pagamento.Dinheiro;

import java.util.Scanner;

/*
 * ==========================================================
 * CLASSE PAGAMENTOSERVICE
 * ==========================================================
 *
 * Responsável por gerenciar todo o processo de pagamento
 * dos pedidos realizados no sistema.
 *
 *
 * Suas principais responsabilidades são:
 *
 * • Exibir as formas de pagamento disponíveis;
 * • Receber a opção escolhida pelo usuário;
 * • Definir a estratégia de pagamento correspondente;
 * • Gerenciar as opções de parcelamento do cartão de crédito;
 * • Processar o pagamento por meio da interface Pagamento;
 * • Registrar a forma de pagamento utilizada;
 * • Disponibilizar as informações de parcelamento para o
 *   PedidoService.
 *
 * Para realizar essas operações, a classe utiliza:
 *
 * • A interface Pagamento como contrato para todas as formas
 *   de pagamento;
 * • As implementações Pix, CartaoCredito,
 *   CartaoDebito e Dinheiro;
 * • Um Scanner para receber as entradas do usuário.
 *
 * Durante o processamento do pagamento, esta classe:
 *
 * • Apresenta as opções de pagamento;
 * • Valida a escolha do usuário;
 * • Define a quantidade máxima de parcelas de acordo com
 *   o valor da compra;
 * • Cria dinamicamente a implementação adequada da
 *   interface Pagamento;
 * • Executa o método pagar() da forma de pagamento
 *   selecionada;
 * • Armazena informações como a forma de pagamento e o
 *   número de parcelas para utilização no resumo do pedido.
 *
 *
 */


public class PagamentoService {


    /*
     * ==================================================
     * ATRIBUTO
     * ==================================================
     */

    private Scanner scanner;

    private String formaPagamento;

    private int parcelas;




    /*
     * ==================================================
     * CONSTRUTOR
     * ==================================================
     */

    public PagamentoService(){

        scanner = new Scanner(System.in);

    }

    /*
     * ==================================================
     * GETTER
     * ==================================================
     */

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }



    /*
     * ==================================================
     * REALIZAR PAGAMENTO
     * ==================================================
     *
     * Recebe o valor total do pedido.
     *
     * Fluxo:
     *
     * 1 - Mostra opções;
     * 2 - Usuário escolhe;
     * 3 - Cria o pagamento;
     * 4 - Executa pagar().
     *
     */

    public double getValorParcela(double valorTotal) {

        if(parcelas <= 0) {
            return valorTotal;
        }

        return valorTotal / parcelas;

    }


    public boolean realizarPagamento(double valor) {

        System.out.println("\n========== FORMA DE PAGAMENTO ==========");

        System.out.println("1 - PIX");
        System.out.println("2 - Cartão de Crédito");
        System.out.println("3 - Cartão de Débito");
        System.out.println("4 - Dinheiro");

        System.out.print("\nEscolha uma opção: ");

        int opcao = scanner.nextInt();

        Pagamento pagamento;

        switch (opcao) {

            case 1:

                pagamento = new Pix();

                formaPagamento = "PIX";

                break;

            case 2:

                formaPagamento = "CARTÃO DE CRÉDITO";

                int maxParcelas;

                /*
                 * Até R$ 500
                 * apenas 2x
                 */
                if (valor <= 500) {

                    maxParcelas = 2;

                }

                /*
                 * Acima de R$ 1500
                 * até 12x
                 */
                else if (valor > 1500) {

                    maxParcelas = 12;

                }

                /*
                 * Entre 500 e 1500
                 * até 6x
                 */
                else {

                    maxParcelas = 6;

                }

                System.out.println("\n===== PARCELAMENTO =====");

                System.out.printf("Valor Total da Compra: R$ %.2f%n%n", valor);

                for (int i = 1; i <= maxParcelas; i++) {

                    double valorParcela = valor / i;

                    System.out.printf(
                            "%d - %dx de R$ %.2f%n",
                            i,
                            i,
                            valorParcela
                    );

                }

                int parcelas;

                do {

                    System.out.print("\nEscolha o número de parcelas: ");

                    parcelas = scanner.nextInt();

                } while (parcelas < 1 || parcelas > maxParcelas);

                /*
                 * Guarda a quantidade escolhida
                 * para utilizar depois no resumo.
                 */
                this.parcelas = parcelas;

                pagamento = new CartaoCredito(parcelas);

                break;

            case 3:

                pagamento = new CartaoDebito();

                formaPagamento = "CARTÃO DE DÉBITO";

                break;

            case 4:

                pagamento = new Dinheiro();

                formaPagamento = "DINHEIRO";

                break;

            default:

                System.out.println("\nForma de pagamento inválida.");

                return false;

        }

        pagamento.pagar(valor);

        System.out.println("Pagamento realizado com sucesso!");

        return true;

    }


}