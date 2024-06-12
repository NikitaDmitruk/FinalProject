package MyExceptions;

import Model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionsInfoNotFound extends Exception {
    public TransactionsInfoNotFound() {
        super("Информация о транзакциях отсутствует!");
        try (FileWriter writer = new FileWriter("src/Report.txt", true)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            writer.write(localDateTime.format(DateTimeFormatter.ofPattern(Transaction.dayTimeFormat)) +
                    "|информация о транзакциях отсутствует\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
