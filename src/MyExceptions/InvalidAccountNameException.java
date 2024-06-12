package MyExceptions;

import Model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvalidAccountNameException extends Exception {
    public InvalidAccountNameException(String accFrom, String accTo, String money, String fileName,FileWriter writer) {
        super("Ошибка формата счёта " + accFrom + "!");
        try  {
            LocalDateTime localDateTime = LocalDateTime.now();
            writer.write(localDateTime.format(DateTimeFormatter.ofPattern(Transaction.dayTimeFormat)) + "|" + fileName
                         + "|перевод с " + accFrom + " на " + accTo + "|" + money + "|ошибка во время обработки, " +
                         "неверно указан номер счета\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
