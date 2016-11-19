package ru.neochess.phase0.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by for on 29.10.16.
 */
class ChessBoard extends JPanel implements ImageObserver, MouseListener, MouseMotionListener {

    BufferedImage image_buffer;
    ChessServerConnection serverconnection;

    private int x, y;

    private ChessClient chessclient;

    private int myColor;
    private String myRace;

    private boolean myTurn;

   // private currentRace

    private int grabbed_piece, from_row, from_col, to_row, to_col;

    private Figure grabbed_figure;
    private Figure replaced_figure;

    //    private int chess_matrix[][] = new int[10][10];
//    private String chess_matrix[][] = new String[3][3];
    private Figure chess_matrix[][] = new Figure[10][10];

    private Board board = new Board();

    FiguresLibrary fl = FiguresLibrary.init();

    private ImageIcon chessmen_images[] = new ImageIcon[16];


    public ChessBoard(ChessClient cc) {
        chessclient = cc;
        this.setSize(500, 500);

//        initChessMatrix();

        //CreateChessmenImages();
        image_buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setInitialBoard();
        serverconnection = new ChessServerConnection(this);

//        processCommand("@BLACK");
//        processCommand("@TOKEN");

        grabbed_piece = ChessMen.NOTHING;

    }


    public void resetBoard() {
        setInitialBoard();
        repaint();
        String encoding = encodeBoard();

        try {
            serverconnection.send(encoding);
            serverconnection.send("@RESET");
        } catch (Exception ex) {

        }
    }


    private void setInitialBoard() {

        //  1     2        3       4    5   6   7   8   9   10
        // King Queen King(error) King  Z   Z   Z   Z   Z   Z
        //  Z
        //

//        decodeBoard("WA1WS1WA1WA1ZZZZZZZZZZZZZZZZZZZZZ");
        //decodeBoard("WA1WS1WS1WA1ZZZZZZZZZZZZZZZZZZZZZ");

        decodeBoard( UtiliteChess.getInstance().getInitialBoard());
    }


    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }


    public void paint(Graphics g) {
        Graphics2D gfx = (Graphics2D) g;
        drawOffscreen();
        gfx.drawImage(image_buffer, 0, 0, this);
    }


    private void drawOffscreen() {
        Graphics2D gfx = image_buffer.createGraphics();

        renderChessBoard(gfx);

//        if (grabbed_piece != ChessMen.NOTHING)
        if (grabbed_figure != null) {
//            gfx.drawImage(chessmen_images[grabbed_piece].getImage(), x - 22, y - 22, this);
            gfx.drawImage(grabbed_figure.getImage(), x - 22, y - 22, this);
        }


    }


    private void renderChessBoard(Graphics2D gfx) {
        int x = 0, y = 0;
        boolean b = false;

        for (int i = 0; i < 10; i++) {
            x = 0;
            b = i % 2 == 0;
            for (int j = 0; j < 10; j++) {
                if (b) {
                    gfx.setColor(Color.white);
                }
                else {
                    gfx.setColor(Color.gray);
                }
                b = !b;
                gfx.fillRect(x, y, 50, 50);

                //paintChessMan(board.getCellByIndex(i,j).getFigure(), x, y, gfx);
                //paintChessMan(chess_matrix[i][j], x, y, gfx);

                x += 50;

            }
            y += 50;
        }

        board.paintFigures(gfx, this);

    }


    //    private void paintChessMan(int piece, int x, int y, Graphics2D gfx)
    private void paintChessMan(Figure fig, int x, int y, Graphics2D gfx) {
        if (fig != null) {

            try {
                gfx.drawImage(fig.getImage(), x + 2, y + 2, this);
            } catch (Exception ex) {
                System.out.println("Не нашел картинку для фигуры " + fig.getDesc());
            }

        }

    }


    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3)
        {
            Map<String,Integer> row_col = new HashMap();
            Figure selectFigure;
            Figure newFigure;

            from_row = e.getY() / 50;
            from_col = e.getX() / 50;

            if (from_row < 0 || from_row > 9) return;
            if (from_col < 0 || from_col > 9) return;

            selectFigure = board.getCellByIndex(from_row, from_col).getFigure();

            //обычная пешка
            if (selectFigure != null && selectFigure.getCode().compareTo("O") == 0) {

                    newFigure = fl.getFigureByCode("R"); //Боевая пешка
                    newFigure.setState(selectFigure.getState());
                    newFigure.setRace(selectFigure.getRace());

                    row_col.put("row", from_row);
                    row_col.put("col", from_col);
                    newFigure.placeOnBoard(board, row_col);
                    board.saveFigure(newFigure);

                    board.removeFigure(selectFigure);
                    repaint();

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
        from_row = e.getY() / 50;
        from_col = e.getX() / 50;

        if (from_row < 0 || from_row > 9) return;
        if (from_col < 0 || from_col > 9) return;

//        grabbed_piece = chess_matrix[from_row][from_col];
//        grabbed_figure = chess_matrix[from_row][from_col];
        grabbed_figure = board.getCellByIndex(from_row, from_col).getFigure();

        if(grabbed_figure==null) return;

//        if ((getPieceType(grabbed_piece) != myColor) || !myTurn)
//        {
//            grabbed_piece = ChessMen.NOTHING;
//            return;
//        }

//        chess_matrix[from_row][from_col] = null; //fl.getEmptyFigure(); //ChessMen.NOTHING;

        board.removeFigure(grabbed_figure);

        x = e.getX();
        y = e.getY();

        repaint();
    }


    public void mouseReleased(MouseEvent e) {

        if (e.getButton() != MouseEvent.BUTTON1){
            return;
        }

        Map<String,Integer> row_col = new HashMap();

//        if (grabbed_piece == ChessMen.NOTHING) return;
        if(grabbed_figure == null) return;

        to_row = e.getY() / 50;
        to_col = e.getX() / 50;

        if (to_row < 0 || to_row > 9 || to_col < 0 || to_col > 9) {
//            chess_matrix[from_row][from_col] = grabbed_figure; //grabbed_piece;

            //board.getCellByIndex(from_row, from_col).placeIn(grabbed_figure);

//        public Board placeOnBoard(Board board, Map<String,Integer> row_col) {

            row_col.put("row", from_row);
            row_col.put("col", from_col);
            grabbed_figure.placeOnBoard(board, row_col);

            //grabbed_piece = ChessMen.NOTHING;
            grabbed_figure = null;

            repaint();
            return;
        }

        if ((from_row == to_row && from_col == to_col)
                ||
                !isLegalMove(grabbed_piece, from_row, from_col, to_row, to_col)) {
//            chess_matrix[from_row][from_col] = grabbed_figure; //grabbed_piece;
            board.saveFigure(grabbed_figure);
            //board.getCellByIndex(from_row, from_col).placeIn(grabbed_figure);

            row_col.put("row", from_row);
            row_col.put("col", from_col);
            grabbed_figure.placeOnBoard(board, row_col);


            //grabbed_piece = ChessMen.NOTHING;
            grabbed_figure = null;
            repaint();
            return;
        }

        if (isLegalMove(grabbed_piece, from_row, from_col, to_row, to_col)) {
//            chess_matrix[to_row][to_col] = grabbed_figure; //grabbed_piece;
            board.saveFigure(grabbed_figure);
            //board.getCellByIndex(to_row, to_col).placeIn(grabbed_figure);

            row_col.put("row", to_row);
            row_col.put("col", to_col);
            grabbed_figure.placeOnBoard(board, row_col);

        } else {
//            chess_matrix[from_row][from_col] = grabbed_figure; // grabbed_piece;
            board.saveFigure(grabbed_figure);
            //board.getCellByIndex(from_row, from_col).placeIn(grabbed_figure);

            row_col.put("row", from_row);
            row_col.put("col", from_col);
            grabbed_figure.placeOnBoard(board, row_col);

        }

        //System.out.println(grabbed_figure.getDesc()+" : x="+grabbed_figure.getxCoord()/50+", y="+grabbed_figure.getyCoord()/50);
        grabbed_figure.printCells();

        //grabbed_piece = ChessMen.NOTHING;
        grabbed_figure = null;
        myTurn = false;

        repaint();

        String encoding = encodeBoard();
        System.out.println(encoding);

        try {
            serverconnection.send(encoding);
            serverconnection.send("@TOKEN");
        } catch (Exception ex) {

        }

        //myTurn = false;

    }


    public void mouseDragged(MouseEvent e) {
//        if (grabbed_piece == ChessMen.NOTHING) return;
        if (grabbed_figure == null) return;

        x = e.getX();
        y = e.getY();
        repaint();
    }


    public void mouseMoved(MouseEvent e) {
    }


    boolean isLegalMove(int piece, int from_row, int from_col, int to_row, int to_col) {

        if (myTurn == false) return false;

        if (grabbed_figure.getRace().equals(myRace) == false) return false;

        replaced_figure = board.getCellByIndex(to_row, to_col).getFigure();

        if (replaced_figure != null) {

            if (grabbed_figure.getRace().equals(replaced_figure.getRace())) return false;

        }

       // piece.getRace();

//        if (getPieceType(piece) == getPieceType(chess_matrix[to_row][to_col])) return false;
//
//        switch (piece)
//        {
//            case ChessMen.WPAWN:
//                if ((from_row - to_row) == 1 && from_col == to_col)
//                    return true;
//                return false;
//            case ChessMen.BPAWN:
//        }
//
        return true;
    }


    public synchronized void receiveData(String line) {
        if (line.charAt(0) == '@') {
            processCommand(line);
            return;
        }
        decodeBoard(line);
        repaint();
        myTurn = true;

    }


    private void processCommand(String command) {
        if (command.compareTo("@BLACK") == 0) {
            myColor = ChessMen.BLACK;
            myRace = "B";
            chessclient.setTitle("Chess Client - BLACK");
            resetBoard();
        } else if (command.compareTo("@WHITE") == 0) {
            System.out.println("I am WHITE");
            myColor = ChessMen.WHITE;
            myRace = "W";
            chessclient.setTitle("Chess Client - WHITE");
            resetBoard();
            myTurn = true;
        }
        if (command.compareTo("@RESET") == 0) {
            if (myColor == ChessMen.WHITE) myTurn = true;
        } else if (command.compareTo("@TOKEN") == 0) myTurn = true;
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

        Map<String,Integer> row_col = new HashMap();

        //if (encoding.length() < 100) return;

//        Figure prevF = null;

        for (int i = 0; i < encoding.length(); i += 3) {

            row = i / 30;
            col = (i / 3) % 10;
            pieceRace = encoding.substring(i, i + 1); //encoding.charAt(i);
            pieceCode = encoding.substring(i + 1, i + 2); //encoding.charAt(i+1);
            pieceState = encoding.substring(i + 2, i + 3); //encoding.charAt(i+2);

            if (pieceCode.equalsIgnoreCase("Z")) {
                //chess_matrix[row][col] = null;
                board.getCellByIndex(row, col).clear();
            } else {

                if (board.getCellByIndex(row, col).getFigure() == null) {

                    Figure currentFigure = fl.getFigureByCode(pieceCode);
                    currentFigure.setState(pieceState);
                    currentFigure.setRace(pieceRace);

//                    if (currentFigure != null && currentFigure==prevF){
//                        System.out.println("фигура в "+row+" "+col+"та же что и предыдущая --- и это ошибка!");
//                    }
//                    prevF = currentFigure;
//                    if (currentFigure != null && currentFigure==prevF){
//                        System.out.println("этот всегда должен сойтись");
//                    }

                    //chess_matrix[row][col] = currentFigure;

                    row_col.put("row", row);
                    row_col.put("col", col);
                    board.saveFigure(currentFigure);
                    currentFigure.placeOnBoard(board, row_col);

                } else {
//                    if (chess_matrix[row][col] != null && chess_matrix[row][col]==prevF){
//                        System.out.println("фигура в "+row+" "+col+"та же что и предыдущая");
//                    }
                }
            }

        }

    }

}

