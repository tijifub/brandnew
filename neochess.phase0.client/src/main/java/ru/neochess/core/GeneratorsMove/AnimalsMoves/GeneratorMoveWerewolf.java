package ru.neochess.core.GeneratorsMove.AnimalsMoves;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveLeftDiagonal;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveOneStep;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveOneStepDiag;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveRightDiagonal;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveWerewolf implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        //change diag

        GeneratorMoveOneStep MoveOneStep = new GeneratorMoveOneStep();
        result.addAll(MoveOneStep.getMove(currentCell,typeGamer));

        //giagonals

        GeneratorMoveRightDiagonal RightDiagonal = new GeneratorMoveRightDiagonal();
        GeneratorMoveLeftDiagonal LeftDiagonal = new GeneratorMoveLeftDiagonal();

        result.addAll(RightDiagonal.getMove(currentCell,typeGamer));
        result.addAll(LeftDiagonal.getMove(currentCell,typeGamer));

        return result;
    }

}