package ru.neochess.core.Move;

import ru.neochess.core.CellBoard;
import ru.neochess.core.CoreFigure;
import ru.neochess.core.TypeFigure;

/**
 * Ход
 * Created by diviz on 10.12.2016.
 */
public class Move implements IMove {

    protected final CellBoard from, to;
    private final CoreFigure oldCoreFigureFrom;
    private final CoreFigure oldCoreFigureTo;
    private final CoreFigure coreFigureTo;
    private final CoreFigure coreFigureFrom;

    public Move(CellBoard from, CellBoard to, CoreFigure coreFigureTo) {
        this(from, to, null, coreFigureTo);
    }

    public Move(CellBoard from, CellBoard to, CoreFigure coreFigureFrom, CoreFigure coreFigureTo) {
        this.from = from;
        this.to = to;
        this.coreFigureTo = coreFigureTo;
        this.coreFigureFrom = coreFigureFrom;
        oldCoreFigureTo = to.getCoreFigure();
        oldCoreFigureFrom = from.getCoreFigure();

    }

    public Move() {
        this.from = null;
        this.to = null;
        this.coreFigureTo = null;
        this.coreFigureFrom = null;
        oldCoreFigureTo = null;
        oldCoreFigureFrom = null;

    }

    public Boolean equals (int from_col, int from_row, int to_col, int to_row)
    {
        if (from  != null && to != null) {
            if ((from.getCell().getX() == from_row) && (from.getCell().getY() == from_col) && (to.getCell().getX() == to_row) && (to.getCell().getY() == to_col))

                return true;
        }

        return false;
    }

    @Override
    public void make() {
        from.setCoreFigure(coreFigureFrom);
        to.setCoreFigure(coreFigureTo);
    }

    @Override
    public void cancel() {
        to.setCoreFigure(oldCoreFigureTo);
        from.setCoreFigure(oldCoreFigureFrom);
    }


    @Override
    public CellBoard getFrom() {
        return from;
    }

    @Override
    public CellBoard getTo() {
        return to;
    }

    public CoreFigure getCoreFigureTo() {
        return coreFigureTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;
        if (getFrom() != null ? !getFrom().equals(move.getFrom()) : move.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(move.getTo()) : move.getTo() != null) return false;
        if (oldCoreFigureFrom != null ? !oldCoreFigureFrom.equals(move.oldCoreFigureFrom) : move.oldCoreFigureFrom != null)
            return false;
        return getCoreFigureTo() != null ? getCoreFigureTo().equals(move.getCoreFigureTo()) : move.getCoreFigureTo() == null;
    }

    @Override
    public int hashCode() {
        int result = getFrom() != null ? getFrom().hashCode() : 0;
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (oldCoreFigureFrom != null ? oldCoreFigureFrom.hashCode() : 0);
        result = 31 * result + (getCoreFigureTo() != null ? getCoreFigureTo().hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return from + "->" + to + ":" + coreFigureTo;
    }
}
