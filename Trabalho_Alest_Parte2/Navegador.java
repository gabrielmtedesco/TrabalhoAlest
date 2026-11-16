public class Navegador{
    private Deque avancar;
    private Deque voltar;
    private String paginaAtual;
    private ListaDupla favoritos;
    private Deque historico;
    private int operacoes;

    public Navegador(){
        this.avancar = new Deque();
        this.voltar = new Deque();
        this.paginaAtual = null;
        this.favoritos = new ListaDupla();
        this.historico = new Deque();
        this.operacoes = 0;
    }

    public void visitar(String url){
        if (paginaAtual != null){
            voltar.inserirFim(paginaAtual);
        }

        paginaAtual = url;

        avancar.limpar();

        historico.inserirFim(url);

        System.out.println("Visitando: " + url);
    }

    public void voltar(){
        if (operacoes >=5){
            System.out.println("Você atingiu o limite de operações (5).");
            return;
        }

        if (voltar.estaVazio()){
            System.out.println("Não há páginas para voltar.");
            return;
        }

        avancar.inserirFim(paginaAtual);
        paginaAtual = voltar.removerFim();

        operacoes++;

        System.out.println("Voltando para: " + paginaAtual);
    }

    public void avancar(){
        if (operacoes >=5){
            System.out.println("Você atingiu o limite de operações (5).");
            return;
        }

        if (avancar.estaVazio()){
            System.out.println("Não há páginas para avançar.");
            return;
        }

        voltar.inserirFim(paginaAtual);
        paginaAtual = avancar.removerFim();

        operacoes++;

        System.out.println("Avançando para: " + paginaAtual);
    }

    public void atual(){
        if (paginaAtual == null){
            System.out.println("Nenhuma página visitada ainda.");
        } else{
            System.out.println("Página atual: " + paginaAtual);
        }
    }

    public void favoritar(String url){
        if (favoritos.contem(url)){
            System.out.println("O site " + url + " já está nos favoritos.");
            return;
        }
        favoritos.inserir(url);
        System.out.println("Você favoritou: " + url);
    }

    public void exibirFavoritos(){
        System.out.println("Favoritos:");
        favoritos.exibir();
    }

    public void exibirHistorico(){
        historico.exibirHistorico();
    }



}