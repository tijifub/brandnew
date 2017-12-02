package ru.neochess.phase0.client.MoveHandler;

import ru.neochess.core.Move.Move;
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


            if (moveHandler.moveKill(x, y)) {

                moveNotation = board.moveNotation;
                if (board.getUnlimitedMoves() == false) {
                    String encoding = board.encodeBoard();
                    System.out.println(encoding);
                    board.clientState.getCurrent().sendMove( encoding, moveNotation);
                }
                board.repaint();
            }

        }
    };


    public PopupBoardMenu(ChessBoard b, MoveHandler m)
    {

        JMenuItem item = new JMenuItem("Kill");
        item.addActionListener(menuListener);
        popup.add(item);
        board = b;
        moveHandler = m;
    }

    public void ShowMunu (int x, int y)
    {
        if (moveHandler.menuKill(x, y)) {
            this.x = x;
            this.y = y;
            popup.show(board, x, y);
        }
    }

}
