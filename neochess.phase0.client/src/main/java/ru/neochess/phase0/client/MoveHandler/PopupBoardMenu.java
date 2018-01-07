package ru.neochess.phase0.client.MoveHandler;

import ru.neochess.phase0.client.ChessBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TiJi on 20.08.17.
 */
public class PopupBoardMenu {

    JPopupMenu popup = new JPopupMenu();
    int x;
    int y;
    ChessBoard board = null;
    String moveNotation = null;
    MoveHandler moveHandler;



    ActionListener menuListener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {

            System.out.println("Popup menu item ["
                    + event.getActionCommand() + "] was pressed.");

            switch (event.getActionCommand()) {
                case "Убить" :

                    if (moveHandler.moveKill(x, y)) {

                        moveNotation = board.moveNotation;
                        if (board.getUnlimitedMoves() == false) {
                            String encoding = board.encodeBoard();
                            System.out.println(encoding);
                            board.clientState.getCurrent().sendMove(encoding, moveNotation);
                        }
                        board.repaint();
                    }
                case "Короткая рокировка" :
                    if (moveHandler.moveShortCastling(x, y)) {

                        moveNotation = board.moveNotation;
                        if (board.getUnlimitedMoves() == false) {
                            String encoding = board.encodeBoard();
                            System.out.println(encoding);
                            board.clientState.getCurrent().sendMove(encoding, moveNotation);
                        }
                        board.repaint();
                    }
                case "Длинная рокировка" :
                    if (moveHandler.moveLongCastling(x, y)) {

                        moveNotation = board.moveNotation;
                        if (board.getUnlimitedMoves() == false) {
                            String encoding = board.encodeBoard();
                            System.out.println(encoding);
                            board.clientState.getCurrent().sendMove(encoding, moveNotation);
                        }
                        board.repaint();
                    }

            }
        }
    };


    public PopupBoardMenu(ChessBoard b, MoveHandler m)
    {
        board = b;
        moveHandler = m;
    }

    public void ShowMenu(int x, int y)
    {
        if (moveHandler.menuKill(x, y)) {

            JMenuItem itemKill = new JMenuItem("Убить");
            itemKill.addActionListener(menuListener);
            popup.add(itemKill);

            this.x = x;
            this.y = y;
           // popup.show(board, x, y);
        }
        if (moveHandler.menuShortCastling(x, y)) {

            JMenuItem itemShortCastling = new JMenuItem("Короткая рокировка");
            itemShortCastling.addActionListener(menuListener);
            popup.add(itemShortCastling);

            this.x = x;
            this.y = y;
           // popup.show(board, x, y);
        }
        if (moveHandler.menuLongCastling(x, y)) {

            JMenuItem itemLongCastling = new JMenuItem("Длинная рокировка");
            itemLongCastling.addActionListener(menuListener);
            popup.add(itemLongCastling);

            this.x = x;
            this.y = y;
           // popup.show(board, x, y);
        }

        if (popup.getComponentCount() > 0)
            popup.show(board, x, y);

    }

}
