package MyExceptions;

import Model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountsInfoNotFound extends Exception {
    public AccountsInfoNotFound() {
        super("Информация о счетах отсутствует!");
        try (FileWriter writer = new FileWriter("src/Report.txt", true)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            writer.write(localDateTime.format(DateTimeFormatter.ofPattern(Transaction.dayTimeFormat)) +
                    "|информация о счетах отсутствует\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
