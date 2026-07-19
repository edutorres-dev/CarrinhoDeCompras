package Model;

/*
 * ==========================================================
 * CLASSE CLIENTE
 * ==========================================================
 *
 * Responsável por representar um cliente cadastrado no
 * sistema de compras.
 *
 * Cada cliente possui:
 *
 * -> Identificador único (ID)
 * -> Nome
 * -> Email
 *
 * Os objetos desta classe são utilizados pelo Carrinho e
 * pelo Pedido para identificar o responsável pela compra,
 * possibilitando o registro e o acompanhamento dos pedidos
 * realizados.
 *
 */

public class Cliente {

    /*
     * ===================
     * ATRIBUTOS
     * ====================
     * */

    private int id;
    private String nome;
    private String email;

    /*
     * ===================
     * CONSTRUTOR
     * ====================
     * */
    public Cliente(int id, String nome, String email){
        this.id=id;
        this.nome=nome;
        this.email=email;
    }

    /*
     * ===================
     * GETTERS
     * ====================
     * */
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }

    /*
     * ===================
     *  SETTERS
     * ====================
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    /*
     * ===================
     * CONVERTE PARA STRING
     * ====================
     * */
    @Override
    public String toString(){
        return  "\nID: " + id +
                "\nNome: " + nome +
                "\nEmail: " + email;

    }
}
