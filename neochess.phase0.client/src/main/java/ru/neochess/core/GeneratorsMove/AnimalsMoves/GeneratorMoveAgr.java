package ru.neochess.core.GeneratorsMove.AnimalsMoves;

import ru.neochess.core.*;
import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.Move.Move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

    /**
     * Created by TiJi on 26.03.17.
     * генерация хода агра
     */
    public class GeneratorMoveAgr  implements IGeneratorMove {

        @Override
        public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
            List<Move> result = new ArrayList<>();

            Iterator<CellBoard> down = currentCell.getIterator(AdjacentCell.Down);
            Iterator<CellBoard> leftdown = currentCell.getIterator(AdjacentCell.LeftDown);
            Iterator<CellBoard> rightdown = currentCell.getIterator(AdjacentCell.RightDown);
            Iterator<CellBoard> up = currentCell.getIterator(AdjacentCell.Up);
            Iterator<CellBoard> leftup = currentCell.getIterator(AdjacentCell.LeftUp);
            Iterator<CellBoard> rightup = currentCell.getIterator(AdjacentCell.RightUp);
            boolean firstStep = false;
            boolean firstStepFromLeader = false;


            //доп условия
            if (currentCell.getCell().getY() == 1) // ход с 9й линии
            {
                firstStep = true;

                CellBoard prev;
                CoreFigure prevF;
                if (up.hasNext()) {
                    prev = up.next();
                    prevF = prev.getCoreFigure();
                    if (prevF != null) // ход от вожака
                        if (prevF.getTypeFigure().equals(TypeFigure.Leader))
                            firstStepFromLeader = true;
                }

                if (leftup.hasNext()) {
                    prev = leftup.next();
                    prevF = prev.getCoreFigure();
                    if (prevF != null) // ход от вожака
                        if (prevF.getTypeFigure().equals(TypeFigure.Leader))
                            firstStepFromLeader = true;
                }
                if (rightup.hasNext()) {
                    prev = rightup.next();
                    prevF = prev.getCoreFigure();
                    if (prevF != null) // ход от вожака
                        if (prevF.getTypeFigure().equals(TypeFigure.Leader))
                            firstStepFromLeader = true;
                }
            }



            //ход
            if (down.hasNext()) {
                CellBoard next = down.next();
                if (next.getCoreFigure() == null) {
                    result.add(new Move(currentCell, next,
                            currentCell.getCoreFigure()));
                }

                if (firstStep) {
                    down = next.getIterator(AdjacentCell.Down);


                    if (down.hasNext()) {
                        CellBoard next2 = down.next();
                        if (next2.getCoreFigure() == null) {
                            result.add(new Move(currentCell, next2,
                                    currentCell.getCoreFigure()));
                        }
                    }
                    if( firstStepFromLeader)
                    {
                        CellBoard next3 = down.next();
                        if (next3.getCoreFigure() == null) {
                            result.add(new Move(currentCell, next3,
                                    currentCell.getCoreFigure()));
                        }
                    }

                }

            }

            //аттака
            if (down.hasNext()) {
                CellBoard next = down.next();
                if (next.getCoreFigure() != null && (next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
                    result.add(new Move(currentCell, next,
                            currentCell.getCoreFigure()));
                }
            }

            if (leftdown.hasNext() ) {
                CellBoard next = leftdown.next();
                if (next.getCoreFigure() != null && (next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
                    result.add(new Move(currentCell, next,
                            currentCell.getCoreFigure()));
                }
            }
            if (rightdown.hasNext()) {
                CellBoard next = rightdown.next();
                if ((next.getCoreFigure() != null) && (next.getCoreFigure().getTypeGamer() != currentCell.getCoreFigure().getTypeGamer())) {
                    result.add(new Move(currentCell, next,
                            currentCell.getCoreFigure()));
                }
            }
            return result;
        }

    }



