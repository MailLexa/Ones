package server;

import client.ClientWindow;

public class Main {



    public  static void main(String[] args) {



        Thread myThready = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                Server server = new Server();
            }
        });
        myThready.start();	//Запуск потока

        System.out.println("Главный поток завершён...");

        {
            ClientWindow  clientWindow = new ClientWindow();
       // ClientWindow clientWindow_1 = new ClientWindow();
    }

}
}