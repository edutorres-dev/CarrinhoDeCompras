package Model;

import java.util.ArrayList;

/*
 * ==================================================
 * CLASSE CARRINHO
 * ==================================================
 *
 * Representa o carrinho de compras de um cliente.
 *
 * Responsabilidade:
 *
 * -> Armazenar os itens adicionados ao carrinho.
 *
 *
 * Todas as regras de negócio (adicionar produtos,
 * remover produtos, alterar quantidades, calcular
 * total etc.) ficam na classe CarrinhoService.
 *
 */

public class Carrinho {

    /*
     * =====================
     * ATRIBUTO
     * ====================
     */

    private ArrayList<ItemCarrinho> itens;

    /*
     * ========================
     * CONSTRUTOR
     * ========================
     */

    public Carrinho() {

        itens = new ArrayList<>();

    }

    /*
     * ==========================
     * GETTER
     * ==========================
     */

    public ArrayList<ItemCarrinho> getItens() {

        return itens;

    }

}