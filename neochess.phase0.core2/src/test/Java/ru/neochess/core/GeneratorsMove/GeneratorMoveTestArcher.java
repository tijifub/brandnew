package ru.neochess.core.GeneratorsMove;

import org.junit.Test;
import ru.neochess.core.Board;
import ru.neochess.core.CellBoard;
import ru.neochess.core.Figure;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveTestArcher {

    Board board;
    private CellBoard getCellEndLine() {
        board = new Board();

        return board.getCellByIndex(2, 0);
    }


    private CellBoard getDisposition(Figure leftUpFigure, Figure upFigure,
                                     Figure rightUpFigure, Figure centerFigure) {

        board = new Board();
        CellBoard leftUp, up, rightUp, center;

        leftUp = board.getCellByIndex(4, 4);
        leftUp.setFigure(leftUpFigure);

        rightUp = board.getCellByIndex(6, 6);
        rightUp.setFigure(rightUpFigure);

        up = board.getCellByIndex(5, 4);
        up.setFigure(upFigure);

        center = board.getCellByIndex(5, 2);
        center.setFigure(centerFigure);
        return center;
    }
    private CellBoard getDisposition(Integer x, Integer y, Figure leftUpFigure, Figure upFigure,
                                     Figure rightUpFigure, Figure centerFigure) {

        board = new Board();
        CellBoard leftUp, up, rightUp, center;

        leftUp = board.getCellByIndex(4, 4);
        leftUp.setFigure(leftUpFigure);

        rightUp = board.getCellByIndex(6, 6);
        rightUp.setFigure(rightUpFigure);

        up = board.getCellByIndex(7, 7);
        up.setFigure(upFigure);

        center = board.getCellByIndex(x, y);
        center.setFigure(centerFigure);
        return center;
    }

    private CellBoard getDispositionFree(int x , int y , Figure centerFigure) {

        board = new Board();
        CellBoard center = board.getCellByIndex(x, y);
        center.setFigure(centerFigure);
        return center;

    }

    @Test
    public void testGetMoveArcher() throws Exception {

        CellBoard currCell = getDispositionFree(5,5, new Figure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMoveArcher();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue(moves.size() == 4);
    }

    @Test
    public void testGetMoveArcherAttackOpponent() throws Exception {

        CellBoard currCell = getDisposition(new Figure(TypeGamer.Black), new Figure(TypeGamer.Black),
                new Figure(TypeGamer.Black), new Figure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMoveArcher();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("у дикообраза 4 хода и 1 выстрел - всего 5 ходов",moves.size() == 5);
    }

    @Test
    public void testGetMovePorcupineAttackDistance() throws Exception {

        CellBoard currCell = getDisposition( 7, 1, new Figure(TypeGamer.Black), new Figure(TypeGamer.Black),
                new Figure(TypeGamer.Black), new Figure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMovePorcupine();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("дикообраз не должен достреливать до фигуры расположенной дальше расстояния выстрела",moves.size() == 4);
    }
}