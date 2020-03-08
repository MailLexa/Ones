package server;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private static Logger log = Logger.getLogger(Server.class.getName());
		// экземпляр нашего сервера
    private Server server;
		// исходящее сообщение
    private PrintWriter outMessage;
		// входящее собщение
    private Scanner inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 3443;
		// клиентский сокет
    private Socket clientSocket = null;
		// количество клиента в чате, статичное поле
    private static int clients_count = 0;

		// конструктор, который принимает клиентский сокет и сервер
    public ClientHandler(Socket socket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
		// Переопределяем метод run(), который вызывается когда
		// мы вызываем new Thread(client).start();
    @Override
    public void run() {
        try {
            while (true) {
								// сервер отправляет сообщение
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }

            while (true) {
                // Если от клиента пришло сообщение
                if (inMessage.hasNext()) {
                    System.out.println("\n Пришло сообщение");
                    log.info("Пришло сообщение");
                    String clientMessage = inMessage.nextLine();
                   // System.out.println(clientMessage + '1');
                    //System.out.println(Text1(clientMessage,1));

                    // System.out.println(Autin(Text1(clientMessage,1)));
										// если клиент отправляет данное сообщение, то цикл прерывается и 
										// клиент выходит из чата
                    if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        break;
                    }
										// выводим в консоль сообщение (для теста)
                    System.out.println(clientMessage);

										// отправляем данное сообщение всем клиентам
                    if (Autin(Text1(clientMessage,1)))

                    server.sendMessageToAllClients(Text1(clientMessage,2));
                }
								// останавливаем выполнение потока на 100 мс
                Thread.sleep(100);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            this.close();
        }
    }
		// отправляем сообщение
    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
		// клиент выходит из чата
    public void close() {
				// удаляем клиента из списка
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
    }

    public String Text1(String name, Integer che){
        String[] all = name.split("/");
        if (che==1) {
           return all[0];
        }
        if (che==2) {

            return all[1];
        }
        else{
            return "";
        }

    }
    public Boolean Autin(String name1){
        Connection c = null;
        Statement stmt = null;
        Boolean result = false;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM AUTO;" );



            while ( rs.next() ) {
                int id = rs.getInt("ID");
                String  login = rs.getString("Login");
                String password  = rs.getString("Pas");
                password+=login;
                if (password.equals(name1))
                    result=true;


                //System.out.println( password  );
                //System.out.println( name1  );
//
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return result;
    }


}
