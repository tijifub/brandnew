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
 * Created by TiJi on 05.08.17.
 */
public class GeneratorMoveOneStepDiag implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        Iterator<CellBoard> leftdown = currentCell.getIterator(AdjacentCell.LeftDown);
        Iterator<CellBoard> rightdown = currentCell.getIterator(AdjacentCell.RightDown);
        Iterator<CellBoard> leftUp = currentCell.getIterator(AdjacentCell.LeftUp);
        Iterator<CellBoard> rightUp = currentCell.getIterator(AdjacentCell.RightUp);

        if (leftdown.hasNext()) {
            next = leftdown.next();
            if ((next.getCoreFigure() == null)||(next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
                result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
              }
        }

        if (rightdown.hasNext()) {
            next = rightdown.next();
            if ((next.getCoreFigure() == null)||(next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
                result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
              }
        }

        if (leftUp.hasNext()) {
            next = leftUp.next();
            if ((next.getCoreFigure() == null)||(next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
                result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
             }
        }

        if (rightUp.hasNext()) {
            next = rightUp.next();
            if ((next.getCoreFigure() == null)||(next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
                result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
             }
        }

        return result;
    }
}
