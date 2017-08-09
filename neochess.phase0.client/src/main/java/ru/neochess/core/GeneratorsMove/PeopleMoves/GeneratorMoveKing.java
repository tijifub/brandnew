package ru.neochess.core.GeneratorsMove.PeopleMoves;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveOneStep;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveOneStepDiag;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveKing implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        GeneratorMoveOneStep MoveOneStep = new GeneratorMoveOneStep();
        GeneratorMoveOneStepDiag MoveOneStepDiag = new GeneratorMoveOneStepDiag();
        result.addAll(MoveOneStep.getMove(currentCell,typeGamer));
        result.addAll(MoveOneStepDiag.getMove(currentCell,typeGamer));

        return result;
    }
}