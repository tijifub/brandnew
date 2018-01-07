package ru.neochess.core.GeneratorsMove.SimpleMovePatterns;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.Move.Castling;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeFigure;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 06.12.2017.
 */
public class GeneratorLongCastling implements IGeneratorMove {

    List<Move> result = new ArrayList<>();
    TypeFigure wife;
    Integer dist = 3;
    CellBoard step;

    public void setDist(Integer d)
    {
        this.dist = d;
    }

    public void setWife(TypeFigure  w)
    {
        this.wife = w;
    }

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {

        CellBoard nextS;

      //  Move move;

        Integer steps;

        boolean freeSpace = true;

        Iterator<CellBoard> iterator;

        if (typeGamer == TypeGamer.W) {

            iterator = currentCell.getIterator(AdjacentCell.Left);
        }
        else
        {
            return null;
        }



        if (iterator.hasNext()) {
            nextS = iterator.next();
            steps = 2;
            do {
                 freeSpace = (nextS.getCoreFigure() == null);
                if (iterator.hasNext())
                    nextS = iterator.next();
                else break;

            } while (steps++ < dist);


            if (freeSpace) {
                castleMove(currentCell, nextS);
            }
        }

        return result;
    }

  /*  protected Move createMove(CellBoard currentCell, CellBoard next) {

        if (next.getCoreFigure() == null) {
            //return (new Move(currentCell, next, currentCell.getCoreFigure()));

            if (next.getCoreFigure().getTypeFigure().equals(wife)) {
                castleMove(currentCell, next);
                return null;
            }
        }
        else return null;
    }*/

    protected void castleMove(CellBoard currentCell, CellBoard next) {
        result.add(new Castling (currentCell, next.getRight(), next, currentCell));

    }
}
