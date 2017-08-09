package ru.neochess.phase0.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by for on 01.11.16.
 */
public class FiguresLibrary {

    private static FiguresLibrary instance = new FiguresLibrary();

    private List<LibItem> items = new ArrayList<LibItem>() {
        {
            add(new LibItem("A", "Агр", "/figures/animals/agr.png", "AnimalsMoves.GeneratorMoveAgr",  (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("B", "Дикобраз", "/figures/animals/dkbrz.png", "AnimalsMoves.GeneratorMovePorcupine", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("C", "Дракон", "/figures/animals/drakon.png", "AnimalsMoves.GeneratorMoveDragon",(board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("D", "Конь", "/figures/animals/kon.png","AnimalsMoves.GeneratorMoveHorse", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("E", "Носорог", "/figures/animals/nosrg.png","AnimalsMoves.GeneratorMoveRhinoceros", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("F", "Оборотень", "/figures/animals/obrtn.png","AnimalsMoves.GeneratorMoveWerewolf", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("G", "Вожак", "/figures/animals/vojak.png","AnimalsMoves.GeneratorMoveLeader", (board, rc, figure) -> {
                    board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                    return board;
            }));

            add(new LibItem("H", "Слон", "/figures/animals/slon.png","AnimalsMoves.GeneratorMoveElephant", (board, rc, figure) -> {
               // figure = board.findFigureByCode();

                int pos = figure.getMousePos();
                switch(pos)
                {
                    case 0:




                        board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                        board.getCellByIndex(rc.get("row"), rc.get("col") + 1).placeIn(figure);
                        board.getCellByIndex(rc.get("row") + 1, rc.get("col")).placeIn(figure);
                        board.getCellByIndex(rc.get("row") + 1, rc.get("col") + 1).placeIn(figure);
                        break;

                    case 1:

                        board.getCellByIndex(rc.get("row"), rc.get("col") - 1).placeIn(figure);
                        board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                        board.getCellByIndex(rc.get("row") + 1, rc.get("col") - 1).placeIn(figure);
                        board.getCellByIndex(rc.get("row") + 1, rc.get("col")).placeIn(figure);

                        break;
                    case 2:
                        board.getCellByIndex(rc.get("row") - 1, rc.get("col")).placeIn(figure);
                        board.getCellByIndex(rc.get("row") - 1, rc.get("col") + 1).placeIn(figure);
                        board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                        board.getCellByIndex(rc.get("row") , rc.get("col") + 1).placeIn(figure);

                        break;
                   // default:

                    case 3:

                        board.getCellByIndex(rc.get("row") - 1, rc.get("col") - 1).placeIn(figure);
                        board.getCellByIndex(rc.get("row") - 1, rc.get("col")).placeIn(figure);
                        board.getCellByIndex(rc.get("row"), rc.get("col") - 1).placeIn(figure);
                        board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);

                        break;

                    case -1:
                         board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);

                }

                // board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);

               //  board.getCellByIndex(rc.get("row") + 1, rc.get("col")).placeIn(figure);
               //  board.getCellByIndex(rc.get("row"), rc.get("col") + 1).placeIn(figure);
               //  board.getCellByIndex(rc.get("row") + 1, rc.get("col") + 1).placeIn(figure);
                return board;
            }));

// -------------------------------------

            add(new LibItem("I", "Король", "/figures/humans/korol.gif","PeopleMoves.GeneratorMoveKing", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("J", "Королева", "/figures/humans/krlva.gif","PeopleMoves.GeneratorMoveQueen", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("K", "Ладья", "/figures/humans/ladya.gif","PeopleMoves.GeneratorMoveRook", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("L", "Лучник", "/figures/humans/luchnk.png","PeopleMoves.GeneratorMoveArcher", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("M", "Монах", "/figures/humans/monah.gif","", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("N", "Офицер", "/figures/humans/ofcr.png","PeopleMoves.GeneratorMoveOfficer", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("O", "Пешка", "/figures/humans/peshka.png","PeopleMoves.GeneratorMovePawn", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("P", "Ловушка", "/figures/humans/lvshk.gif","", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

            add(new LibItem("R", "Пешка боевая", "/figures/humans/peshka_war.png","PeopleMoves.GeneratorMovePawnAttack", (board, rc, figure) -> {
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board;
            }));

        }
    };

    public static FiguresLibrary init() {
        //instance.addItem(new LibItem("A", "Слон"));

        return instance;
    }

//    private void addItem(LibItem item){
//        items.add(item);
//    }

    private LibItem getItemByCode(String code) {
        for (LibItem lib : items) {
            if (lib != null && lib.getCode().equalsIgnoreCase(code)) {
                return lib;
            }
        }
        return null;
    }


    /**
     * Получить фигуру из библиотеке по ее коду
     * @param code Код фигуры
     * @return Figure
     */
    public Figure getFigureByCode(String code) {
        return new Figure(this.getItemByCode(code));
    }

//    public Figure getEmptyFigure() {
//        return getFigureByCode("Z");
//    }

}
