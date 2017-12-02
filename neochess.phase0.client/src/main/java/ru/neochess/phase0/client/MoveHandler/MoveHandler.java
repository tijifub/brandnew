package ru.neochess.phase0.client.MoveHandler;
import ru.neochess.phase0.client.*;
import ru.neochess.phase0.client.Figure;
import java.util.HashMap;
import java.util.Map;




/**
 * Created by TiJi on 05.02.17.
 */
public class MoveHandler {

    int grabbed_piece, from_row, from_col, to_row, to_col;
    Figure figureAim = null;
    MoveLiting liting;

    ChessBoard chessBoard;
    Board board;

    public MoveHandler(ChessBoard c, Board b)
    {
        chessBoard = c;
        board =b;
    }

    public boolean figureTransform(int X, int Y) {


        Map<String, Integer> row_col = new HashMap();

        Figure selectFigure;
        Figure newFigure;

        from_row = (Y - board.gap) / board.cellsize;
        from_col = (X - board.gap) / board.cellsize;

        if (from_row < 0 || from_row > 9) return false;
        if (from_col < 0 || from_col > 9) return false;


        selectFigure = chessBoard.getBoard().getCellByIndex(from_row, from_col).getFigure();
        if (selectFigure.getRace().equals(chessBoard.clientState.sessionData.race) == false) return false;

        //обычная пешка
        if (selectFigure != null &&
                selectFigure.getCode().compareTo("O") == 0) {

            newFigure = chessBoard.fl.getFigureByCode("R"); //Боевая пешка
            newFigure.setState(selectFigure.getState());
            newFigure.setRace(selectFigure.getRace());

            row_col.put("row", from_row);
            row_col.put("col", from_col);
            newFigure.placeOnBoard(chessBoard.getBoard(), row_col);
            chessBoard.getBoard().saveFigure(newFigure);

            chessBoard.getBoard().removeFigure(selectFigure);

            selectFigure = null;
            newFigure.printCells();
            chessBoard.moveNotation = newFigure.printNotation();
            chessBoard.addTextArea1(chessBoard.moveNotation);
            newFigure = null;

            //myTurn = false; // изменнение пешки считается ходом.

        return true;}
        else {return false; }

    }

    public boolean moveBegin(int X, int Y)
    {

        from_row = (Y - board.gap) / board.cellsize;
        from_col = (X - board.gap) / board.cellsize;

        if (from_row < 0 || from_row > 9) return false;
        if (from_col < 0 || from_col > 9) return false;

        Figure tfigure = chessBoard.getBoard().getCellByIndex(from_row, from_col).getFigure();

        if(tfigure==null) return false;
        if (tfigure.getRace().equals(chessBoard.clientState.sessionData.race) == false) return false;


        chessBoard.setGrabbed_figure(tfigure);
        chessBoard.getGrabbed_figure().setMousePos(chessBoard.getBoard().getCellByIndex(from_row, from_col));

        //  if (chessBoard.grabbed_figure.getRace().equals(chessBoard.clientState.sessionData.race) == false) return false;

        liting = new MoveLiting(chessBoard , from_col, from_row);

        chessBoard.getBoard().removeFigure(chessBoard.getGrabbed_figure());

        return true;
    }

    public boolean moveEnd (int X, int Y)
    {
        Map<String,Integer> row_col = new HashMap();

        if(chessBoard.getGrabbed_figure() == null) return false;

        to_row = (Y - board.gap) / board.cellsize;
        to_col = (X - board.gap) / board.cellsize;

        if (to_row < 0 || to_row > 9 || to_col < 0 || to_col > 9) {
//
            row_col.put("row", from_row);
            row_col.put("col", from_col);

            chessBoard.getGrabbed_figure().placeOnBoard(chessBoard.getBoard(), row_col);

            chessBoard.setGrabbed_figure(null);

            return false;
        }

        if (from_row == to_row && from_col == to_col)

         {
             chessBoard.getBoard().saveFigure(chessBoard.getGrabbed_figure());

            row_col.put("row", from_row);
            row_col.put("col", from_col);
             chessBoard.getGrabbed_figure().placeOnBoard(chessBoard.getBoard(), row_col);

             chessBoard.setGrabbed_figure(null);
             chessBoard.repaint();
            return false;
        }

       // if (chessBoard.setNoRules() == false)


            chessBoard.getBoard().saveFigure(chessBoard.getGrabbed_figure());

            row_col.put("row", to_row);
            row_col.put("col", to_col);

            chessBoard.getGrabbed_figure().placeOnBoard(chessBoard.getBoard(), row_col);

        chessBoard.getGrabbed_figure().printCells();
        chessBoard.moveNotation = chessBoard.getGrabbed_figure().printNotation();
        chessBoard.addTextArea1(chessBoard.moveNotation);

        chessBoard.setGrabbed_figure(null);
     return true;
    }

    public boolean menuKill (int X, int Y)
    {
        Map<String,Integer> row_col = new HashMap();

        to_row = (Y - board.gap) / board.cellsize;
        to_col = (X - board.gap) / board.cellsize;

        figureAim = chessBoard.getBoard().getCellByIndex(to_row, to_col).getFigure();

        if (figureAim == null) return false;
        else if (figureAim.getRace().equals(chessBoard.clientState.sessionData.race) == true) return false;
        else return true;

    }

    public boolean moveKill (int X, int Y)
    {
        Map<String,Integer> row_col = new HashMap();

         if (figureAim == null) return false;
        else
        {
            figureAim.printCells();
            chessBoard.moveNotation = figureAim.printNotation() + " убит";
            chessBoard.addTextArea1(chessBoard.moveNotation);
            chessBoard.getBoard().removeFigure(figureAim);
            return true;
        }

    }

    public boolean isLegalMove ()
    {
        return true;
    }

}
