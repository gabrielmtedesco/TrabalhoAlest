import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Navegador navegador = new Navegador();
        Scanner entrada = new Scanner(System.in);
        boolean executando = true;

        // hasNextLine também permite encerrar normalmente no fim de um arquivo.
        while (executando && entrada.hasNextLine()) {
            String linha = entrada.nextLine().trim();
            if (linha.isEmpty()) {
                continue;
            }

            // Separa o comando do argumento, aceitando espaço ou tabulação.
            int separador = 0;
            while (separador < linha.length()
                    && !Character.isWhitespace(linha.charAt(separador))) {
                separador++;
            }
            String comando = linha.substring(0, separador).toUpperCase(Locale.ROOT);
            String url = linha.substring(separador).trim();
            boolean recebeUrl = comando.equals("VISITAR") || comando.equals("FAVORITAR");
            boolean semUrl = comando.equals("VOLTAR") || comando.equals("AVANCAR")
                    || comando.equals("ATUAL") || comando.equals("HISTORICO")
                    || comando.equals("FAVORITOS") || comando.equals("SAIR");

            if (recebeUrl && url.isEmpty()) {
                System.out.println("Uso: " + comando + " <url>");
                continue;
            }
            if (semUrl && !url.isEmpty()) {
                System.out.println("O comando " + comando + " não recebe argumentos.");
                continue;
            }

            switch (comando) {
                case "VISITAR":
                    navegador.visitar(url);
                    break;
                case "VOLTAR":
                    navegador.voltar();
                    break;
                case "AVANCAR":
                    navegador.avancar();
                    break;
                case "ATUAL":
                    navegador.atual();
                    break;
                case "HISTORICO":
                    navegador.exibirHistorico();
                    break;
                case "FAVORITAR":
                    navegador.favoritar(url);
                    break;
                case "FAVORITOS":
                    navegador.exibirFavoritos();
                    break;
                case "SAIR":
                    System.out.println("Encerrando.");
                    executando = false;
                    break;
                default:
                    System.out.println("Comando inválido: " + comando);
            }
        }
        entrada.close();
    }
}
