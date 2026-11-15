public class ListaDupla{
    private No inicio;
    private No fim;
    private int tamanho;

    public ListaDupla(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void inserir(String url){
        
        if (contem(url)){
            return;
        }

        No novo = new No(url);

        if (inicio == null){
            inicio = novo;
            fim = novo;
        } else{
            fim.proximo = novo;
            novo.anterior = fim;
            fim = novo;
        }

        tamanho++;
    }

    public boolean contem(String url){
        No atual = inicio;

        while (atual != null){
            if (atual.valor.equals(url)){
                return true;
            }

            atual = atual.proximo;
        }

        return false;
    }

    public void exibir(){
        if (inicio == null){
            System.out.println("Você ainda não adicionou nenhum site aos favoritos.");
            return;
        }

        No atual = inicio;

        while (atual != null){
            System.out.println(atual.valor);
            atual = atual.proximo;
        }
    }
}