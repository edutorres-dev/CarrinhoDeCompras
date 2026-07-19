package Pagamento;

/*
 * ==========================================================
 * INTERFACE PAGAMENTO
 * ==========================================================
 *
 * Define o contrato que deve ser seguido por todas as formas
 * de pagamento disponíveis no sistema.
 *
 * Qualquer classe que implemente esta interface deve fornecer
 * sua própria implementação do método pagar(), aplicando as
 * regras específicas do respectivo meio de pagamento.
 *
 * Atualmente, esta interface é implementada pelas classes:
 *
 * • CartaoCredito;
 * • CartaoDebito;
 * • Dinheiro;
 * • Pix.
 *
 * O uso desta interface permite que o sistema trabalhe com
 * diferentes formas de pagamento de maneira uniforme,
 * utilizando polimorfismo e reduzindo o acoplamento entre as
 * classes da aplicação.
 *
 */

public interface Pagamento {
    // Todo pagamento deve implementar este método
    boolean pagar(double valor);
}
