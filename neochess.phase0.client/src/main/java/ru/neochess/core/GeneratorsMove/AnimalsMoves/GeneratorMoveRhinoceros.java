package ru.neochess.core.GeneratorsMove.AnimalsMoves;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.CoreFigure;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.Move.Move;
import ru.neochess.core.Move.RinoAttack;
import ru.neochess.core.TypeGamer;
import ru.neochess.phase0.client.Figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveRhinoceros implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;


        Move move;

        Iterator<CellBoard> up = currentCell.getIterator(AdjacentCell.Up);
        Iterator<CellBoard> down = currentCell.getIterator(AdjacentCell.Down);
        Iterator<CellBoard> right = currentCell.getIterator(AdjacentCell.Right);
        Iterator<CellBoard> left = currentCell.getIterator(AdjacentCell.Left);

        // горизонтали
        //1.
        do { if (up.hasNext()) {
            next = up.next();
            move = createMove(currentCell, next, up);
            if (move != null) result.add(move);
        } else move = null;

        } while (move != null);

        //2.
        do {
            if (right.hasNext()) {
                next = right.next();
                move = createMove(currentCell, next, right);
                if (move != null) result.add(move);
            } else move = null;

        } while (move != null);

        //3.
        do {
            if (down.hasNext()) {
                next = down.next();
                move = createMove(currentCell, next, down);
                if (move != null) result.add(move);
            } else move = null;

        } while (move != null);

        //4.
        do {
            if (left.hasNext()) {
                next = left.next();
                move = createMove(currentCell, next, left);
                if (move != null) result.add(move);
            } else move = null;

        } while (move != null);

        return result;
    }

    protected Move createMove(CellBoard currentCell, CellBoard next, Iterator<CellBoard> it) {

        CellBoard victimCell;
        CoreFigure victim;

        if (next.getCoreFigure() == null) {
            return (new Move(currentCell, next, currentCell.getCoreFigure()));
        } else if (next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())
        {
            victim = next.getCoreFigure();
            victimCell = next;
            attackMove(currentCell, next, it, victim, victimCell);
        }

        return null;
    }
    protected void attackMove(CellBoard currentCell, CellBoard next, Iterator<CellBoard> it, CoreFigure victim, CellBoard victimCell) {

        if (it.hasNext())
        next = it.next();

        result.add(new RinoAttack(currentCell, next, currentCell.getCoreFigure(), victimCell, victim));

    }
}