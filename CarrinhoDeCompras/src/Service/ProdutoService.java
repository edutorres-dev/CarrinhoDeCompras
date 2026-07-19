package Service;

import Model.Produto;
import Model.ProdutoDigital;
import Model.ProdutoFisico;

import java.util.ArrayList;
import java.util.Scanner;


/*
 * ==========================================================
 * CLASSE PRODUTOSERVICE
 * ==========================================================
 *
 * Responsável por gerenciar todas as operações relacionadas
 * aos produtos cadastrados no sistema.
 *
 * Suas principais responsabilidades são:
 *
 * • Cadastrar novos produtos;
 * • Editar produtos existentes;
 * • Remover produtos;
 * • Listar todos os produtos cadastrados;
 * • Buscar produtos pelo ID;
 * • Carregar produtos padrão para testes;
 * • Gerenciar a geração automática dos IDs dos produtos.
 *
 * Para realizar essas operações, a classe utiliza:
 *
 * • Uma lista de objetos Produto para armazenar os produtos;
 * • Um Scanner para receber as entradas do usuário;
 * • O polimorfismo para manipular produtos físicos e
 *   digitais através da classe abstrata Produto.
 *
 * Durante o cadastro e a edição dos produtos, esta classe
 * identifica o tipo de produto informado pelo usuário e
 * instancia a classe correspondente (ProdutoFisico ou
 * ProdutoDigital), tratando também seus atributos
 * específicos.
 *
 */

public class ProdutoService {

    /*
     * =========================
     * ATRIBUTOS
     * =========================
     */

    // Lista de produtos do sistema
    private ArrayList<Produto> produtos;

    // Scanner utilizado para entrada de dados
    private Scanner scanner;

    /*
     * Responsável por gerar automaticamente
     * o ID dos produtos.
     */
    private int proximoId;

    /*
     * =========================
     * CONSTRUTOR
     * =========================
     *
     * Recebe a lista de produtos e o Scanner já criados
     * pela classe Menu.
     *
     * Dessa forma toda a aplicação utiliza a mesma lista
     * de produtos.
     */
    public ProdutoService(ArrayList<Produto> produtos, Scanner scanner) {

        this.produtos = produtos;
        this.scanner = scanner;

        proximoId = 1;


    }

    /*
     * =========================
     * GETTERS E SETTERS
     * =========================
     */

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    /*
     * =========================
     * BUSCAR PRODUTO POR ID
     * =========================
     *
     * Percorre toda a lista procurando o ID informado.
     *
     * Se encontrar, retorna o produto.
     * Caso contrário retorna null.
     */
    public Produto buscarProdutoPorId(int id) {

        for (Produto produto : produtos) {

            if (produto.getId() == id) {

                return produto;

            }

        }

        return null;

    }

    /*
     * =========================
     * LISTAR PRODUTOS
     * =========================
     */

    public void listarProdutos() {

        // Verifica se existe produto cadastrado
        if (produtos.isEmpty()) {

            System.out.println("\nNenhum produto cadastrado.");

            return;

        }

        System.out.println("\n========== PRODUTOS ==========\n");

        for (Produto produto : produtos) {

            System.out.println(produto);

            System.out.println("----------------------------");

        }

    }

    /*
     * =========================
     * CADASTRAR PRODUTO
     * =========================
     */

    public void cadastrarProduto() {

        scanner.nextLine(); // limpa o enter que ficou do menu

        System.out.println("\n====== CADASTRO DE PRODUTO ======\n");

        int id = proximoId++;

        System.out.println("ID: " + id);

        System.out.println("Nome:");
        String nome = scanner.nextLine();

        System.out.println("Preço: ");
        double preco = scanner.nextDouble();

        System.out.println("Estoque: ");
        int estoque = scanner.nextInt();

        System.out.println("\nTipo do Produto");

        System.out.println("1 - Produto Físico");
        System.out.println("2 - Produto Digital");

        System.out.print("Escolha: ");

        int tipo = scanner.nextInt();

        if (tipo == 1) {

            System.out.print("Peso (kg): ");

            double peso = scanner.nextDouble();

            produtos.add(

                    new ProdutoFisico(
                            id,
                            nome,
                            preco,
                            estoque,
                            peso
                    )

            );

        }

        else if (tipo == 2) {

            System.out.print("Tamanho do Arquivo (MB): ");

            double tamanho = scanner.nextDouble();

            produtos.add(

                    new ProdutoDigital(
                            id,
                            nome,
                            preco,
                            estoque,
                            tamanho
                    )

            );

        }

        else {

            System.out.println("Tipo inválido.");

            return;

        }

        System.out.println("\nProduto cadastrado com sucesso!");

    }

    /*
     * =========================
     * EDITAR PRODUTO
     * =========================
     */

    public void editarProduto() {

        System.out.println("\n====== EDITAR PRODUTO ======\n");

        /*
         * Verifica se existem produtos cadastrados.
         */

        if (produtos.isEmpty()) {

            System.out.println("\nNenhum produto cadastrado.");

            return;

        }

        /*
         * Exibe todos os produtos para que o usuário
         * possa visualizar seus IDs antes de editar.
         */

        listarProdutos();

        /*
         * Solicita o ID do produto.
         */

        System.out.print("\nDigite o ID do produto que deseja editar: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produto = buscarProdutoPorId(id);

        /*
         * Verifica se o produto existe.
         */

        if (produto == null) {

            System.out.println("\nProduto não encontrado.");

            return;

        }

        /*
         * Exibe os dados atuais do produto.
         */

        System.out.println("\nProduto Atual:");

        System.out.println(produto);

        /*
         * Solicita os novos dados.
         */

        System.out.print("\nNovo Nome: ");
        produto.setNome(scanner.nextLine());

        System.out.print("Novo Preço: ");
        produto.setPreco(scanner.nextDouble());

        System.out.print("Novo Estoque: ");
        produto.setEstoque(scanner.nextInt());

        /*
         * Caso seja um Produto Físico,
         * também permite alterar o peso.
         */

        if (produto instanceof ProdutoFisico) {

            ProdutoFisico fisico = (ProdutoFisico) produto;

            System.out.print("Novo Peso (kg): ");

            fisico.setPeso(scanner.nextDouble());

        }

        /*
         * Caso seja um Produto Digital,
         * permite alterar o tamanho do arquivo.
         */

        else if (produto instanceof ProdutoDigital) {

            ProdutoDigital digital = (ProdutoDigital) produto;

            System.out.print("Novo Tamanho do Arquivo (MB): ");

            digital.setTamanhoArquivo(scanner.nextDouble());

        }

        System.out.println("\nProduto atualizado com sucesso!");

    }

    /*
     * =========================
     * REMOVER PRODUTO
     * =========================
     */

    public void removerProduto() {

        System.out.println("\n====== REMOVER PRODUTO ======\n");

        System.out.print("Digite o ID do produto: ");

        int id = scanner.nextInt();

        Produto produto = buscarProdutoPorId(id);

        if (produto == null) {

            System.out.println("Produto não encontrado.");

            return;

        }

        produtos.remove(produto);

        System.out.println("\nProduto removido com sucesso!");

    }

    /*
     * =========================
     * CARREGAR PRODUTOS PADRÃO ( TESTE DESENVOLVIMENTO)
     * =========================
     *
     * Método utilizado para popular o sistema
     * automaticamente Durante os testes.
     */

    public void carregarProdutosPadrao() {

        produtos.add(new ProdutoFisico(
                1,
                "Mouse Gamer",
                150,
                10,
                0.8
        ));

        produtos.add(new ProdutoFisico(
                2,
                "Teclado Mecânico",
                300,
                15,
                1.2
        ));

        produtos.add(new ProdutoFisico(
                3,
                "Monitor Gamer",
                1200,
                8,
                5.4
        ));

        produtos.add(new ProdutoDigital(
                4,
                "Curso Java",
                799,
                999,
                3500
        ));

        produtos.add(new ProdutoDigital(
                5,
                "Curso Spring Boot",
                999,
                999,
                4200
        ));

    }

}
