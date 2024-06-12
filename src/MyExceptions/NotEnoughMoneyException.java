package MyExceptions;

import Model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String accFrom, String accTo, String money, String fileName) {
        super("Недостаточно средств на счёте " + accFrom + "!");
        try (FileWriter writer = new FileWriter("src/Report.txt",true)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            writer.write(localDateTime.format(DateTimeFormatter.ofPattern(Transaction.dayTimeFormat)) + "|" + fileName
                         + "|перевод с " + accFrom + " на " + accTo + "|" + money + "|ошибка во время обработки, " +
                         "недостаточно средств\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
