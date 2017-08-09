package ru.neochess.core.GeneratorsMove.SimpleMovePatterns;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 05.08.17.
 */
public class GeneratorMoveOneStep {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        Iterator<CellBoard> up = currentCell.getIterator(AdjacentCell.Up);
        Iterator<CellBoard> down = currentCell.getIterator(AdjacentCell.Down);
        Iterator<CellBoard> right = currentCell.getIterator(AdjacentCell.Right);
        Iterator<CellBoard> left = currentCell.getIterator(AdjacentCell.Left);

        if (down.hasNext()) {
            next = down.next();
            //   if (next.getFigure() == null) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            //  }
        }

        if (up.hasNext()) {
            next = up.next();
            //  if (next.getFigure() == null) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            //   }
        }

        if (left.hasNext()) {
            next = left.next();
            //   if (next.getFigure() == null) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            //  }
        }

        if (right.hasNext()) {
            next = right.next();
            //  if (next.getFigure() == null) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            // }
        }

        return result;
    }

}
