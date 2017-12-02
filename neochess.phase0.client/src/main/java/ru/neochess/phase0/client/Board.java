package ru.neochess.phase0.client;

import com.sun.corba.se.impl.orbutil.graph.Graph;


import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import com.google.protobuf.*;
import ru.neochess.core.Move.Move;
import ru.neochess.core.Move.Shot;


/**
 * Created by for on 05.11.16.
 */
public class Board {

    public int gap = 50;
    public int cellsize = 50;
    public int cellnum = 10;
    public int txtleft = 25;
    public int txtright = 15;
    public int txtop = 35;
    public int txtbottom = 20;
    public int boardsize = 600;
    //private ArrayList<BoardCell> cells = new ArrayList<>(); // все клетки доски
    private BoardCell cell_matrix[][] = new BoardCell[10][10];

    private ArrayList<Figure> figures= new ArrayList<>();

    public void Clear()
    {
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){

                    this.cell_matrix[i][j].clear();

            }
        }
        figures.clear();
    }

  /*  public Figure findFigureByCode (String code)
    {

       Figure f =  figures.stream().filter(figure1 -> code.equals(figure1.getCode())).findAny().orElse(null);

        return f;
    }*/

    public Board() {
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                this.cell_matrix[i][j] = new BoardCell(i, j);
            }
        }
    }

    public BoardCell getCellByIndex(int row, int col) {
        return this.cell_matrix[row][col];
    }

    public Figure putFigure (String pieceRace,String pieceState, String pieceCode, FiguresLibrary fl, Board board, Map<String,Integer> row_col)
    {
        Figure currentFigure = null;

        if (pieceCode.equals("H")) {

            //нужно как-то не добавлять слона в набор фигур 4 раза
            // проверяем есть ли уже слон ( а можно проверять есть ли незаполненый клетками слон

            currentFigure = figures.stream().filter(figure1 -> pieceCode.equals(figure1.getCode())).findAny().orElse(null);

        }
        if (currentFigure == null)
        {
            currentFigure = fl.getFigureByCode(pieceCode);
            figures.add(currentFigure);
            currentFigure.setState(pieceState);
            currentFigure.setRace(pieceRace);
        }

        currentFigure.placeOnBoard(board, row_col);
        return currentFigure;

    }
    public void saveFigure(Figure f){

            if (!figures.contains(f)) {
            figures.add(f);
        }
     }

    public void removeFigure(Figure f){
        if(figures.contains(f)){
            figures.remove(f);
        }

        // удалить также из всех клеток
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if (this.cell_matrix[i][j].getFigure() == f) {
                    this.cell_matrix[i][j].clear();
                }
            }
        }
    }

    public void paintBoard(Graphics2D gfx, ChessBoard observer) {


        boolean b = false;
        int x;
        int y;
        int revers = 0;

        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, boardsize, boardsize);
        gfx.setColor(Color.white);

        x = cellsize + cellsize / 2;
        y = 0;

        if (observer.clientState.sessionData != null) {

            //РИСУЕМ ДОСКУ ДЛЯ ЛЮДЕЙ (БЕЛЫХ)
            if (observer.clientState.sessionData.race.equals("W")) {


                for (char a = 'A'; a <= 'J'; a++) {

                    gfx.drawString(String.valueOf(a).toString(), x, y + txtop);

                    gfx.drawString(String.valueOf(a).toString(), x, y + gap + cellsize * cellnum + txtbottom);

                    x += cellsize;

                }

                x = 0;
                y = cellsize + cellsize/2;

                for (int n = cellnum; n >= 1; n--) {

                    gfx.drawString(String.valueOf(n).toString(), x+ gap + cellsize * cellnum + txtright, y);

                    gfx.drawString(String.valueOf(n).toString(), x + txtleft, y);

                    y += cellsize;

                }

        // РИСУЕМ ДОСКУ ДЛЯ ЖИВОТНЫХ (ЧЕРНЫХ)
            } else if (observer.clientState.sessionData.race.equals("B")) {

                for (char a = 'J'; a >= 'A'; a--) {

                    gfx.drawString(String.valueOf(a).toString(), x, y + txtop);

                    gfx.drawString(String.valueOf(a).toString(), x, y + gap + cellsize * cellnum + txtbottom);

                    x += cellsize;

                }

                x = 0;
                y = cellsize + cellsize/2;
                for (int n = 1; n <= cellnum; n++) {

                    gfx.drawString(String.valueOf(n).toString(), x+ gap + cellsize * cellnum + txtright, y);

                    gfx.drawString(String.valueOf(n).toString(), x + txtleft, y);

                    y += cellsize;


                }

                revers = 1;

            }

            y = gap;
            for (int i = 0; i < cellnum; i++) {
                x = gap;
                b = i % 2 == revers;
                for (int j = 0; j < cellnum; j++) {
                    if (b) {
                        gfx.setColor(Color.white);
                    }
                    else {
                        gfx.setColor(Color.gray);
                    }
                    b = !b;
                    gfx.fillRect(x, y, cellsize, cellsize);

                    x += cellsize;

                }
                y += cellsize;
            }
        }
    }
    public void paintFigures(Graphics2D gfx, ChessBoard observer) {
        int gap = 50;
        int cell = 50;
        int a = 0;

        for (Figure f : figures) {
            try {
                gfx.drawImage(f.getImage(), f.getxCoord() + gap + 2, f.getyCoord() + gap + 2, observer);
            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("Не нашел картинку для фигуры " + f.getDesc());
            }
        }
    }


    public void renderCellLiting(Graphics2D gfx, java.util.List<Move> moveList)
    {
        Move move;
        Shot shot;
        int x;
        int y;


        // gfx.setColor(new Color(255, 255, 0, 50));
        for (int i = 0; i < moveList.size(); i++) {
            move = moveList.get(i);

            if (move instanceof Shot)
            {
                gfx.setColor(new Color(227, 9, 37, 64));
                shot = (Shot) move;
                x = shot.getAimCell().getCell().getX();
                y = shot.getAimCell().getCell().getY();

                gfx.fillRect(x * cellsize + gap, y * cellsize + gap, cellsize, cellsize);


            }
            else if (move instanceof Move) {

                if (move.getTo() != null) {
                    x = move.getTo().getCell().getX();
                    y = move.getTo().getCell().getY();

                    gfx.setColor(new Color(255, 255, 0, 50));

                    gfx.fillRect(x * cellsize + gap, y * cellsize + gap, cellsize, cellsize);
                }
            }

        }
    }
}

