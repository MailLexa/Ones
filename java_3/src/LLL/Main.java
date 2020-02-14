package LLL;
import java.io.*;
import java.util.*;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Integer arr1[] = {1, 2, 3, 4, 5, 6, 7};
        method_1(arr1,1,4);
        method_1_1(arr1,1, 4);

        Main.method_2_0(arr1);

        Box<Orange> or = new Box<Orange>();
        Box<Orange> or1 = new Box<>();
        Box<Apple> ap = new Box<>();
        Box<Apple> ap1 = new Box<>();
        or.boxadd(new Orange(),10);
        or1.boxadd(new Orange(),10);
        ap.boxadd(new Apple(),4);
        ap1.boxadd(new Apple(),4);
        System.out.println(or.getWeight());
        System.out.println(or.setcompare(or1));
        or.setbox(or1,5);
        or.setbox(ap,3);
        System.out.println(or.list.size());
        System.out.println(or1.list.size());
    }

    public static void method_1(Integer[] arr, int n1, int n2){
        System.out.println("Task 1.0: "+Arrays.toString(arr));
        Integer tem = arr[n1];
        arr[n1]=arr[n2];
        arr[n2]=tem;
        System.out.println(Arrays.toString(arr)+"\n");
}
    public static void method_1_1 (Object[] ar, int s1, int s2){
        System.out.println("Task 1.1: "+Arrays.toString(ar));
        Object arra = ar[s1];
        ar[s1] = ar[s2];
        ar[s2] = arra;
        System.out.print(Arrays.toString(ar) + "\n");
    }

    public static <Integer> void method_2_0(Integer[] ar){
        ArrayList <Integer> myarr = new ArrayList<Integer>();
        Collections.addAll(myarr, ar);
        System.out.println("Task 2 \n");
        for (int i=0 ; i<myarr.size();i++) {
            System.out.println( myarr.get(i));
        }
    }
}