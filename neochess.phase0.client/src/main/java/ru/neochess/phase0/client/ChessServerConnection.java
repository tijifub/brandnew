package ru.neochess.phase0.client;
import ru.neochess.phase0.client.State.ClientStateWrapper;

import java.io.*;
import java.net.Socket;
import ru.neochess.phase0.client.CheMessage.*;
import javax.swing.*;

import javax.swing.*;


/**
 * Created by for on 29.10.16.
 */
public class ChessServerConnection
{
    private static final int PORT = Integer.parseInt(UtiliteChess.getInstance().getPort());
    private static final String HOST = UtiliteChess.getInstance().getHost();
    private InputHandlerThread inputhandler;

    ChessBoard chessboard;


    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;
    private DataOutputStream os;
    private DataInputStream is;
   private ClientStateWrapper clientState;

    public ChessServerConnection(ChessBoard cb , ClientStateWrapper cs)
    {
        chessboard = cb;
        clientState = cs;


        System.out.println(HOST);

        try
        {

            sock = new Socket(HOST, PORT);
            in  = new BufferedReader(new InputStreamReader( sock.getInputStream()));
            out = new PrintWriter( sock.getOutputStream(), true );
            os = new DataOutputStream(sock.getOutputStream());
            is = new DataInputStream(sock.getInputStream());
            inputhandler = new InputHandlerThread(this, in, is);
            inputhandler.start();

        }

        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Server connection problems. Host:" + HOST + ":" + PORT + " error" + e , "Server unreacheble", JOptionPane.PLAIN_MESSAGE);

        }
    }


    public void send(String line)
    {
        out.println(line);
    }
   public void sendMessage(byte[] message)
    {
      /*  try { os = new DataOutputStream(sock.getOutputStream());

        }catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            os.write(message);
        //    os.close();

            // os.writeChar('n');

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void reply(String line)
    {
       // clientState.processMSG(line);
    }

    public synchronized void reply(ChessMessage.NeoCheMessage msg)
    {
         clientState.processMSG(msg);
    }

    public void sendMessage(ChessMessage.NeoCheMessage message)
    {


        try {
            System.out.println(message);
            message.writeDelimitedTo(os);
            os.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
