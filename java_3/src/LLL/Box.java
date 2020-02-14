package LLL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Box <S extends Fruit> {
    public   ArrayList<S> list = new ArrayList<>();
    String name ="" ;
    private S ob; // почему не передается Apple или Orange ?
    public Box(){


    }

    public Float getWeight( ){
        Float wei = 0.0f;
        for (S o : list) {
            wei += o.getWeight();
        }
        return wei;

    }

    public Boolean setcompare(Box n){
        if (Math.round(getWeight())==Math.round(n.getWeight()))
            return true;
        else return false;
    }

    public void setbox(Box s, Integer col){
        if (name.equals(s.name)){
            if (col<s.list.size()) {
                list.addAll(s.list);
                for (int b=1; b<=s.list.size()-col; b++)
                    list.remove(0);
                for (int b=1; b<=col; b++)
                    s.list.remove(0);



            }
        }
    }

    public void boxadd(S Fruit_1, Integer col){
        if (name.equals("")){
            for (Integer i=1; i<=col; i++) {
                list.add(Fruit_1);
            }
            name = Fruit_1.getname();
        }

    }

}
