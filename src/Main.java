import Account.Accounts;
import FileParser.FileParser;
import Transaction.Transaction;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Accounts accounts = new Accounts();
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
                ArrayList<Transaction> transactions = FileParser.parse();
                for (Transaction transaction : transactions) {
                    if (transaction.checkTransactionFormat(transaction)) {
                        transaction.completeTransaction(accounts.getValidAccounts());
                    }
                }
                accounts.updateAccountInfo();
            } else if (option == 2) {

            } else {
                System.out.println("Вы ввели неправильное число!");
            }
        } else {
            System.out.println("Вы ввели не целое число!");
        }
    }
}