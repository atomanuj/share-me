package GitCheck1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class shutdown implements Runnable{
    PrintWriter ctrlWriter;
    Socket clientSocket;
    public shutdown(PrintWriter ctrlWriter ,Socket clientSocket){
        this.clientSocket = clientSocket;
        this.ctrlWriter = ctrlWriter;
    }
    //Shutdown Server
    @Override
    public void run() {
        ctrlWriter.println("shutdown " );
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println(in.readLine());
            in.close();
            ctrlWriter.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
