package Lesson6;

import java.io.IOException;

public class Massiv {

    Integer numb = 0;
    Integer [] masout ;


   public  Integer [] OutMassiv (Integer[] mas){

        try{
            for(int i = 0 ; i<mas.length; i++){
             if (mas[i]==4){
                 numb = i;

            }
        }
            masout=new Integer[(mas.length-numb-1)];
            if (numb != 0){
                for (int i=0; mas.length>numb+1;i++){
                    masout[i] = mas[numb+1];
                    numb++;
                }
            }else throw new IOException("I am Exception Alpha!");

        }
        catch (IOException e){
            System.out.println("Ошибка");
        }
        return masout;

    }
}
