package ru.neochess.core.GeneratorsMove.AnimalsMoves;

import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;

import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.*;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveElephant implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        GeneratorMoveOneStepHor MoveOneStepHor = new GeneratorMoveOneStepHor();
        GenetatorMoveOneStepVert MoveOneStepVert = new GenetatorMoveOneStepVert();
        result.addAll(MoveOneStepHor.getMove(currentCell,typeGamer));
        result.addAll(MoveOneStepVert.getMove(currentCell,typeGamer));

        return result;
    }
}