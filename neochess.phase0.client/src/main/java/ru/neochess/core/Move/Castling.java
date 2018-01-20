package ru.neochess.core.Move;

import ru.neochess.core.CellBoard;
import ru.neochess.core.CoreFigure;

/**
 * Created by TiJi on 03.12.2017.
 */
public class Castling extends Move {
    protected final CellBoard wifeFrom, wifeTo;
    protected final CellBoard from, to;
    private CoreFigure husband;
    private CoreFigure wife;
   // private final CoreFigure coreFigureTo;
   // private final CoreFigure coreFigureFrom;


    public Castling(CellBoard from, CellBoard to,CellBoard wifeFrom, CellBoard wifeTo, CoreFigure Husband, CoreFigure Wife) {
        this.from = from;
        this.to = to;
        this.husband = Husband;
        this.wife = Wife;
        this.wifeFrom = wifeFrom;
        this.wifeTo = wifeTo;

    }

    public Castling(CellBoard from, CellBoard to,CellBoard wifeFrom, CellBoard wifeTo) {
        this.from = from;
        this.to = to;

        this.wifeFrom = wifeFrom;
        this.wifeTo = wifeTo;

    }


    public void make() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public CellBoard getFrom() {
        return from;
    }

    @Override
    public CellBoard getTo() {
        return to;
    }

    @Override
    public CoreFigure getCoreFigureTo() {
        return null;
    }

    @Override
    public String toString() {
        return "0-0";
    }

    public CellBoard getWifeFrom() {
        return wifeFrom;
    }

    public CellBoard getWifeTo() {
        return wifeTo;
    }


}
