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
            fim.proximo = null;
        }

        tamanho--;
        return valorRemovido;
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

    public void exibirHistorico(){
        if (estaVazio()){
            System.out.println("Historico vazio.");
            return;
        }

        No atual = fim;
        String resultado = "Historico: ";

        while (atual != null){
            resultado += atual.valor;

            if (atual.anterior != null){
                resultado += " -> ";
            }

            atual = atual.anterior;
        }

        System.out.println(resultado);
    }
    
}