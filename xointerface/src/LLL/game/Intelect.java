package LLL.game;

public class Intelect {



    public  int [] Intelecty(char Text, char Texttwo, GameBoard board) {
        char [][] arr = board.gameField;
        int [] number = {0,0,0,0,0,};
        int [] numbertwo = {0,-1,0,0,0,0};
        int[] rezult = {0,arr.length,arr.length,arr.length,arr.length,arr.length};  // результат четырех проверок
        int [] koordin = {-1,-1,-1,-1,-1,-1,-1,-1,-1}; // координаты массива
        int [] koordinateone = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        for (int a = 0; a < arr.length; a++) { //строки
            for (int b = 0; b < arr.length; b++) {
                if (arr[a][b]==Texttwo)
                    numbertwo[5]++;
                if (arr[a][b] == '\u0000') {
                    koordin[1] = a;
                    koordin[2] = b;
                    number[1]++;
                    if (numbertwo[5] == 0) {
                        if (b == arr.length - 1) {
                            if (rezult[1]>=arr.length  - number[0]) {
                                rezult[1] = arr.length  -number[0];
                                koordinateone[1] = koordin[1];
                                koordinateone[2] = koordin[2];
                            }
                        }

                    }
                }
                if (arr[a][b] == Text ) {
                    number[0]++;

                    if (numbertwo[5]== 0){
                        if (b==arr.length-1) {
                            number[1] = arr.length - number[0];
                            if (rezult[1]>=number[1] ){
                                rezult[1]=number[1];
                                koordinateone[1] = koordin[1];
                                koordinateone[2] = koordin[2];
                            }
                        }


                    }


                }



            }
            number[1] = 0;
            number[0] = 0;
            numbertwo[5] = 0;
        }
        if (koordinateone[1]==-1 || koordinateone[2]==-1){
            koordinateone[1] = koordin[1];
            koordinateone[2] = koordin[2];
        }

        number[0] = 0;
        number[1] = 0;
        numbertwo[5] = 0;

        for (int a = 0; a < arr.length; a++) {                       //столбцы. находим вертикальные столбцы без "y".
            for (int b = 0; b < arr.length; b++) {
                if (arr[b][a]==Texttwo)
                    numbertwo[5]++;
                if (arr[b][a] == '\u0000') {
                    koordin[3] = b;
                    koordin[4] = a;
                    number[1]++;
                    if (numbertwo[5] == 0) {
                        if (b== arr.length - 1) {
                            if ( rezult[2]>=arr.length  - number[0]) {
                                rezult[2] = arr.length  - number[0];
                                koordinateone[3] = koordin[3];
                                koordinateone[4] = koordin[4];
                            }
                        }

                    }
                }

                //проходимся по массиву
                if (arr[b][a] == Text ) {
                    number[0]++;


                    if (numbertwo[5]== 0) {
                        if (b == arr.length - 1) {
                            number[1] = arr.length  - number[0];
                            if (rezult[2]>=number[1] ){
                                rezult[2]=number[1];
                                koordinateone[3] = koordin[3];
                                koordinateone[4] = koordin[4];
                            }
                        }



                    }

                }






            }
            number[1]=0;
            numbertwo[5] = 0;
            number[0] = 0;
        }
        if (koordinateone[3]==-1 || koordinateone[4]==-1){
            koordinateone[3] = koordin[4];
            koordinateone[4] = koordin[3];
        }

        number[0] = 0;
        number[1] = 0;
        numbertwo[5] = 0;

        for (int w=0; w<arr.length; w++){
            if (arr[w][w] == Texttwo)
                numbertwo[5]++;
            if (arr[w][w] == '\u0000') {
                koordin[5] = w;
                koordin[6] = w;
                number[1]++;
                if (numbertwo[5] == 0) {
                    if (w == arr.length - 1) {
                        if (rezult[3]>=arr.length  - number[0])
                            rezult[3] = arr.length  - number[0];
                        koordinateone[5] = koordin[5];
                        koordinateone[6] = koordin[6];

                    }

                }
            }
            if (arr[w][w] == Text){
                number[0]++;


                if (numbertwo[5] == 0) {
                    if (w == arr.length - 1) {
                        if (rezult[3]>=arr.length  - number[0])
                            rezult[3] = arr.length  - number[0];

                    }

                }
            }
            if (koordin[5] ==-1 & koordin[6] == -1){
                if (arr[w][w] == '\u0000') {
                    number[1]++;
                    koordinateone[5] = koordin[5];
                    koordinateone[6] = koordin[6];
                }
            }





        }
        if (koordinateone[5]==-1 || koordinateone[6]==-1){
            koordinateone[5] = koordin[5];
            koordinateone[6] = koordin[6];
        }

        number[0] = 0;
        number[1] = 0;
        numbertwo[5] = 0;

        int tem = arr.length - 1;
        for (int w=0; w<arr.length; w++){
            if (arr[w][tem] == Texttwo)
                numbertwo[5]++;




            if (arr[w][tem] == '\u0000') {
                koordin[7] = w;
                koordin[8] = tem;
                number[1]++;
                if (numbertwo[5] == 0) {
                    if (w == arr.length - 1) {
                        if (rezult[4]>=arr.length  - number[0]) {
                            rezult[4] = arr.length  - number[0];
                            koordinateone[7] = koordin[7];
                            koordinateone[8] = koordin[8];
                        }
                    }

                }
            }
            if (arr[w][tem] == Text){
                number[0]++;

                if (numbertwo[5]==0) {
                    if (w == arr.length - 1) {
                        if (rezult[4]>=arr.length  - number[0])
                            rezult[4] = arr.length  - number[0];
                        koordinateone[7] = koordin[7];
                        koordinateone[8] = koordin[8];

                    }
                }

            }
            if (koordin[7] ==-1 & koordin[8] == -1){
                if (arr[w][tem] == '\u0000') {
                    number[1]++;
                    koordinateone[7] = koordin[7];
                    koordinateone[8] = koordin[8];
                }
            }



            tem--;
        }
        if (koordinateone[7]==-1 || koordinateone[8]==-1){
            koordinateone[7] = koordin[7];
            koordinateone[8] = koordin[8];
        }
        int numbtemp = 1;
        int ch = 1;
        int [] rezultat = {0,arr.length,0,0};
        //System.out.print(rezult[1] + " " + rezult[2] + " " + rezult[3] + " " + rezult[4] + " " + rezult[5] );
        //System.out.print("\n"+koordinateone[1] + " " + koordinateone[2] + " " + koordinateone[3] + " " + koordinateone[4] + " " + koordinateone[5]+ " " + koordinateone[6]+ " " + koordinateone[7]+ " " + koordinateone[8] );
        for (int v=1; v<=5;v++){
            if (rezultat[1]>rezult[v]){
                rezultat[1] = rezult[v];
                rezultat[2] =  koordinateone[numbtemp];
                rezultat[3] =  koordinateone[numbtemp+1];
                ch++;
            }else if (ch == 1 & v==5){
                rezultat[2] = koordinateone[1];
                rezultat[3] = koordinateone[2];
            }
            numbtemp+=2;
        }


        return rezultat;
    }


}
