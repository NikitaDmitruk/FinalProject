package MyExceptions;

import Transaction.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionsFilesNotFoundException extends Exception {
    public TransactionsFilesNotFoundException() {
        super("В папке Input отсутствуют файлы формата txt");
        try (FileWriter writer = new FileWriter("src/Report.txt", true)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            writer.write(localDateTime.format(DateTimeFormatter.ofPattern(Transaction.dayTimeFormat)) +
                         "|в папке Input отсутствуют файлы формата txt\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
