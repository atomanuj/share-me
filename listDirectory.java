package GitCheck1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class listDirectory implements Runnable{
    String dirPath;
    PrintWriter ctrlWriter;
    Socket clientSocket;
    public listDirectory(String dirPath , PrintWriter ctrlWriter , Socket clientSocket){
        this.clientSocket = clientSocket;
        this.ctrlWriter = ctrlWriter;
        this.dirPath = dirPath;
    }
    //List Directory Content
    @Override
    public void run() {
        String dirPathOnServer = dirPath.trim();
        ctrlWriter.println("dir " +  dirPathOnServer);
        String files; //to be used in the while loop (printing files to client)
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while ((files= in.readLine())!=null)
            {
                System.out.println(files);
            }
            ctrlWriter.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
