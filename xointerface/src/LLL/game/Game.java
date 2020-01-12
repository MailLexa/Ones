package LLL.game;

import javax.swing.*;

public class Game {
    private GameBoard board;//ссылка на игровое
    private GamePlayer[] gamePlayers = new GamePlayer[2];// массив игроков
    private int playersTurn = 0;  // индекс текущего игрока

    public Game(){
        this.board = new GameBoard(this);

    }

    public void initGame(){
        gamePlayers[0] = new GamePlayer(true, 'X'); // Создаем объекты (поле)
        gamePlayers[1] = new GamePlayer(false,'O');

    }

    //Метод передачи хода
    void passTurn() {
        if(playersTurn == 0)
            playersTurn = 1;
        else
            playersTurn = 0;
    }

    GamePlayer getCurrentPlayer(){ // Переменная , текущий игрок.
        return gamePlayers[playersTurn];
    }

    void showMessage (String messageText){
        JOptionPane.showMessageDialog(board, messageText);

    }
}
