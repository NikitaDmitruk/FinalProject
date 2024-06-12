package MyExceptions;

import Model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String accFrom, String accTo, String money, String fileName, String notFoundAcc) {
        super("Ошибка, счет " + notFoundAcc + " не найден!");
        try (FileWriter writer = new FileWriter("src/Report.txt", true)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            writer.write(localDateTime.format(DateTimeFormatter.ofPattern(Transaction.dayTimeFormat)) + "|" + fileName
                         + "|перевод с " + accFrom + " на " + accTo + "|" + money + "|ошибка во время обработки, " +
                         "неверно указан номер счёта и сумма перевода\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
