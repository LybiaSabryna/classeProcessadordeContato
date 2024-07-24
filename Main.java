import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProcessadorDeContrato {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Número do contrato: ");

        LocalDate dataContrato;
        while (true) {
            System.out.print("Data do contrato (dd/MM/yyyy): ");
            try {
                String dataContratoString = scanner.nextLine();
                dataContrato = LocalDate.parse(dataContratoString, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Confirme a data novemente.");
            }
        }

        double valorTotal;
        while (true) {
            System.out.print("Valor total do contrato (apenas números): ");
            if (scanner.hasNextDouble()) {
                valorTotal = scanner.nextDouble();
                break;
            } else {
                System.out.println("Confirme o valor novamente.");
                scanner.next();
            }
        }

        int numeroMeses;
        while (true) {
            System.out.print("Número de meses para parcelamento: ");
            if (scanner.hasNextInt()) {
                numeroMeses = scanner.nextInt();
                if (numeroMeses > 0) {
                    break;
                } else {
                    System.out.println("Número de meses deve ser maior que zero. Por favor, tente novamente.");
                }
            } else {
                System.out.println("Número de meses inválido. Por favor, tente novamente.");
                scanner.next();
            }
        }

        scanner.nextLine();

        double taxaJuroMensal = 0.01;
        double taxaPagamento = 0.02;

        double valorParcelaBase = valorTotal / numeroMeses;

        System.out.println("\nParcelas:");
        for (int i = 1; i <= numeroMeses; i++) {
            LocalDate dataParcela = dataContrato.plusMonths(i);
            double valorParcelaComJuros = valorParcelaBase * (1 + taxaJuroMensal * i);
            double valorParcelaComTaxa = valorParcelaComJuros * (1 + taxaPagamento);

            System.out.printf("Parcela %d: Data: %s, Valor: %.2f%n", i, dataParcela.format(formatter), valorParcelaComTaxa);
        }

        scanner.close();
    }
}
