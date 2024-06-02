import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Carro {
    private String placa;
    private LocalDateTime horarioEntrada;

    public Carro(String placa) {
        this.placa = placa;
        this.horarioEntrada = LocalDateTime.now();
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public String infoCarro(DateTimeFormatter formatter) {
        return "Placa: " + placa + ", Horário de Entrada: " + horarioEntrada.format(formatter);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "placa='" + placa + '\'' +
                ", horarioEntrada=" + horarioEntrada +
                '}';
    }
}