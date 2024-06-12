package MyExceptions;

import Model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvalidAccountNameAndTransferAmountException extends Exception {
    public InvalidAccountNameAndTransferAmountException(String accFrom, String accTo, String money, String fileName) {
        super("Ошибка формата суммы перевода и номера счёта " + accFrom + "!");
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
