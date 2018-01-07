package ru.neochess.phase0.client.MoveHandler;

import ru.neochess.phase0.client.*;
import ru.neochess.phase0.client.Figure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TiJi on 05.02.17.
 */
public class MoveHandler {

    int from_row, from_col, to_row, to_col;
    Figure figureAim = null;
    MoveLiting liting;
    Figure selectFigure = null;

    ChessBoard chessBoard;
    Board board;

    public MoveHandler(ChessBoard c, Board b) {
        chessBoard = c;
        board = b;
    }

    public int getCol(int X) {
        int col = (X - board.gap) / board.cellsize;
        if (board.race.equals("B"))
            col = board.cellnum - 1 - col;

        return col;
    }

    public int getRow(int Y) {
        int row = (Y - board.gap) / board.cellsize;
        if (board.race.equals("B"))
            row = board.cellnum - 1 - row;

        return row;
    }

    public Figure getFigure(int X, int Y) {
        from_row = getRow(Y);
        from_col = getCol(X);

        if (!checkBoarders(from_col, from_row)) return null;

        Figure f = chessBoard.getBoard().getCellByIndex(from_row, from_col).getFigure();

        return f;
    }

    public Boolean checkBoarders(int col, int row) {
        if (row < 0 || row > (board.cellnum - 1) || col < 0 || col > (board.cellnum - 1)) return false;
        else return true;
    }

    public boolean figureTransform(int X, int Y) {


        Map<String, Integer> row_col = new HashMap();

        Figure selectFigure;
        Figure newFigure;

        selectFigure = getFigure(X, Y);

        if (selectFigure.getRace().equals(chessBoard.getBoard().race) == false) return false;

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

            return true;
        } else {
            return false;
        }

    }

    public boolean moveBegin(int X, int Y) {
        Figure tfigure;

        if ((tfigure = getFigure(X, Y)) == null) return false;

        if (!(tfigure.getRace().equals(chessBoard.getBoard().race))) return false;

        from_row = getRow(Y);
        from_col = getCol(X);

        chessBoard.setGrabbed_figure(tfigure);
        chessBoard.getGrabbed_figure().setMousePos(chessBoard.getBoard().getCellByIndex(from_row, from_col));

        liting = new MoveLiting(chessBoard, from_col, from_row);

        chessBoard.getBoard().removeFigure(chessBoard.getGrabbed_figure());

        return true;
    }

    public boolean moveEnd(int X, int Y) {
        Map<String, Integer> row_col = new HashMap();

        if (chessBoard.getGrabbed_figure() == null) return false;

        to_row = getRow(Y);
        to_col = getCol(X);

        if (!checkBoarders(to_col, to_row)) {
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

       // Arrays.asList(chessBoard.getMoveList()).contains()


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

    public boolean menuKill(int X, int Y) {
        Map<String, Integer> row_col = new HashMap();

        to_row = getRow(Y);
        to_col = getCol(X);

        figureAim = chessBoard.getBoard().getCellByIndex(to_row, to_col).getFigure();

        if (figureAim == null) return false;
        else if (figureAim.getRace().equals(chessBoard.getBoard().race) == true) return false;
        else return true;

    }

    public boolean menuShortCastling(int X, int Y) {
        Map<String, Integer> row_col = new HashMap();
        // Figure selectFigure;

        from_row = getRow(Y);
        from_col = getCol(X);

        selectFigure = chessBoard.getBoard().getCellByIndex(to_row, to_col).getFigure();

        if (selectFigure == null) return false;
        else if (!(selectFigure.getRace().equals(chessBoard.getBoard().race))) return false;
        else if (!(selectFigure.getCode().equals("G") || selectFigure.getCode().equals("I"))) return false;
        else return true;

    }

    public boolean menuLongCastling(int X, int Y) {
        Map<String, Integer> row_col = new HashMap();
        // Figure selectFigure;

        from_row = getRow(Y);
        from_col = getCol(X);

        selectFigure = chessBoard.getBoard().getCellByIndex(to_row, to_col).getFigure();

        if (selectFigure == null) return false;
        else if (!(selectFigure.getRace().equals(chessBoard.getBoard().race))) return false;
        else if (!selectFigure.getCode().equals("I")) return false;
        else return true;

    }

    public boolean moveKill(int X, int Y) {
        Map<String, Integer> row_col = new HashMap();

        if (figureAim == null) return false;
        else {
            figureAim.printCells();
            chessBoard.moveNotation = figureAim.printNotation() + " убит";
            chessBoard.addTextArea1(chessBoard.moveNotation);
            chessBoard.getBoard().removeFigure(figureAim);
            return true;
        }

    }

    public boolean moveShortCastling(int X, int Y) {
        Map<String, Integer> row_col = new HashMap();

        if (selectFigure == null) return false;
        else {
            selectFigure.printCells();
            chessBoard.moveNotation = "0-0 короткая рокировка";
            chessBoard.addTextArea1(chessBoard.moveNotation);

            chessBoard.getBoard().removeFigure(selectFigure);
            chessBoard.getBoard().saveFigure(selectFigure);

            to_row = from_row;

            if (selectFigure.getRace().equals("B"))
                to_col -= 2;
            else
                to_col += 3;

            row_col.put("row", to_row);
            row_col.put("col", to_col);

            selectFigure.placeOnBoard(chessBoard.getBoard(), row_col);


            if (selectFigure.getRace().equals("B"))
                from_col -= 3;
            else
                from_col += 4;

            selectFigure = chessBoard.getBoard().getCellByIndex(from_row, from_col).getFigure();
            chessBoard.getBoard().removeFigure(selectFigure);
            chessBoard.getBoard().saveFigure(selectFigure);

            if (selectFigure.getRace().equals("B"))
                to_col += 1;
            else
                to_col -= 1;

            row_col.put("col", to_col);
            selectFigure.placeOnBoard(chessBoard.getBoard(), row_col);
            selectFigure = null;
            return true;
        }

    }

    public boolean moveLongCastling(int X, int Y) {
        Map<String, Integer> row_col = new HashMap();

        if (selectFigure == null) return false;
        else {
            selectFigure.printCells();
            chessBoard.moveNotation = "0-0-0 длинная рокировка";
            chessBoard.addTextArea1(chessBoard.moveNotation);

            chessBoard.getBoard().removeFigure(selectFigure);
            chessBoard.getBoard().saveFigure(selectFigure);

            to_row = from_row;
            to_col -= 3;
            row_col.put("row", to_row);
            row_col.put("col", to_col);

            selectFigure.placeOnBoard(chessBoard.getBoard(), row_col);

            from_col -= 5;

            selectFigure = chessBoard.getBoard().getCellByIndex(from_row, from_col).getFigure();
            chessBoard.getBoard().removeFigure(selectFigure);
            chessBoard.getBoard().saveFigure(selectFigure);

            to_col += 1;
            row_col.put("col", to_col);
            selectFigure.placeOnBoard(chessBoard.getBoard(), row_col);
            selectFigure = null;
            return true;
        }

    }

    public boolean isLegalMove() {
        return true;
    }

}
