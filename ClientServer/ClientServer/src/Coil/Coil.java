package Coil;

public class Coil {
    int count;
    double startdiametr;
    double enddiametr;
    double diametrwire;




    public Coil (int count, double startdiametr, double diametrwire){
        this.count = count;
        this.startdiametr = startdiametr;

        this.diametrwire = diametrwire;
    }
    public void calculation (){

        double calculation = 0.0;
        for (Integer i=0;i<=count;i++){
            calculation =startdiametr *2*3.14+calculation;
            startdiametr = startdiametr + diametrwire;
        }
        System.out.println(calculation);
        System.out.println("\n"+startdiametr);
    }
}
