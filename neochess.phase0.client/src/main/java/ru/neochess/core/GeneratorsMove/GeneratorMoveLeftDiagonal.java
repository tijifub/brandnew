package ru.neochess.core.GeneratorsMove;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 24.04.17.
 */
public class GeneratorMoveLeftDiagonal {
    List<Move> result = new ArrayList<>();
    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;
        Iterator<CellBoard> leftUp = currentCell.getIterator(AdjacentCell.LeftUp);
        Iterator<CellBoard> rightdown = currentCell.getIterator(AdjacentCell.RightDown);

        do { if (leftUp.hasNext()) {
            next = leftUp.next();
            move = createMove(currentCell, next);
            result.add(move);
        } else move = null;

        } while (move != null);
        do {
            if (rightdown.hasNext()) {
                next = rightdown.next();
                move = createMove(currentCell, next);
                result.add(move);
            } else move = null;

        } while (move != null);

        return result;
    }

    protected Move createMove(CellBoard currentCell, CellBoard next) {

        if (next.getCoreFigure() == null) {
            return (new Move(currentCell, next, currentCell.getCoreFigure()));
        } else AttackMove(currentCell, next);

        return null;
    }

    protected void AttackMove(CellBoard currentCell, CellBoard next) {
        result.add(new Move(currentCell, next, currentCell.getCoreFigure()));

    }
}
