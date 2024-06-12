package FileParser;

import MyExceptions.TransactionsFilesNotFoundException;
import Model.Transaction;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    public static String dayTimeFormat;

    public static ArrayList<Transaction> parse() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".txt");
        File files = new File("src/Input");
        Pattern transaction = Pattern.compile("\\S+\\|\\S+\\|\\S+");
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            ArrayList<File> inputFiles = new ArrayList<>();
            Collections.addAll(inputFiles, Objects.requireNonNull(files.listFiles(filter)));
            if (!inputFiles.isEmpty()) {
                for (File file : inputFiles) {
                    try (FileReader reader = new FileReader(file)) {
                        int i;
                        StringBuilder fileInfo = new StringBuilder();
                        while ((i = reader.read()) != -1) {
                            fileInfo.append((char) i);
                        }
                        if (fileInfo.isEmpty()) {
                            reader.close();
                            LocalDateTime localDateTime = LocalDateTime.now();
                            file.renameTo(new File("src/InvalidFiles/" + localDateTime.format(DateTimeFormatter
                                    .ofPattern(FileParser.dayTimeFormat)) + "_" + file.getName()));
                        } else {
                            Matcher matcher = transaction.matcher(fileInfo);
                            if (matcher.find()) {
                                matcher.reset();
                                while (matcher.find()) {
                                    String[] info = matcher.group().split("\\|");
                                    transactions.add(new Transaction(info[0], info[1], info[2], file.getName()));
                                }
                                reader.close();
                                LocalDateTime localDateTime = LocalDateTime.now();
                                file.renameTo(new File("src/Archive/" + localDateTime.format(DateTimeFormatter
                                        .ofPattern(FileParser.dayTimeFormat)) + "_" + file.getName()));
                            } else {
                                reader.close();
                                LocalDateTime localDateTime = LocalDateTime.now();
                                file.renameTo(new File("src/InvalidFiles/" + localDateTime.format(DateTimeFormatter
                                        .ofPattern(FileParser.dayTimeFormat)) + file.getName()));
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                throw new TransactionsFilesNotFoundException();
            }
        } catch (
                TransactionsFilesNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
        for (File file : files.listFiles()) {
            LocalDateTime localDateTime = LocalDateTime.now();
            file.renameTo(new File("src/InvalidFiles/" + localDateTime.format(DateTimeFormatter
                    .ofPattern("HH_mm_ss_yyyy_MM_dd")) + file.getName()));
        }
        return transactions;
    }
}
