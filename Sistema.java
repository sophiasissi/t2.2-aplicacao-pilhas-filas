import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Sistema {
    private Fila fila;
    private int totalManobras;

    public Sistema(int capacidade) {
        this.fila = new Fila(capacidade);
        this.totalManobras = 0;
    }

    public void entrada(String placa) {
        Carro carro = new Carro(placa);
        if (fila.enfileira(carro)) {
            System.out.println("Carro " + carro.getPlaca() + " entrou às " + carro.getHorarioEntrada());
        } else {
            System.out.println("Estacionamento cheio.");
        }
    }

    public void sair(String placa) {
        LinkedList<Carro> tempQueue = new LinkedList<>();
        Carro carro = null;
        int manobras = 0;

        // Desenfileirar carros até encontrar o carro desejado ou esvaziar a fila
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

        // Recolocar os carros temporariamente removidos na fila
        while (!tempQueue.isEmpty()) {
            fila.enfileira(tempQueue.removeFirst());
        }

        // Se o carro foi encontrado, registrar a saída
        if (carro != null) {
            LocalDateTime horarioSaida = LocalDateTime.now();
            Duration permanencia = Duration.between(carro.getHorarioEntrada(), horarioSaida);
            System.out.println("Carro " + carro.getPlaca() + " saiu às " + horarioSaida + ". Tempo de permanência: " + permanencia.toMinutes() + " minutos. Manobras realizadas: " + manobras);
            totalManobras += manobras;
        } else {
            System.out.println("Carro " + placa + " não encontrado no estacionamento.");
        }
    }

    public void consulta(String placa) {
        int posicao = 1;
        int i = fila.primeiro;
        boolean encontrado = false;
        while (true) {
            if (fila.dados[i] != null && fila.dados[i].getPlaca().equals(placa)) {
                System.out.println("Carro " + placa + " está na posição " + posicao + " da fila. Entrou às " + fila.dados[i].getHorarioEntrada() + ".");
                encontrado = true;
                break;
            }
            if (i == fila.ultimo) break;
            i = fila.proxima(i);
            posicao++;
        }
        if (!encontrado) {
            System.out.println("Carro não encontrado.");
        }
    }

    public void relatorioCarro() {
        System.out.println("Relatório de Ocupação Atual do Estacionamento:");
        System.out.println(fila.toString());
    }

    public int getManobras() {
        return totalManobras;
    }
}