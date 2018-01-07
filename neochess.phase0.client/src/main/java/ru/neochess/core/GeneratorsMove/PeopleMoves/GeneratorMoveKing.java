package ru.neochess.core.GeneratorsMove.PeopleMoves;

import ru.neochess.core.CellBoard;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.*;
import ru.neochess.core.GeneratorsMove.SimpleMovePatterns.GeneratorMoveOneStepDiag;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeFigure;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveKing implements IGeneratorMove {

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

        if (currentCell.getCell().getY() == 9)
        {
            GeneratorShortCastling shortCastling = new GeneratorShortCastling();
            shortCastling.setWife(TypeFigure.Rook);
            shortCastling.setDist(3);
            result.addAll(shortCastling.getMove(currentCell,typeGamer));

            GeneratorLongCastling longCastling = new GeneratorLongCastling();
            shortCastling.setWife(TypeFigure.Rook);
            shortCastling.setDist(5);
            result.addAll(longCastling.getMove(currentCell,typeGamer));

        }

        return result;
    }
}