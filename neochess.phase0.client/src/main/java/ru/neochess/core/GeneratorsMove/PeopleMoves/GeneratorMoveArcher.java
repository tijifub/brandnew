package ru.neochess.core.GeneratorsMove.PeopleMoves;

import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveOneStepHor;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorShotHorizontal;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorShotVertical;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveArcher implements IGeneratorMove {

    List<Move> result = new ArrayList<>();
   // List<Shot> result1 = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {

        GeneratorMoveOneStepHor MoveOneStep = new GeneratorMoveOneStepHor();
        GeneratorShotHorizontal ShotHorizontal = new GeneratorShotHorizontal();
        GeneratorShotVertical ShotVertical = new GeneratorShotVertical();
        result.addAll(MoveOneStep.getMove(currentCell, typeGamer));
        result.addAll(ShotHorizontal.getMove(currentCell,typeGamer));
        result.addAll(ShotVertical.getMove(currentCell,typeGamer));

        return result;

    }
}
