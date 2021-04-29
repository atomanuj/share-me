package GitCheck1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class download5 implements Runnable{
    PrintWriter ctrlWriter;
    Socket clientSocket;
    String fileName;
    String fileD;
    public download5(String fileName, String fileD , PrintWriter ctrlWriter , Socket clientSocket ){
        this.clientSocket = clientSocket;
        this.ctrlWriter = ctrlWriter;
        this.fileD = fileD;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        ctrlWriter.println("GET " + fileName +" "+ fileD);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
