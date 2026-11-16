public class Navegador {
    private static final int LIMITE = 5;
    private Deque avancar;
    private Deque voltar;
    private ListaDupla favoritos;
    private Deque historico;

    public Navegador() {
        avancar = new Deque();
        voltar = new Deque();
        favoritos = new ListaDupla();
        historico = new Deque();
    }

    // Aceita endereços como pucrs.br, sem exigir http://.
    private boolean urlValida(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        for (int i = 0; i < url.length(); i++) {
            if (Character.isWhitespace(url.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void visitar(String url) {
        if (!urlValida(url)) {
            System.out.println("URL inválida. Informe uma URL sem espaços.");
            return;
        }
        // O fim é o topo: página atual mais até cinco páginas anteriores.
        voltar.inserirFim(url);
        if (voltar.getTamanho() > LIMITE + 1) {
            voltar.removerInicio();
        }
        // Uma nova visita abandona o caminho de avanço anterior.
        avancar.limpar();
        historico.inserirFim(url);
        System.out.println("Visitando: " + url);
    }

    public void voltar() {
        // A última página restante é a atual e não pode ser removida aqui.
        if (voltar.getTamanho() <= 1) {
            System.out.println("Não há páginas para voltar.");
            return;
        }
        avancar.inserirFim(voltar.removerFim());
        System.out.println("Voltando para: " + voltar.exibirFim());
    }

    public void avancar() {
        if (avancar.estaVazio()) {
            System.out.println("Não há páginas para avançar.");
            return;
        }
        voltar.inserirFim(avancar.removerFim());
        System.out.println("Avançando para: " + voltar.exibirFim());
    }

    public void atual() {
        if (voltar.estaVazio()) {
            System.out.println("Nenhuma página visitada ainda.");
        } else {
            System.out.println("Atual: " + voltar.exibirFim());
        }
    }

    public void favoritar(String url) {
        if (!urlValida(url)) {
            System.out.println("URL inválida. Informe uma URL sem espaços.");
            return;
        }
        if (favoritos.contem(url)) {
            System.out.println("URL já favoritada: " + url);
            return;
        }
        favoritos.inserir(url);
        System.out.println("Favoritado: " + url);
    }

    public void exibirFavoritos() {
        System.out.print("Favoritos: ");
        favoritos.exibir();
    }

    public void exibirHistorico() {
        historico.exibirHistorico();
    }
}
