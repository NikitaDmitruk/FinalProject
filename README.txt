To run the program you need: JDK 17.0.11 or higher.
If desired, Intellij IDEA.

Copy the project to your PC to the folder C://Users/LocalUser/IdeaProjects/ and run it in Intellij IDEA.
The initial files of transactions and accounts have already been created.


Program Description.
This program is the final project of the first unit of the Java Developer course.
Program Description.
At the beginning of the program, Transaction.dayTimeFormat and FileParser.dayTimeFormat specify the format for displaying the date and time, which
will be written to the report file along with the data about the transactions and exceptions.
To start the program, you must enter 1 or 2 in the console, which will determine the further actions of the program. If you choose 1,
the program will perform the operation of parsing translation files from the Input folder, and when selecting 2, it will call the operation of outputting all translations from the Report.txt file.
from the Report.txt file.
If you select 1, the program creates an object of the Accounts class. When the object is created from the file Accounts.txt (stores accounts in the format of
ХХХХХХХХХ-ХХХХХХ|amount) the information about current accounts and funds on them is read out. The information is read out according to the specified template.
Information about valid accounts is added to the validAccounts collection, about invalid accounts to invalidAccounts. The information is recorded in LinkedHashMap.
The key is the account number and the value is the amount of funds on the account. If the Accounts.txt file is missing, an IOException is thrown and the information is stored in the LinkedHashMap.
IOException exception is thrown and the information is written to the Report.txt file with date and time.
Next, the program calls the static method parse of the FileParser class, which returns the collection ArrayList with objects of the
of the Transaction class.
The parse method collects all files from the Input folder into the collection. If there are no such files, the program throws an exception
TransactionsFilesNotFoundException and the message about it is written to the report.txt file with the date and time when it happened.
Next, the necessary files are searched and read out according to the template, and the read information about the accounts is added to the collection.
The collection contains objects of the Transaction class. The object stores information about the account from where the transfer should be made,
where and for what amount, as well as the name of the file from where it was read out. If in the process of reading the file it turns out that it is
empty or does not contain necessary information about transfers, it will be moved to the InvalidFiles folder, with the addition of the date and time at the beginning of its name.
the date and time when this occurred. If information has been retrieved from the file, it is moved to the Archive folder and its name is also changed.
Archive folder and its name is also changed with the addition of the date and time at the beginning of the file name (to eliminate the possibility of the file repeating).
file repetition). If a file with the same name is present in the folder, the file from the Input folder will disappear.
If there is no information about transactions (e.g. translation files are not in the Input folder), then the program
throws the TransactionsInfoNotFound exception because there will be no objects in the Transaction object collection.
If there is no information about accounts (for example, the Accounts.txt file does not exist), the program throws the exception
AccountsInfoNotFound. Also, all non- txt files are placed in the InvalidFiles folder.
Next, the checkTransactionFormat method is executed on each translation object. The method checks the correctness of the format of accounts and
The method returns boolean value. If the format of any of the accounts is incorrect, an exception is thrown.
InvalidAccountNameException, if the format of the amount is incorrect, then InvalidTransferAmountFormatException, if both accounts and amounts,
InvalidAccountNameAndTransferAmountException.
If the account and amount formats are correct, then the program further executes the completeTransaction method for each transaction.
The parameters of this method contain information about current accounts and amounts on them. Then the program checks if there are accounts
specified in the transaction and the presence of the necessary transfer amount on the account. In the case when both accounts exist and there are enough funds, the transfer is performed.
funds are sufficient, the transfer is performed, the information about it is recorded in the file-report in the format “date-time | file_1 |
transfer from XXXXX-XXXXXXX to YYYYYYY-YYYYYY 500 | successfully processed". When the transfer is executed, the information is updated
information about funds on the accounts is updated.
If there are insufficient funds, a NotEnoughMoneyException exception is thrown. If one of the accounts is missing
or both, an AccountNotFoundException is thrown.
After all transfers have been processed, the updateAccountInfo method is called. It overwrites the current account information in the
Account.txt file. If there are invalid accounts in the Account.txt file, they will be marked with the text “Invalid Accounts:”.
If you select 2, the program will display a list of all transfers from the account file in the console. FileReader takes the information from the file-report,
and according to the transaction template, all information about successful and unsuccessful transactions is output to the console.
Also the program information about all exceptions is written to the file-report with date and time.
The class diagram is shown in the file DependencyDiagram.png



Для запуска программы необходимо: JDK 17.0.11 и выше.
При желании Intellij IDEA.

Скопировать проект себе на ПК в папку C://Users/LocalUser/IdeaProjects/ и запустить его в Intellij IDEA.
Начальные файлы транзакций и счетов уже созданы.


Описание программы.
Данная программа является финальным проектом первого блока обучения курса Java разработчик.
Описание программы.
В начале программы Transaction.dayTimeFormat и FileParser.dayTimeFormat задают формат отображения даты и времени, которые
будут записываться в файл отчет вместе с данные о проведенных транзакциях и возникших исключениях.
Для запуска программы необходимо ввести в консоль 1 или 2, что определит дальнейшие действия программы. При выборе 1,
программа выполнит операцию парсинга файлов переводов из папки Input, а при выборе 2 вызовет операции вывода всех переводов
из файла-отчета Report.txt.
При выборе 1, программа создаем объект класс Accounts. При создании объекта из файла Accounts.txt (хранит счета в формате
ХХХХХ-ХХХХХ|сумма) вычитывается информация о текущих счетах и средствах на них. Информация вычитывается по заданному шаблону.
Информация о валидных счетах добавляется в коллекцию validAccounts, о невалидных в invalidAccounts. Информация записывается в LinkedHashMap.
Ключом является номер счета, а значением - размер средств на нем. Если файл Accounts.txt отсутствует, выбрасывается
исключение IOException и информация об это записывается в файл Report.txt с датой и временем.
Далее программа вызывает статический метод parse класса FileParser, которые возвращает коллекцию ArrayList с объектами
класса Transaction.
Метод parse собирает все файлы из папки Input в коллекцию. Если таких файлов нет, то программа выбрасывает исключение
TransactionsFilesNotFoundException и сообщение о нем записывается в файл report.txt с датой и временем, когда это произошло.
Далее нужные файлы перебираются и вычитываются по шаблону, а прочитанная информация о счетах добавляется в коллекцию.
Коллекция содержит объекты класса Transaction. Объект хранит себе информацию о счете откуда нужно осуществить перевод,
куда и на какую сумму, а также имя файла, откуда он был вычитан. Если в процессе вычитывания файла окажется, что он
пустой или не содержит необходимой информации о переводах, то он будет перемещен в папку InvalidFiles, с добавлением в
начале его имени даты и времени когда это произошло. Если из файла была получена информация, то он перемещается в папку
Archive и его имя так же изменяется с добавлением даты и времени в начале имени файла (для исключения возможности
повторения файла). Если в папке будет присутствовать файл с таким же именем, то файл из папки Input исчезнет.
Если информация о транзакциях отсутствует (например фйлов переводов нет в папке Input), то программа
выбрасывает исключение TransactionsInfoNotFound, т.к. в коллекции объектов Transaction не будет объектов.
Если информация о счетах отсутствует (например не существует файла Accounts.txt), то программа выбрасывает исключение
AccountsInfoNotFound. Также все файлы не txt формата помещаются в папку InvalidFiles.
Далее каждого объекта перевода выполняется метод checkTransactionFormat. Метод проверяет правильность формата счетов и
суммы перевода возвращает значение boolean. Если формат какого либо из счетов неверный, то выбрасывается исключение
InvalidAccountNameException, если неверный формат суммы, то InvalidTransferAmountFormatException, если и счета и суммы,
то InvalidAccountNameAndTransferAmountException.
Если формат счетов и суммы верный, то далее программа выполняет для каждой транзакции метод completeTransaction.
В параметры данного метода передается информация о текущих счетах и суммах на них. Далее проверяется наличие счетов
указанных в транзакции и наличие на счете необходимой суммы перевода. В случае, когда оба счета существуют и денежных
средств достаточно, выполняется перевод, информация об это записывается в файл-отчет в формате "дата-время | файл_1 |
перевод с XXXXX-XXXXX на YYYYY-YYYYY 500 | успешно обработан". При выполнении перевода, происходит обновления информации
о денежных средствах на счетах.
В случае, когда недостаточно средств, выбрасывается исключение NotEnoughMoneyException. Если отсутствует один из счетов
или оба, то AccountNotFoundException.
После обработки всех переводов, вызывается метод updateAccountInfo. Он перезаписывает актуальную информацию о счетах в
файл Account.txt. Если в файле Account.txt невалидные счета, то они будут помечены текстом "Невалидные счета:".
При выборе 2, программа выведет в консоль список всех переводов из файла-отчета. FileReader берет информацию из файл-отчет,
и по шаблону транзакции, вся информация об успешных и неуспешных транзакциях выводится в консоль.
Также программа информацию о всех исключениях записывается в файл-отчет с датой и временем.
Диаграмма классов приведена в файле DependencyDiagram.png





