package GitCheck1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class removeDirectory implements Runnable{
    String dirPath;
    PrintWriter ctrlWriter;
    Socket clientSocket;
    public removeDirectory(String dirPath , PrintWriter ctrlWriter ,Socket clientSocket){
        this.clientSocket = clientSocket;
        this.ctrlWriter = ctrlWriter;
        this.dirPath = dirPath;
    }
    // Remove directory
    @Override
    public void run() {
        String dirPathToDelete = dirPath.trim();
        ctrlWriter.println("rmdir " +  dirPathToDelete);
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
