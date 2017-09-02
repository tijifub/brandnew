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

    ActionListener menuListener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {

            System.out.println("Popup menu item ["
                    + event.getActionCommand() + "] was pressed.");

            MoveHandler moveHandler = new MoveHandler();
            if (moveHandler.moveKill(x, y, board)) {
                String encoding = board.encodeBoard();
                System.out.println(encoding);
                moveNotation = board.moveNotation;
                board.clientState.getCurrent().sendMove( encoding, moveNotation);
                board.repaint();
            }

        }
    };


    public PopupBoardMenu(ChessBoard b)
    {
        JMenuItem item = new JMenuItem("Kill");
        item.addActionListener(menuListener);
        popup.add(item);
        board = b;
    }

    public void ShowMunu (int x, int y)
    {
        this.x = x;
        this.y = y;
        popup.show(board, x ,y);
    }

}
