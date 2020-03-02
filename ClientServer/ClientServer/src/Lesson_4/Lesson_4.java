package Lesson_4;

public class Lesson_4 extends Thread{
    private final Object mon = new Object();
    String Letter= "A";
    Integer i=-2;

    public Lesson_4(){
        }

    public static void  main() {
        Lesson_4 w = new Lesson_4();
       Thread  t1 = new Thread(() -> {
            w.OutA();
        });
        Thread t2 = new Thread(() -> {
            w.OutB();
        });
        Thread t3 = new Thread(() -> {
            w.OutC();
        });
        t1.start();
        t2.start();
        t3.start();
    }

            void OutA() {
            synchronized (mon) {
                while(i<2) {
                    try {
                        while (Letter != "A") {
                            mon.wait();
                        }
                        System.out.print("A");
                        Letter = "B";
                        mon.notifyAll();
                    }
                    catch (InterruptedException e) {
                        System.err.println();
                    }
                }
                }
    }

    void OutB() {
        synchronized (mon){
            while(i<2) {
                try {
                    while (Letter != "B") {
                        mon.wait();
                    }
                    System.out.print("B");
                    Letter = "C";
                    mon.notifyAll();
                } catch (InterruptedException e) {
                    System.err.println();
                }
            }

        }
    }

        void OutC() {
            synchronized (mon){
                while(i<3) {
                    try {
                        while (Letter != "C") {
                            mon.wait();
                        }
                        System.out.print("C");
                        i = i + 1;
                        Letter = "A";
                        mon.notifyAll();
                    }
                catch (InterruptedException e){
                    System.err.println();
                }
                }
            }
        }
}
