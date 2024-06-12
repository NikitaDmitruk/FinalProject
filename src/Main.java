import Model.Accounts;
import FileParser.FileParser;
import MyExceptions.AccountsInfoNotFound;
import MyExceptions.TransactionsInfoNotFound;
import Model.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Transaction.dayTimeFormat = "HH:mm:ss yyyy-MM-dd";
        FileParser.dayTimeFormat = "HH_mm_ss_yyyy_MM_dd";
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Введите 1 для вызова операции парсинга файлов перевода из папки Input
                Введите 2 для вызова операции вывода списка всех переводов из файла-отчета Report.txt
                """);
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();
            if (option == 1) {
                try {
                    Accounts accounts = new Accounts();
                    ArrayList<Transaction> transactions = FileParser.parse();
                    if (!transactions.isEmpty()) {
                        if (!accounts.getValidAccounts().isEmpty()) {
                            for (Transaction transaction : transactions) {
                                if (transaction.checkTransactionFormat(transaction)) {
                                    transaction.completeTransaction(accounts.getValidAccounts());
                                }
                            }
                            accounts.updateAccountInfo();
                        } else {
                            throw new AccountsInfoNotFound();
                        }
                    } else {
                        throw new TransactionsInfoNotFound();
                    }
                } catch (AccountsInfoNotFound | TransactionsInfoNotFound e) {
                    System.out.println(e.getMessage());
                }
            } else if (option == 2) {
                try (FileReader reader = new FileReader("src/Report.txt")) {
                    int i;
                    StringBuilder report = new StringBuilder();
                    while ((i = reader.read()) != -1) {
                        report.append((char) i);
                    }
                    System.out.println(report);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Вы ввели неправильное число!");
            }
        } else {
            System.out.println("Вы ввели не целое число!");
        }
    }
}