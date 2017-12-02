package ru.neochess.phase0.client.State;

import ru.neochess.phase0.client.ChessBoard;

import javax.swing.*;
import ru.neochess.phase0.client.CheMessage.ChessMessage.NeoCheMessage;
import ru.neochess.phase0.client.CheMessage.ChessMessage;
/**
 * Created by TiJi on 03.12.16.
 */
public class StateReady extends State  implements ClientState {
   // private String UserName;

    public StateReady() {

       if (UserName.isEmpty()) {
            UserName = JOptionPane.showInputDialog("Ваше имя");
        }

    }

    @Override
    public void sendMove(String board, String move) {

    }

    @Override
    public void receiveMove(ChessBoard chessboard, String line, String move) {

    }

    @Override
    public void receiveState(String state) {

        switch (state)
        {
            case "ERROR": wrapper.setCurrent(new StateError()); break;
            case "END": finishGame(); break;
        }

    }

    @Override
    public void receiveConfirm(NeoCheMessage msg) {
        wrapper.sessionData.gameID = msg.getSessionId();
        wrapper.chessBoard.addTextArea1(msg.getUser(0).getName() + " vs " + msg.getUser(1).getName());

        if (msg.getUser(0).getName().equals(UserName))
        {
            wrapper.sessionData.userID = msg.getUser(0).getId();
            wrapper.sessionData.userName = msg.getUser(0).getName();
            wrapper.sessionData.race = msg.getUser(0).getRace();

           wrapper.sessionData.enemyID = msg.getUser(1).getId();
           Enemy = wrapper.sessionData.enemyName = msg.getUser(1).getName();

        }

       else if (msg.getUser(1).getName().equals(UserName))
        {
            wrapper.sessionData.userID = msg.getUser(1).getId();
            wrapper.sessionData.userName = msg.getUser(1).getName();
            wrapper.sessionData.race = msg.getUser(1).getRace();

            wrapper.sessionData.enemyID = msg.getUser(0).getId();
            Enemy = wrapper.sessionData.enemyName = msg.getUser(0).getName();
        }

        if (wrapper.sessionData.race.equals("W"))
        {
            wrapper.setCurrent(new StateMove());
            System.out.println("I am P");
            wrapper.chessBoard.chessclient.setTitle( UserName + " (Люди) vs " + Enemy + " (Животные)" );
            JOptionPane.showMessageDialog(null, "Это NeoChess! Вам досталась раса людей",  UserName + ". Вы играете против " + Enemy, JOptionPane.PLAIN_MESSAGE);

        }
        else if (wrapper.sessionData.race.equals("B")) {
            wrapper.setCurrent(new StateWait());
            System.out.println("I am A");
            wrapper.chessBoard.chessclient.setTitle( UserName + " (Животные) vs " + Enemy + " (Люди)") ;
            JOptionPane.showMessageDialog(null, "Это NeoChess! Вам досталась раса животных", UserName + ", Вы играете против " + Enemy, JOptionPane.PLAIN_MESSAGE);

        }
        wrapper.chessBoard.repaint();
        wrapper.chessBoard.setInitialBoard();

    }

    @Override
    public void sendState() {

       wrapper.sessionData.userName = UserName;

      ChessMessage.User.Builder user = ChessMessage.User.newBuilder();
        user.setName(UserName);

        ChessMessage.NeoCheMessage.Builder messageBuilder = ChessMessage.NeoCheMessage.newBuilder();
        messageBuilder.addUser(user);
        messageBuilder.setState("ready");
        ChessMessage.NeoCheMessage message =  messageBuilder.build();
        wrapper.sendToServer(message);

        wrapper.chessBoard.chessclient.setTitle("|NeoChess| " + UserName + " ждет соперника...");

    }

    @Override
    public void recieveColor( char color) {

    }

        @Override
        public void finishGame() {
            wrapper.setCurrent(new StateEnd());

        }



}
