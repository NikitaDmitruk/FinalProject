package Transaction;

import Account.Accounts;
import MyExceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Transaction {
    private String accountFrom;
    private String accountTo;
    private String money;
    private String fileName;
    public static String dayTimeFormat;

    public Transaction(String accountFrom, String getAccountTo, String money, String fileName) {
        this.accountFrom = accountFrom;
        this.accountTo = getAccountTo;
        this.money = money;
        this.fileName = fileName;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getGetAccountTo() {
        return accountTo;
    }

    public void setGetAccountTo(String getAccountTo) {
        this.accountTo = getAccountTo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "accountFrom='" + accountFrom + '\'' +
               ", getAccountTo='" + accountTo + '\'' +
               ", money='" + money + '\'' +
               ", fileName='" + fileName + '\'' +
               '}';
    }

    public void completeTransaction(HashMap<String, String> accounts) {
        try (FileWriter writer = new FileWriter("src/Report.txt", true)) {
            if (accounts.containsKey(accountFrom) && accounts.containsKey(accountTo)
                && Integer.parseInt(accounts.get(accountFrom)) >= Integer.parseInt(money)) {
                LocalDateTime localDateTime = LocalDateTime.now();
                accounts.put(accountFrom, String.valueOf(Integer.parseInt(accounts.get(accountFrom)) - Integer
                        .parseInt(money)));
                accounts.put(accountTo, String.valueOf(Integer.parseInt(accounts.get(accountTo)) + Integer
                        .parseInt(money)));
                writer.write(localDateTime.format(DateTimeFormatter.ofPattern(dayTimeFormat)) +
                             "|" + fileName + "|перевод с " + accountFrom + " на " + accountTo + "|"
                             + getMoney() + "|успешно обработан\n");
                System.out.println(localDateTime.format(DateTimeFormatter.ofPattern(dayTimeFormat)) +
                                   "|" + fileName + "|перевод с " + accountFrom + " на " + accountTo + "|"
                                   + getMoney() + "|успешно обработан\n");
            } else {
                if (accounts.containsKey(accountFrom) && accounts.containsKey(accountTo)
                    && Integer.parseInt(accounts.get(accountFrom)) < Integer.parseInt(money)) {
                    throw new NotEnoughMoneyException(accountFrom, accountTo, money, fileName);
                } else if (!accounts.containsKey(accountFrom) || !accounts.containsKey(accountTo)) {
                    if (!accounts.containsKey(accountFrom)) {
                        throw new AccountNotFoundException(accountFrom, accountTo, money, fileName, accountFrom);
                    } else {
                        throw new AccountNotFoundException(accountFrom, accountTo, money, fileName, accountTo);
                    }
                }
            }
        } catch (AccountNotFoundException | NotEnoughMoneyException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkTransactionFormat(Transaction transaction) {
        try (FileWriter writer = new FileWriter("src/Report.txt", true)) {
            if (Accounts.validateAcc(accountFrom) && Accounts.validateAcc(accountTo)
                && Accounts.validateBalance(money)) {
                return true;
            } else if (!Accounts.validateAcc(accountFrom) || !Accounts.validateAcc(accountTo)
                                                             && Accounts.validateBalance(money)) {
                throw new InvalidAccountNameException(accountFrom, accountTo, money, fileName, writer);
            } else if (Accounts.validateAcc(accountFrom) && Accounts.validateAcc(accountTo)
                       && !Accounts.validateBalance(money)) {
                throw new InvalidTransferAmountFormatException(accountFrom, accountTo, money, fileName);
            } else {
                throw new InvalidAccountNameAndTransferAmountException(accountFrom, accountTo, money, fileName);
            }
        } catch (InvalidAccountNameAndTransferAmountException | InvalidTransferAmountFormatException |
                 InvalidAccountNameException | IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
