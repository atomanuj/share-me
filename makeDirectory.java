package GitCheck1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class makeDirectory implements Runnable{
    String newDirPath;
    PrintWriter ctrlWriter;
    Socket clientSocket;
    public makeDirectory(String newDirPath , PrintWriter ctrlWriter ,Socket clientSocket){
        this.clientSocket = clientSocket;
        this.ctrlWriter = ctrlWriter;
        this.newDirPath = newDirPath;
    }
    // Create a new directory
    @Override
    public void run() {
        String dirPath = newDirPath.trim();
        ctrlWriter.println("mkdir " +  dirPath);
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
