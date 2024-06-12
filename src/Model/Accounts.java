package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Accounts {
    private LinkedHashMap<String, String> validAccounts;
    private LinkedHashMap<String, String> invalidAccounts;

    public Accounts() {
        this.validAccounts = new LinkedHashMap<>();
        this.invalidAccounts = new LinkedHashMap<>();
        try (FileReader reader = new FileReader("src/Accounts.txt")) {
            int i;
            StringBuilder fileInfo = new StringBuilder();
            while ((i = reader.read()) != -1) {
                fileInfo.append((char) i);
            }
            Matcher matcher = Pattern.compile("\\S+\\|\\S+").matcher(fileInfo);
            if (matcher.find()) {
                matcher.reset();
                while (matcher.find()) {
                    String[] accountInfo = matcher.group().split("\\|");
                    if (validateAcc(accountInfo[0]) && validateBalance(accountInfo[1])) {
                        validAccounts.put(accountInfo[0], accountInfo[1]);
                    } else {
                        invalidAccounts.put(accountInfo[0], accountInfo[1]);
                    }
                }
            }
        } catch (IOException exceptionOne) {
            System.out.println(exceptionOne.getMessage());
            try (FileWriter writer = new FileWriter("src/Report.txt")) {
                LocalDateTime localDateTime = LocalDateTime.now();
                writer.write(localDateTime.format(DateTimeFormatter.ofPattern(Transaction.dayTimeFormat)) +
                        "|файл Accounts.txt отсутствует\n");
            } catch (IOException exceptionTwo) {
                System.out.println(exceptionTwo.getMessage());
            }
        }
    }

    public HashMap<String, String> getValidAccounts() {
        return validAccounts;
    }

    public HashMap<String, String> getInvalidAccounts() {
        return invalidAccounts;
    }

    public static boolean validateAcc(String accId) {
        Matcher matcher = Pattern.compile("\\d{5}-\\d{5}").matcher(accId);
        return matcher.matches();
    }

    public static boolean validateBalance(String accBalance) {
        Matcher matcher = Pattern.compile("\\d+").matcher(accBalance);
        return matcher.matches();
    }

    public void updateAccountInfo() {
        try (FileWriter writer = new FileWriter("src/Accounts.txt")) {
            for (Map.Entry<String, String> entry : validAccounts.entrySet()) {
                writer.write(entry.getKey() + "|" + entry.getValue() + "\n");
                writer.flush();
            }
            if (!invalidAccounts.isEmpty()) {
                writer.write("Невалидные номера счетов:\n");
                for (Map.Entry<String, String> entry : invalidAccounts.entrySet()) {
                    writer.write(entry.getKey() + "|" + entry.getValue() + "\n");
                    writer.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
