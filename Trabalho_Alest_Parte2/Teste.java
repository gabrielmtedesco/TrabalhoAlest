
public class Teste {

    public static void main(String[] args) {

        Navegador navegador = new Navegador();

        System.out.println("===== TESTE 1: ESTADO INICIAL =====");
        navegador.atual();
        navegador.voltar();
        navegador.avancar();
        navegador.exibirHistorico();
        navegador.exibirFavoritos();

        System.out.println("\n===== TESTE 2: VISITAS =====");
        navegador.visitar("google.com");
        navegador.visitar("pucrs.br");
        navegador.visitar("github.com");
        navegador.atual();
        navegador.exibirHistorico();

        System.out.println("\n===== TESTE 3: NAVEGACAO =====");
        navegador.voltar();   // 1
        navegador.voltar();   // 2
        navegador.voltar();   // Nao conta
        navegador.avancar();  // 3
        navegador.avancar();  // 4
        navegador.voltar();   // 5
        navegador.avancar();  // Bloqueado
        navegador.atual();

        System.out.println("\n===== TESTE 4: HISTORICO =====");
        navegador.visitar("google.com");
        navegador.exibirHistorico();

        System.out.println("\n===== TESTE 5: FAVORITOS =====");
        navegador.favoritar("google.com");
        navegador.favoritar("pucrs.br");
        navegador.favoritar("github.com");

        navegador.favoritar("google.com");
        navegador.favoritar("pucrs.br");

        navegador.exibirFavoritos();

        System.out.println("\n===== TESTE 6: AVANCO INVALIDADO =====");
        Navegador outro = new Navegador();

        outro.visitar("site1.com");
        outro.visitar("site2.com");
        outro.voltar();
        outro.visitar("site3.com");
        outro.avancar();
        outro.atual();
        outro.exibirHistorico();
    }
}
