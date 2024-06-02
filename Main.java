public class Main {
    public static void main(String[] args) {
        Sistema estacionamento = new Sistema(3);

        // Entrada de carros
        estacionamento.entrada("ABC-0123");
        estacionamento.entrada("DEF-4567");
        estacionamento.entrada("GHI-8910");
        estacionamento.entrada("JKL-1648");
        //Estacionamento cheio não suporta mais a entrada de carros

        // Relatório de carros
        estacionamento.relatorioCarro();

        // Saida de carros
        estacionamento.sair("DEF-4567");

        // Relatório de carros depois da saída de um carro
        estacionamento.relatorioCarro();
        
        // Consulta de carro
        estacionamento.consulta("ABC-0123");

        estacionamento.sair("AAA-1234");
        estacionamento.sair("CCC-9101");
    }
}
