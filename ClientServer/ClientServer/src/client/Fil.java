package client;
import javax.swing.*;
import java.io.*;

public class Fil extends Main{

    public  void main(String[] args) {
    }

    void TestFile (){// проверка существует ли файл

        try {
            File Fil = new File("history.txt");
            if (Fil.exists()) {
            }
            else {
                if (Fil.createNewFile())
                    System.out.println("File created");
                else
                    System.out.println("File already exists");
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    void ReadFile ( JTextArea ReadFile){//считывание данных из файла
    try {
        FileReader  fin=new FileReader("history.txt");
        int i=-1;
        int c;
        while((c=fin.read())!=-1){
            ReadFile.append(String.valueOf((char)c));
           // System.out.print((char)c+"\n");
        }
    }
    catch (Exception e){
        System.err.println(e);
    }
}

     void LoadFile (JTextArea Mes){ // загрузка данных в файл
        try {
            while  (Mes.getLineCount()>99) { //проверяем количество строк
                Mes.remove(0);
            }

            FileWriter fin = new FileWriter("history.txt", false); //запись в файл
            fin.write(Mes.getText().toString());
            fin.flush();
        }
        catch (Exception e){
            System.err.println(e);
        }

    }
}

