package GitCheck1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class removeFile implements Runnable{
    String fileName;
    PrintWriter ctrlWriter;
    Socket clientSocket;
    public removeFile(String fileName , PrintWriter ctrlWriter ,Socket clientSocket){
        this.clientSocket = clientSocket;
        this.ctrlWriter = ctrlWriter;
        this.fileName = fileName;
    }
    // Remove file from server
    @Override
    public void run() {
        String fileToDelete = fileName.trim();
        ctrlWriter.println("rm " +  fileToDelete);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println(in.readLine());
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
