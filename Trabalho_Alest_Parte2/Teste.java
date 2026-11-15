
public class Teste {

    public static void main(String[] args) {

        Navegador navegador = new Navegador();

        // Teste 1: Historico vazio
        navegador.exibirHistorico();

        // Teste 2: Visitas normais
        navegador.visitar("pucrs.br");
        navegador.visitar("google.com");
        navegador.exibirHistorico();

        // Teste 3: Voltar e avancar
        navegador.voltar();
        navegador.avancar();
        navegador.exibirHistorico();

        // Teste 4: URL repetida
        navegador.visitar("pucrs.br");
        navegador.exibirHistorico();
    }
}

