package ru.neochess.core.GeneratorsMove.AnimalsMoves;

import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveOneStepHor;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveOneStepDiag;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GenetatorMoveOneStepVert;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveLeader implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        GeneratorMoveOneStepHor MoveOneStepHor = new GeneratorMoveOneStepHor();
        GenetatorMoveOneStepVert MoveOneStepVert = new GenetatorMoveOneStepVert();
        GeneratorMoveOneStepDiag MoveOneStepDiag = new GeneratorMoveOneStepDiag();
        result.addAll(MoveOneStepHor.getMove(currentCell,typeGamer));
        result.addAll(MoveOneStepVert.getMove(currentCell, typeGamer));
        result.addAll(MoveOneStepDiag.getMove(currentCell,typeGamer));

        return result;
    }
}