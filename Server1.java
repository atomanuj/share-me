package GitCheck1;

import java.io.*;
import java.net.*;


public class Server1 {

    public static void main(String[] args) throws IOException
    {
        int portNumber= 4400;

        // server is listening on port 4400
        ServerSocket ss = new ServerSocket(portNumber);


        // running infinite loop for getting
        // client request
        while (true)
        {
            System.out.println("Server waiting for Connection...");
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();
                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for new client");

                // create a new thread object and invoking the start()method.

                new Thread(new clientHandler(s,dis,dos)).start();

            }
            catch (Exception e){
                e.printStackTrace();return;
            }
            //ss.close();
        }
    }

}
