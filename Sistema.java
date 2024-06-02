import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Sistema {
    private Fila fila;
    private int totalManobras;
    private DateTimeFormatter formatter;

    public Sistema(int capacidade) {
        this.fila = new Fila(capacidade);
        this.totalManobras = 0;
        this.formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public int getManobras() {
        return totalManobras;
    }

    public void entrada(String placa) {
        Carro carro = new Carro(placa);
        if (fila.enfileira(carro)) {
            System.out.println("Carro de placa: " + carro.getPlaca() + ", entrou no horário das: " + carro.getHorarioEntrada().format(formatter));
        } else {
            System.out.println("Estacionamento está cheio.");
        }
    }

    public void sair(String placa) {
        LinkedList<Carro> tempQueue = new LinkedList<>();
        Carro carro = null;
        int manobras = 0;

        while (!fila.estaVazia()) {
            Carro current = fila.desenfileira();
            if (current.getPlaca().equals(placa)) {
                carro = current;
                break;
            } else {
                tempQueue.add(current);
                manobras++;
            }
        }
        while (!tempQueue.isEmpty()) {
            fila.enfileira(tempQueue.removeFirst());
        }
        if (carro != null) {
            LocalDateTime horarioSaida = LocalDateTime.now();
            Duration permanencia = Duration.between(carro.getHorarioEntrada(), horarioSaida);
            System.out.println("\nCarro de placa: " + carro.getPlaca() + ", saiu no horário das: " + horarioSaida.format(formatter) + ". \nTempo total de permanência: " + permanencia.toMinutes() + " min.\nManobras realizadas: " + manobras);
            totalManobras += manobras;
        } else {
            System.out.println("Carro não encontrado");
        }
    }

    public void consulta(String placa) {
        int posicao = 1;
        int i = fila.getPrimeiro();
        boolean encontrado = false;
        while (true) {
            if (fila.getDados()[i] != null && fila.getDados()[i].getPlaca().equals(placa)) {
                System.out.println("\nCarro de placa: " + placa + " está na posição " + posicao + " da fila. Horário de entrada: " + fila.getDados()[i].getHorarioEntrada().format(formatter) + ".");
                encontrado = true;
                break;
            }
            if (i == fila.getUltimo()) break;
            i = fila.proxima(i);
            posicao++;
        }
        if (!encontrado) {
            System.out.println("Carro não encontrado.");
        }
    }

    public void relatorioCarro() {
        System.out.println("\nRelatório sobre a ocupação atual: ");
        System.out.println(fila.toString());
    }
}