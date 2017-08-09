package ru.neochess.core.GeneratorsMove.PeopleMoves;

import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveHorisontal;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveVertical;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveRook implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        GeneratorMoveHorisontal Horisontal = new GeneratorMoveHorisontal();
        GeneratorMoveVertical Vertical = new GeneratorMoveVertical();

        result.addAll(Horisontal.getMove(currentCell,typeGamer));
        result.addAll(Vertical.getMove(currentCell,typeGamer));

        return result;

    }
}