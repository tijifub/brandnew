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
 * Created by TiJi on 02.09.17.
 */
public class GenetatorMoveOneStepVert implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        Iterator<CellBoard> up = currentCell.getIterator(AdjacentCell.Up);
        Iterator<CellBoard> down = currentCell.getIterator(AdjacentCell.Down);


        if (down.hasNext()) {
            next = down.next();
            if ((next.getCoreFigure() == null)||(next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
                result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
              }
        }

        if (up.hasNext()) {
            next = up.next();
            if ((next.getCoreFigure() == null)||(next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            }
        }


        return result;
    }
}