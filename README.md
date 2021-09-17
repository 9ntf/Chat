#Сетевой чат
____

##Серверная часть:
____
* Класс Server, ожидает покдлючение новых пользователей
* Класс Connection:
  * Заправшивает имя пользователя
  * В отдельном Thread'е отправляет сообщение всем подключившимся пользователям
* Класс Logger, шаблон Singleton, записывает все сообщения и события в файл log.txt
* Класс Settings, шаблон Singleton, берет настройки из файла settings.txt


##Клиентская чать:
____
* Класс User:
  * Подключается к серверу
  * Запрашивает имя пользователя
  * В отдельном Thread'е происходит отправка сообщений