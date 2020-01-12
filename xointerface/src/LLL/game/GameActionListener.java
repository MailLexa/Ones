package LLL.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;
    private GameBoard BoardGam;
    private Intelect Intelectboard;

    public GameActionListener(int row, int cell, GameButton gButton){
        this.row = row;
        this.cell = cell;
        this.button = gButton;
         //BoardGam  = new GameBoard();
         Intelectboard = new Intelect();


    }

    @Override
    public void actionPerformed(ActionEvent  e) {
        GameBoard board = button.getBoard();
        GameBoard BoardGam= button.getBoard();

        if(board.isTurnable(row, cell)){
            updateByPlayersData(board);

            if(board.isFull()){
                board.getGame().showMessage("Ничья");
                board.emptyField();
            }
            else{
                //updateByAiData(board);
                intelect( BoardGam);

            }

        }
        else{
            board.getGame().showMessage("Некорректный ход");
        }

    }

    private void updateByPlayersData(GameBoard board){
        board.updateGameField(row,cell );

        button.setText(Character.toString((board.getGame().getCurrentPlayer().getPlayerSign())));

        if(board.checkWin()){
            button.getBoard().getGame().showMessage("Вы выиграли");
            board.emptyField();
        }
        else{
            board.getGame().passTurn();
        }
    }

    private void intelect( GameBoard boardn){
        int [] rezult = Intelectboard.Intelecty('X', 'O', boardn);
        int [] rezulttemp = Intelectboard.Intelecty('O', 'X', boardn);

        //if (rezult[1]<rezulttemp[1]  ){
         //   input(2, rezult[2] ,rezult[3]);

       // }else if (rezult[1]>rezulttemp[1] && rezult[1]!=0 ){
            //input(2, rezulttemp[2] ,rezulttemp[3]);
         if (rezult[1]==rezulttemp[1] || rezult[1]==1 ) {
             rezulttemp[2] = rezult[2];
             rezulttemp[3] = rezult[3];

         }
         if (boardn.isTurnable(rezulttemp[2], rezulttemp[3])) {
             boardn.updateGameField(rezulttemp[2], rezulttemp[3]);

             int cellIndex = boardn.dimension*rezulttemp[2]+rezulttemp[3];
             boardn.getButton(cellIndex).setText(Character.toString(boardn.getGame().getCurrentPlayer().getPlayerSign()));

             if(boardn.checkWin()){
                 button.getBoard().getGame().showMessage("Компьютер выиграл");
                 boardn.emptyField();


             }
             else{
                 boardn.getGame().passTurn();
             }

             if(boardn.isFull()){
                 boardn.getGame().showMessage("Ничья");
                 boardn.emptyField();
             }

         }
         else {
             GameBoard boardb = button.getBoard();
             updateByAiData(boardb);
         }

    }

    private void updateByAiData (GameBoard board){ // ход компа
        int x, y;
        Random rnd = new Random();

        do{
            x = rnd.nextInt(GameBoard.dimension);
            y = rnd.nextInt(GameBoard.dimension);

        }
        while(!board.isTurnable(x,y));


        board.updateGameField(x, y);

        int cellIndex = GameBoard.dimension*x+y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if(board.checkWin()){
            button.getBoard().getGame().showMessage("Компьютер выиграл");
            board.emptyField();


        }
        else{
            board.getGame().passTurn();
        }

        if(board.isFull()){
            board.getGame().showMessage("Ничья");
            board.emptyField();
        }


    }
}
