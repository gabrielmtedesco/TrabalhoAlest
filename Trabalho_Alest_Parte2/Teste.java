import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

// Testes separados da interface: execute com java Teste.
public class Teste {
    private static int verificacoes = 0;

    private static void conferir(String nome, boolean resultado) {
        if (!resultado) {
            throw new AssertionError("Falhou: " + nome);
        }
        verificacoes++;
        System.out.println("OK: " + nome);
    }

    // Captura a saída de uma ação para comparar com o resultado esperado.
    private static String capturar(Runnable acao) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream captura = new PrintStream(buffer, true, StandardCharsets.UTF_8);
        try {
            System.setOut(captura);
            acao.run();
        } finally {
            System.setOut(original);
            captura.close();
        }
        return buffer.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }

    public static void main(String[] args) {
        Deque deque = new Deque();
        conferir("deque começa vazio", deque.estaVazio() && deque.getTamanho() == 0);
        conferir("remoção em deque vazio", deque.removerInicio() == null && deque.removerFim() == null);
        deque.inserirInicio("B");
        deque.inserirInicio("A");
        deque.inserirFim("C");
        conferir("inserção nas duas extremidades", "A".equals(deque.exibirInicio())
                && "C".equals(deque.exibirFim()) && deque.getTamanho() == 3);
        conferir("remoção no início", "A".equals(deque.removerInicio()));
        conferir("remoção no fim", "C".equals(deque.removerFim()));
        conferir("último nó pelo fim", "B".equals(deque.removerFim()) && deque.estaVazio());
        deque.inserirFim("D");
        conferir("último nó pelo início", "D".equals(deque.removerInicio())
                && deque.exibirInicio() == null && deque.exibirFim() == null);
        deque.inserirFim("E");
        deque.limpar();
        conferir("limpar atualiza tamanho e extremidades", deque.getTamanho() == 0
                && deque.exibirInicio() == null && deque.exibirFim() == null);
        deque.inserirInicio("F");
        conferir("reutilização após limpar", "F".equals(deque.removerFim()));

        ListaDupla lista = new ListaDupla();
        lista.inserir("a.com");
        lista.inserir("b.com");
        lista.inserir("a.com");
        conferir("favoritos em ordem sem duplicatas",
                capturar(lista::exibir).equals("a.com -> b.com\n"));
        conferir("busca de favorito ausente", !lista.contem("c.com"));

        Navegador n = new Navegador();
        conferir("voltar sem histórico", capturar(n::voltar).equals("Não há páginas para voltar.\n"));
        conferir("avançar sem futuro", capturar(n::avancar).equals("Não há páginas para avançar.\n"));
        conferir("atual sem visitas", capturar(n::atual).equals("Nenhuma página visitada ainda.\n"));
        conferir("histórico vazio", capturar(n::exibirHistorico).equals("Histórico vazio.\n"));
        conferir("favoritos vazios", capturar(n::exibirFavoritos).equals("Favoritos: Nenhum favorito.\n"));
        capturar(() -> n.visitar("google.com"));
        conferir("não remove a única página", capturar(n::voltar).equals("Não há páginas para voltar.\n")
                && capturar(n::atual).equals("Atual: google.com\n"));
        capturar(() -> n.visitar("pucrs.br"));
        conferir("voltar recupera anterior", capturar(n::voltar).equals("Voltando para: google.com\n"));
        conferir("avançar recupera próxima", capturar(n::avancar).equals("Avançando para: pucrs.br\n"));
        conferir("movimentação não duplica visitas no histórico",
                capturar(n::exibirHistorico).equals("Histórico: pucrs.br -> google.com\n"));
        capturar(n::voltar);
        capturar(() -> n.visitar("nova.com"));
        conferir("nova visita limpa avanço", capturar(n::avancar).equals("Não há páginas para avançar.\n"));
        capturar(() -> n.visitar("nova.com"));
        conferir("histórico mantém visitas repetidas", capturar(n::exibirHistorico)
                .equals("Histórico: nova.com -> nova.com -> pucrs.br -> google.com\n"));
        capturar(() -> n.favoritar("a.com"));
        conferir("favorito duplicado avisa", capturar(() -> n.favoritar("a.com"))
                .equals("URL já favoritada: a.com\n"));
        capturar(() -> n.favoritar("b.com"));
        conferir("favoritos integrados em ordem", capturar(n::exibirFavoritos)
                .equals("Favoritos: a.com -> b.com\n"));
        capturar(() -> { n.visitar(""); n.visitar(null); n.visitar("duas urls"); });
        conferir("URL inválida não altera a página", capturar(n::atual).equals("Atual: nova.com\n"));
        capturar(() -> { n.favoritar(""); n.favoritar(null); n.favoritar("duas urls"); });
        conferir("URL inválida não altera favoritos", capturar(n::exibirFavoritos)
                .equals("Favoritos: a.com -> b.com\n"));

        Navegador limite = new Navegador();
        capturar(() -> {
            for (int i = 1; i <= 8; i++) {
                limite.visitar("p" + i);
            }
            for (int i = 0; i < 5; i++) {
                limite.voltar();
            }
        });
        conferir("cinco retornos chegam a p3", capturar(limite::atual).equals("Atual: p3\n"));
        conferir("sexto retorno bloqueado", capturar(limite::voltar).equals("Não há páginas para voltar.\n"));
        capturar(() -> { for (int i = 0; i < 5; i++) { limite.avancar(); } });
        conferir("cinco avanços chegam a p8", capturar(limite::atual).equals("Atual: p8\n"));
        conferir("sexto avanço bloqueado", capturar(limite::avancar).equals("Não há páginas para avançar.\n"));
        conferir("histórico completo apesar do limite", capturar(limite::exibirHistorico)
                .equals("Histórico: p8 -> p7 -> p6 -> p5 -> p4 -> p3 -> p2 -> p1\n"));
        System.out.println("Total: " + verificacoes + " verificações aprovadas.");
    }
}
