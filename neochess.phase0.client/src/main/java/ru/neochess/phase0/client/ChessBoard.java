package ru.neochess.phase0.client;

import ru.neochess.core.Move.Move;
import ru.neochess.core.Move.Shot;
import ru.neochess.phase0.client.MoveHandler.MoveHandler;
import ru.neochess.phase0.client.State.ClientStateWrapper;
import ru.neochess.phase0.client.MoveHandler.PopupBoardMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.*;

/**
 * Created by for on 29.10.16.
 */
public class ChessBoard extends JPanel implements ImageObserver, MouseListener, MouseMotionListener {

    BufferedImage image_buffer;
    int  linecounter = -1;
    int  linenum = 1;
    //chessboard params

   /* public int gap = 50;
    public int cellsize = 50;
   /* public int cellnum = 10;
    public int txtleft = 25;
    public int txtright = 15;
    public int txtop = 35;
    public int txtbottom = 20;
    public int boardsize = 600;*/

    //chessboard params end

    private int x, y;

    public ChessClient chessclient;

   // private int grabbed_piece, from_row, from_col, to_row, to_col;

    private Figure grabbed_figure = null;

   // private Figure chess_matrix[][] = new Figure[10][10];

    private Board board = new Board();

    public String moveNotation = new String();

    public FiguresLibrary fl = FiguresLibrary.init();

    private java.util.List<Move> moveList = new ArrayList<>();

    public ClientStateWrapper clientState;

    private MoveHandler moveHandler;
    private boolean unlimitedMoves;
    private boolean noRules;

   /* JPopupMenu popup = new JPopupMenu();
    ActionListener menuListener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            System.out.println("Popup menu item ["
                    + event.getActionCommand() + "] was pressed.");
        }
    };*/

   PopupBoardMenu popupBM;

    /*конструктор доски
    0 рисует доску:
    1 добавляет лисенер мышки,
    2 расставляет фигуры в начальное положение
    3 создает объект обертку для состояний и передается в нее */
    public ChessBoard(ChessClient cc) {

        moveHandler = new MoveHandler(this, board);

        popupBM = new PopupBoardMenu(this, moveHandler);
        clientState = new ClientStateWrapper(this);

        chessclient = cc;
        this.setSize(board.boardsize , board.boardsize);

        image_buffer = new BufferedImage(board.boardsize , board.boardsize , BufferedImage.TYPE_INT_RGB);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
       // setInitialBoard();

        clientState.getCurrent().sendState();

    }

    public boolean getUnlimitedMoves()
    {return unlimitedMoves;}

    public void setUnlimitedMoves ()
    {
        if (unlimitedMoves == false) unlimitedMoves = true;
        else {
            unlimitedMoves = false;
            String encoding = encodeBoard();
            System.out.println(encoding);

            clientState.getCurrent().sendMove(encoding, moveNotation);
        }
    }
    public boolean getNoRules()
    {return noRules;}

    public void setNoRules ()
    {
        if (noRules == false) noRules = true;
        else noRules = false;
    }


    public void setGrabbed_figure(Figure f)
    {
        grabbed_figure = f;
    }
    public Figure getGrabbed_figure()
    {
        return grabbed_figure;
    }
    public void setMoveList(java.util.List<Move> moveList)
    {
        this.moveList = moveList;
    }
    public java.util.List<Move>  getMoveList()
    {
        return moveList;
    }
    public Board getBoard()
    {return board;}

    public void exitBoard() {
        clientState.getCurrent().finishGame();
        clientState.getCurrent().sendState();
        System.exit(0);
    }

    public void resetBoard() {
        setInitialBoard();
        clientState.getCurrent().finishGame();
        clientState.getCurrent().sendState();
    }


    public void setInitialBoard() {

        decodeBoard( UtiliteChess.getInstance().getInitialBoard());
        repaint();
    }


    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }


    public void paint(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        drawOffscreen();
        gfx.drawImage(image_buffer, 0, 0, this);
        //setInitialBoard();
    }

    private void drawOffscreen()
    {
        Graphics2D gfx = image_buffer.createGraphics();

        board.paintBoard(gfx, this);
        board.paintFigures(gfx, this);

        if (grabbed_figure != null)
        {
            gfx.drawImage(grabbed_figure.getImage(),
                   - board.cellsize * grabbed_figure.getImageXshift() + x - 22,
                   - board.cellsize * grabbed_figure.getImageYshift() +
                            y - 22, this);

            if (moveList != null)
            {
                board.renderCellLiting(gfx, moveList);
            }
        }
    }


    public void mouseClicked(MouseEvent e) {
        if((e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON2 ) )
                //&& myTurn)
        {

          popupBM.ShowMunu(e.getX(), e.getY());

          if (moveHandler.figureTransform(e.getX(),e.getY()) == true) {
              repaint();
              //отправляем ход

              if (unlimitedMoves == false) {
                  String encoding = encodeBoard();
                  System.out.println(encoding);

                  clientState.getCurrent().sendMove(encoding, moveNotation);
              }
          }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1){
            return;
        }
            if (moveHandler.moveBegin(e.getX(), e.getY()) == true) {
                x = e.getX();
                y = e.getY();

                repaint();

            }

    }


    public void mouseReleased(MouseEvent e) {

        if (e.getButton() != MouseEvent.BUTTON1) {
            return;
        }

        Boolean moveDone = moveHandler.moveEnd(e.getX(), e.getY());
        repaint();

        if (moveDone) {
            if (unlimitedMoves == false) {
                String encoding = encodeBoard();
                System.out.println(encoding);

                clientState.getCurrent().sendMove(encoding, moveNotation);
            }
        }
    }


    public void mouseDragged(MouseEvent e) {

     //   if (grabbed_figure.getRace().equals(clientState.sessionData.race) == false) grabbed_figure = null;

        if (grabbed_figure == null) return;

        x = e.getX();
        y = e.getY();
        repaint();
    }


    public void mouseMoved(MouseEvent e) {
    }



    public String encodeBoard() {
        StringBuilder encoding = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                Figure f = board.getCellByIndex(i, j).getFigure();
                if (f != null) {
                    encoding.append(f.encodeFigure());
                } else {
                    encoding.append("ZZZ");
                }

            }
        }

        return encoding.toString();
    }


    public void decodeBoard(String encoding) {
        int row, col;
        String pieceRace;
        String pieceCode;
        String pieceState;
        boolean slon = false;

        Map<String,Integer> row_col = new HashMap();

        board.Clear();

        for (int i = 0; i < encoding.length(); i += 3) {

            row = i / 30;
            col = (i / 3) % 10;
            pieceRace = encoding.substring(i, i + 1);
            pieceCode = encoding.substring(i + 1, i + 2);
            pieceState = encoding.substring(i + 2, i + 3);

            if (!pieceCode.equalsIgnoreCase("Z")) {

                row_col.put("row", row);
                row_col.put("col", col);
                Figure currentFigure =  board.putFigure(pieceRace,pieceState,pieceCode, fl, board, row_col);

            }

        }

    }

    public void addTextArea1(String line)
    {

        linecounter++;
        if (linecounter <= 0)
        chessclient.addTextToArea1("\n \n");

        else if ((linecounter % 2) == 0)
            chessclient.addTextToArea1(" \n ");

        else if ((linecounter % 2) != 0) {
            chessclient.addTextToArea1("\n \n" + (linenum) + ". ");
            linenum++;
        }

        chessclient.addTextToArea1(line);



    }

}

