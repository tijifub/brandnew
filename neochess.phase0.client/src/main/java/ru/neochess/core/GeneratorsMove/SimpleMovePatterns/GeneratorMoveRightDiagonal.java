package ru.neochess.core.GeneratorsMove.SimpleMovePatterns;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 24.04.17.
 */
public class GeneratorMoveRightDiagonal implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;
        Iterator<CellBoard> rightUp = currentCell.getIterator(AdjacentCell.RightUp);
        Iterator<CellBoard> leftdown = currentCell.getIterator(AdjacentCell.LeftDown);

        do {
            if (rightUp.hasNext()) {
                next = rightUp.next();
                move = createMove(currentCell, next);
               // result.add(moveNotation);
            } else move = null;

        } while (move != null);

        do {
            if (leftdown.hasNext()) {
                next = leftdown.next();
                move = createMove(currentCell, next);
                //result.add(moveNotation);
            } else move = null;

        } while (move != null);

        return result;
    }
    protected Move createMove(CellBoard currentCell, CellBoard next) {

        if (next.getCoreFigure() == null)
        {
            Move move = new Move(currentCell, next, currentCell.getCoreFigure());
            result.add(move);
            return move;
        }
        else if (next.getCoreFigure().getTypeGamer() == currentCell.getCoreFigure().getTypeGamer())
        {
            return null;
        }
        else
        {
            result.add (attackMove(currentCell, next));
            return null;
        }
    }

    protected Move attackMove(CellBoard currentCell, CellBoard next) {
        //  result.add(new Move(currentCell, next, currentCell.getCoreFigure()));
        return new Move(currentCell, next, currentCell.getCoreFigure());

    }

}
