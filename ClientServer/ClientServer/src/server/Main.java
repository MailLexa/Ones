package server;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

import client.ClientWindow;
import Lesson_4.Lesson_4;
import Coil.Coil;

public class Main {

    public  static void main(String[] args) throws Exception{
        //ClientWindow  clientWindow = new ClientWindow();
       //     Thread myThready = new Thread(new Runnable()
     //   {
      //      public void run() //Этот метод будет выполняться в побочном потоке
       //     {
       //         Server server = new Server();
       //     }
      //  });
      //  myThready.start();	//Запуск потока
       // System.out.println("Главный поток завершён...");
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.execute(new Runnable() {
                public void run() {
                    new Server();
                }
            });
            executorService.execute(new Runnable() {

                public void run() {
                    new ClientWindow();
                }
            });
          //  executorService.execute(new Runnable(){  new Server());}
        //    executorService.execute((Runnable) new ClientWindow());
             executorService.shutdown();
             Lesson_4 w = new Lesson_4();
             Lesson_4.main();
         //   Coil r = new Coil(130,0.0,0.05);
          //  r.calculation();



       // ClientWindow clientWindow_1 = new ClientWindow();


    }

}