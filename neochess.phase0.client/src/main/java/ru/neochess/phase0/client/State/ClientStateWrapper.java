package ru.neochess.phase0.client.State;
import ru.neochess.phase0.client.ChessBoard;
import ru.neochess.phase0.client.ChessServerConnection;
import ru.neochess.phase0.client.SessionData.SessionData;
import ru.neochess.phase0.client.CheMessage.ChessMessage.NeoCheMessage;
/**
 * Created by TiJi on 04.01.17.
 */
public class ClientStateWrapper {
    public ClientState currentState;
    public ChessBoard chessBoard;
    public  SessionData sessionData;
    ChessServerConnection serverconnection;


    public char myRace;

    public ClientStateWrapper(ChessBoard c) {

        setCurrent(new StateReady());
        chessBoard = c;
        serverconnection = new ChessServerConnection(chessBoard , this);
        sessionData = new SessionData();

    }

    public void setCurrent( ClientState s ) { currentState = s;
        currentState.setWrapper(this);

    }
    public ClientState getCurrent( ) { return  currentState;}

  /*  synchronized public void processMSG(String line) {

        if (line.charAt(0) == '@') {

            processCommand(chessBoard.chessclient, line);
            return;
        }

        currentState.receiveMove( chessBoard, line);
       // chessBoard.decodeBoard(line);
      //  chessBoard.repaint();

    }*/

    synchronized public void processMSG(NeoCheMessage message) {

       switch (message.getState()) {

           case "steady": currentState.receiveConfirm(message); break;
           case "move":
             //  chessBoard.addTextArea1(chessBoard.grabbed_figure.printNotation());
               currentState.receiveMove(chessBoard, message.getBoard(),message.getMove());

               break;
           case "end": currentState.receiveState("END"); chessBoard.addTextArea1("GAME END"); break;
       }

    }

   /* public void processCommand(ChessClient chessclient, String command){
//пока так -  исправить!!!

        switch (command){
            case "@BLACK":
                myRace = 'B';
                currentState.recieveColor(myRace);
                break;
            case "@WHITE":
                myRace = 'W';
                currentState.recieveColor(myRace);
                break;
            case "@ERROR":
                currentState.receiveState("ERROR");
                break;
            case "@GameEND":
                currentState.receiveState("END");
                chessBoard.setInitialBoard();
                break;
        }
    }*/

 /*   public void sendMSG (String line) {
        try {
            serverconnection.send(line);
            serverconnection.send("@TOKEN");
        } catch (Exception ex) {

        }
    }*/

  /*  public void sendST (String line) {
        try {
           serverconnection.send(line);
        } catch (Exception ex) {

        }
    }*/

    public void sendToServer(NeoCheMessage message)
    {
        serverconnection.sendMessage(message);

    }

}