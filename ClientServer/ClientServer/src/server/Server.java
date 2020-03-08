package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;



public  class Server {
		// порт, который будет прослушивать наш сервер
    static final int PORT = 3443;
		// список клиентов, которые будут подключаться к серверу
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    private static Logger log = Logger.getLogger(Server.class.getName());




    public Server() {
				// сокет клиента, это некий поток, который будет подключаться к серверу
				// по адресу и порту
        Socket clientSocket = null;
				// серверный сокет
        ServerSocket serverSocket = null;
        try {
						// создаём серверный сокет на определенном порту
            serverSocket = new ServerSocket(PORT);
           System.out.println("Сервер запущен!");
           log.info("Сервер запущен ");
						// запускаем бесконечный цикл
            while (true) {
								// таким образом ждём подключений от сервера
                clientSocket = serverSocket.accept();
								// создаём обработчик клиента, который подключился к серверу
								// this - это наш сервер

								// каждое подключение клиента обрабатываем в новом потоке
                ClientHandler client = new ClientHandler(clientSocket, this);
               System.out.println("\n"+"Новый клиент подключился");
               log.info("Новый клиент подключился");
                clients.add(client);

                ExecutorService executorService = Executors.newFixedThreadPool(8);
                executorService.execute( client);
                executorService.shutdown();
              //  new Thread(client).start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("\nПроизошла ошибка");
            log.info("Произошла ошибка");
        }
        finally {
            try {
								// закрываем подключение
                clientSocket.close();
                System.out.println("Сервер остановлен");
                log.info("Сервер остановлен");
                serverSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();

            }
        }
    }
		
		// отправляем сообщение всем клиентам
    public void sendMessageToAllClients(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }

    }

		// удаляем клиента из коллекции при выходе из чата
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

}
