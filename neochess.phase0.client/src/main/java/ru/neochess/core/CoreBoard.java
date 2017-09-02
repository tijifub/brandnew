package ru.neochess.core;

import ru.neochess.phase0.client.Figure;
import ru.neochess.core.CellBoard;

import java.util.ArrayList;

/**
 * Created by diviz on 10.12.2016.
 */
public class CoreBoard {

    //private ArrayList<BoardCell> cells = new ArrayList<>(); // все клетки доски
    private CellBoard cell_matrix[][] = new CellBoard[10][10];

    private ArrayList<CoreFigure> coreFigures = new ArrayList<>();

    public CellBoard getCellByIndex(int row, int col) {
        return this.cell_matrix[row][col];
    }

    public CoreBoard() {
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                this.cell_matrix[i][j] = new CellBoard(i, j, this);
            }
        }
    }

    public CoreBoard (String encoding)
    {
        this();

        for (int i = 0; i < encoding.length(); i += 3) {

            int row, col;
            String pieceRace;
            String pieceCode;
            String pieceState;

            row = i / 30;
            col = (i / 3) % 10;
            pieceRace = encoding.substring(i, i + 1);
            pieceCode = encoding.substring(i + 1, i + 2);
            pieceState = encoding.substring(i + 2, i + 3);

            if (!pieceCode.equalsIgnoreCase("Z")) {

                this.cell_matrix[col][row].setCoreFigure(new CoreFigure(pieceCode, pieceRace));

            }

        }
    }

}
