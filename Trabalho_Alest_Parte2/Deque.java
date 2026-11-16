public class Deque{

    private No inicio;
    private No fim;
    private int tamanho;

    public Deque(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void inserirInicio(String url){
        No novo = new No(url);

        if (inicio == null){
            inicio = novo;
            fim = novo;
        } else{
            novo.proximo = inicio;
            inicio.anterior = novo;
            inicio = novo;
        }

        tamanho++;
    }

    public void inserirFim(String url){
        No novo = new No(url);

        if (fim == null){
            inicio = novo;
            fim = novo;
        } else{
            fim.proximo = novo;
            novo.anterior = fim;
            fim = novo;
        }

        tamanho++;
    }

    public String removerInicio(){
        if (inicio == null){
            return null;
        }

        String valorRemovido = inicio.valor;

        if (inicio == fim){
            inicio = null;
            fim = null;
        } else{
            inicio = inicio.proximo;
            // Desliga a referência ao nó que saiu.
            inicio.anterior = null;
        }

        tamanho--;
        return valorRemovido;
    }

    public String removerFim(){
        if (fim == null){
            return null;
        }

        String valorRemovido = fim.valor;

        if (inicio == fim){
            inicio = null;
            fim = null;
        } else{
            fim = fim.anterior;
            // Desliga a referência ao nó que saiu.
            fim.proximo = null;
        }

        tamanho--;
        return valorRemovido;
    }

    public int getTamanho(){
        return tamanho;
    }

    public boolean estaVazio(){
        return tamanho == 0;
    }

    public String exibirInicio(){
        if (inicio == null){
            return null;
        }

        return inicio.valor;
    }

    public String exibirFim(){
        if (fim == null){
            return null;
        }

        return fim.valor;
    }

    public void limpar(){
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    // As visitas são inseridas no fim; a exibição começa pela mais recente.
    public void exibirHistorico(){
        if (estaVazio()){
            System.out.println("Histórico vazio.");
            return;
        }
        System.out.print("Histórico: ");
        No atual = fim;
        while (atual != null){
            System.out.print(atual.valor);
            if (atual.anterior != null){
                System.out.print(" -> ");
            }
            atual = atual.anterior;
        }
        System.out.println();
    }
}
