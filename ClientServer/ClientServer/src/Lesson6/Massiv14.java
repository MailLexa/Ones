package Lesson6;

public class Massiv14 {
    Boolean Massivout(Integer[] mas){
        Integer a=0;
        Integer b=0;
        for (Integer i=0; i<mas.length;i++){
            if (mas[i]==1)
                a=1;
            if (mas[i]==4)
                b=1;

        }
        if ((a+b) == 2)
            return true;
        else
            return false;
    }
}
