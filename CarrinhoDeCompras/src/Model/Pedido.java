package Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
 * ==========================================================
 * CLASSE PEDIDO
 * ==========================================================
 *
 * Representa uma compra realizada por um cliente.
 *
 * Cada objeto Pedido reúne todas as informações necessárias
 * para registrar uma compra, desde os dados do cliente até
 * a forma de pagamento utilizada.
 *
 * Um pedido possui :
 * -> Número de identificação
 * -> O Cliente responsável pela compra;
 * -> Lista dos produtos (itens) adquiridos
 * -> Valor total da compra ;
 * -> Data e hora do pedido ;
 * -> Status do pedido ;
 * -> Forma de pagamento escolhida;
 * -> Informações de parcelamento (apenas para pagamento por cartão de cŕedito).
 *
 * O pedido é criado após a finalização do carrinho de compras
 * e posteriormente é utilizado pela camada de serviço
 * (PedidoService) para processar o pagamento
 *
 *
 */

public class Pedido {

    /*
     * ======================
     * ATRIBUTOS
     * ======================
     */

    private final int id;
    private Cliente cliente;
    private ArrayList<ItemCarrinho> itens;
    private double valorTotal;
    private final LocalDateTime dataPedido;
    private String status;
    private String formaPagamento;
    private int parcelas;
    private double valorParcela;


    /*
     * ======================
     * CONSTRUTOR
     * ======================
     */
    public Pedido(int id,
                  Cliente cliente,
                  ArrayList<ItemCarrinho> itens,
                  double valorTotal) {

        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.valorTotal = valorTotal;

        // Guarda a data da criação do pedido
        this.dataPedido = LocalDateTime.now();


        // Todo pedido começa pendente
        this.status = "PENDENTE";


        // Inicialmente nenhum pagamento foi definido
        this.formaPagamento = "NÃO INFORMADO";

    }

    /*
     * ======================
     * GETTERS
     * ======================
     */

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<ItemCarrinho> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    /*
     * ======================
     * SETTERS
     * ======================
     */

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItens(ArrayList<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }


    /*
     * ======================
     * FORMATAR DATA E HORA
     * ======================
     */

    private String getDataFormatada() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return dataPedido.format(formato);

    }


    /*
     * ======================
     * CONVERTER PARA STRING
     * ======================
     */

    @Override
    public String toString() {

        String pedido =
                "========== PEDIDO ==========\n" +
                        "Pedido Nº: " + id +
                        "\nCliente: " + cliente.getNome() +
                        "\nData: " + getDataFormatada() +
                        "\nStatus: " + status +
                        "\nPagamento: " + formaPagamento +
                        "\n\nItens:\n\n";

        // Percorre todos os itens do pedido
        for (ItemCarrinho item : itens) {

            pedido += item ;

        }

        // mostra o pedido com seu respectivo valor final
        pedido += "\nValor Total: R$ " +
                String.format("%.2f", valorTotal);


        //exibe as opções de parcelamento apenas quando o pagamento for crédito
        if (formaPagamento.equalsIgnoreCase("CARTÃO DE CRÉDITO")) {

            pedido += "\nParcelamento: " +
                    parcelas + "x de R$ " +
                    String.format("%.2f", valorParcela);

        }

        return pedido;

    }


}
