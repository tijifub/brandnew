package ru.neochess.core.GeneratorsMove.SimpleMovePatterns;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.Move.Move;
import ru.neochess.core.Move.Shot;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
by TiJi on 17.08.17.
 */
public class GeneratorShotVertical implements IGeneratorMove {

    List<Move> result = new ArrayList<>();
    Integer shotDist = 3;

    public void setShotDist(Integer dist)
    {
        shotDist = dist;
    }
    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {

        CellBoard nextS;
        Move move;

        Integer shotSteps;

         Iterator<CellBoard> upS = currentCell.getIterator(AdjacentCell.Up);
         Iterator<CellBoard> downS = currentCell.getIterator(AdjacentCell.Down);


        //1.
        shotSteps = 1;
        do { if (upS.hasNext()) {
            nextS = upS.next();
            move = createMove(currentCell, nextS);
            // result.add(moveNotation);
        } else move = null;

        } while ((move != null)&&(shotSteps++ <= shotDist));


        //3.
        shotSteps = 1;
        do {
            if (downS.hasNext()) {
                nextS = downS.next();
                move = createMove(currentCell, nextS);
                // result.add(moveNotation);
            } else move = null;

        } while ((move != null)&&(shotSteps++ <= shotDist));

        return result;
    }

    protected Move createMove(CellBoard currentCell, CellBoard next) {

        if (next.getCoreFigure() == null) {
            return (new Move(currentCell, next, currentCell.getCoreFigure()));
        }
        else if (next.getCoreFigure().getTypeGamer() == currentCell.getCoreFigure().getTypeGamer())
        {
            return null;
        }
        else {
            attackMove(currentCell, next);
            return null;
        }
    }

    protected void attackMove(CellBoard currentCell, CellBoard next) {
        result.add(new Shot(currentCell, next, currentCell.getCoreFigure(), next.getCoreFigure()));

    }

}


