import java.time.format.DateTimeFormatter;

public class Fila {
    private int primeiro;
    private int ultimo;
    private Carro[] dados;
    private int tamanho;

    public static final int CAPACIDADE_MINIMA = 10;

    public Fila(int capacidade) {
        dados = new Carro[capacidade];
        ultimo = dados.length - 1;
        primeiro = 0;
        tamanho = 0;
    }

    public Fila() {
        this(CAPACIDADE_MINIMA);
    }

    public int getPrimeiro() {
        return primeiro;
    }

    public int getUltimo() {
        return ultimo;
    }

    public Carro[] getDados() {
        return dados;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public boolean estaCheia() {
        return tamanho == dados.length;
    }

    int proxima(int pos) {
        return (pos + 1) % dados.length;
    }

    public boolean enfileira(Carro carro) {
        if (estaCheia()) return false;
        ultimo = proxima(ultimo);
        dados[ultimo] = carro;
        tamanho++;
        return true;
    }

    public Carro desenfileira() {
        if (estaVazia()) return null;
        Carro temp = dados[primeiro];
        primeiro = proxima(primeiro);
        tamanho--;
        return temp;
    }

    @Override
    public String toString() {
        if (estaVazia()) return "Fila vazia.";
        StringBuilder s = new StringBuilder();
        int i = primeiro;
        int posicao = 1;
        do {
            s.append("Posição ").append(posicao).append(": ")
             .append(dados[i].getPlaca()).append(", horário de entrada: ")
             .append(dados[i].getHorarioEntrada().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
             .append("\n");
            i = proxima(i);
            posicao++;
        } while (i != proxima(ultimo));
        return s.toString();
    }

    public String vetorToString() {
        if (estaVazia())
            return "_ _ _ _ _ _ _ _ _ _";
        String s = "";
        int i;
        if (primeiro <= ultimo) {
            for (i = 0; i < primeiro; i++)
                s += "_ ";
            for (i = primeiro; i <= ultimo; i++)
                s += dados[i] + " ";
            for (i = ultimo + 1; i < dados.length; i++)
                s += "_ ";
        } else {
            for (i = 0; i <= ultimo; i++)
                s += dados[i] + " ";
            for (i = ultimo + 1; i < primeiro; i++)
                s += "_ ";
            for (i = primeiro; i < dados.length; i++)
                s += dados[i];
        }
        return s;
    }
}