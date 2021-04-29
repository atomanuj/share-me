package GitCheck1;

import java.io.*;
import java.net.Socket;

public class upload implements Runnable{
    Socket clientSocket;
    FileInputStream fStream;
    PrintWriter ctrlWriter;
    String pathOnClient;
    String pathOnServer;
    public upload(String pathOnClient, String pathOnServer , PrintWriter ctrlWriter , FileInputStream fStream , Socket clientSocket){
        System.out.println("Upload CLIENT WAS INVOKED");
        this.ctrlWriter = ctrlWriter;
        this.fStream = fStream;
        this.clientSocket = clientSocket;
        this.pathOnClient = pathOnClient;
        this.pathOnServer = pathOnServer;
    }

    @Override
    public void run() {
        System.out.println("Upload CLIENT WAS INVOKED");
        String fname = pathOnClient.trim();
        String filed= pathOnServer.trim();
        ctrlWriter.println("PUT ");
        System.out.println("Searching file name= " + fname);
        try {
            File outFile = new File(fname);

            if (outFile.exists()) {
                System.out.println("File Found ....");
                System.out.println("\nFile is being sent to path= " + filed);
                byte [] mybytearray  = new byte [150000];
                fStream = new FileInputStream(fname);
                FileOutputStream fos = new FileOutputStream(filed);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int received = fStream.read(mybytearray,0,mybytearray.length);
                int len= received ;
                while (received>= 0) {
                    System.out.println("Uploading file ... " + (len/outFile.length())*100 + "%");
                    received = fStream.read(mybytearray,len, (mybytearray.length - len));
                    len += received;
                }
                bos.write(mybytearray, 0, len);
                bos.flush();
                fos.close();
                clientSocket.close();
                System.out.println("File uploaded");
            }
            else {
                System.out.println("File " + fname + " does not exist.");
            }
        }
        catch (IOException e) {
            System.out.println("Error receiving file." + e);

        }
    }
}
