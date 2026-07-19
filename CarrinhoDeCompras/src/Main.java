import View.Menu;

/*
 * ==========================================================
 * CLASSE Main
 * ==========================================================
 *
 * Classe responsável por iniciar a execução da aplicação.
 *
 * Esta classe contém o método principal (main), que é o
 * ponto de entrada de qualquer aplicação Java.
 *
 * Sua responsabilidade é apenas inicializar o sistema,
 * criando o menu principal e iniciando o fluxo de execução
 * da aplicação.
 *
 * Durante sua execução, esta classe:
 *
 * -> Cria uma instância da classe Menu;
 * -> Inicializa todos os objetos necessários para o sistema;
 * -> Inicia o menu principal;
 * -> Mantém a aplicação em funcionamento até que o usuário
 *    escolha a opção de sair.
 *
 * Nenhuma regra de negócio é implementada nesta classe.
 * Toda a lógica da aplicação é delegada para a classe Menu
 * e, posteriormente, para as classes da camada Service.
 */

public class Main {

    public static void main(String[] args) {

        // incializando o Menu
        Menu menu = new Menu();

        //executando o sistema
        menu.executar();


    }
}
